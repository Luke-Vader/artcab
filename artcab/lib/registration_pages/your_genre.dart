import 'package:flutter/material.dart';

class Genre extends StatefulWidget {
  @override
  _GenreState createState() => _GenreState();
}

class _GenreState extends State<Genre> {

  final questionColor = Colors.white;
  final formKey = new GlobalKey<FormState>();

  void validateSend() {
    final form = formKey.currentState;
    if(form.validate()) {
      form.save();
      print('Valid');
    } else {
      print('Invalid');
    }
  }

  List<String> specialList = [
    "Comedy",
    "Drama",
    "Action",
    "Tragedy",
    "Thriller",
    "Horror",
    "Noir",
    "Experimental",
    "Romance",
    "Adventure",
    "Documentary",
    "Animation",
    "Silent",
  ];

  List<String> selectedSpecials = List();

  _showDialog() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Genre'),
          content: MultiSelectChip(
            specialList,
            onSelectionChanged: (selectedList) {
              setState(() {
                selectedSpecials = selectedList;
              });
            },
          ),
          actions: <Widget>[
            FlatButton(
              child: Text("Save"),
              onPressed: () {
                Navigator.of(context).pop();
                print(selectedSpecials.length);
              }, 
            )
          ],
        );
      }
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.black,
        body: new Form(
          key: formKey,
          child: new Column(
          children: <Widget>[
            new Flexible(
              child: Container(),
              flex: 5,
            ),
            new Flexible(
              child: new Container(
                padding: const EdgeInsets.all(16.0),
                child: new Text(
                  'Your Preferred Genre?',
                  style: TextStyle(
                    fontSize: 32,
                    color: questionColor,
                  ),
                ),
              ),
              flex: 3,
            ),
            new Flexible(
              child: new Container(
                padding: const EdgeInsets.all(16.0),
                child: FlatButton(
                  child: Text(
                    'Tap to select one or more',
                    style: TextStyle(
                      fontSize: 14,
                      color: Colors.grey,
                    ),
                  ),
                  onPressed: () => _showDialog(),
                ),
              ),
              flex: 3,
            ),
            new Flexible(
              child: new Center(
                  child: new FlatButton(
                child: new Text(
                  'NEXT',
                  style: TextStyle(
                    fontSize: 14,
                    color: questionColor,
                  ),
                ),
                onPressed: validateSend,
              )),
              flex: 5,
            )
          ],
        ),
      )
    );
  }
}


class MultiSelectChip extends StatefulWidget {

  final List<String> specialList;
  final Function(List<String>) onSelectionChanged;

  MultiSelectChip(
    this.specialList,
    {this.onSelectionChanged}
  );

  @override
  _MultiSelectChipState createState() => _MultiSelectChipState();
}

class _MultiSelectChipState extends State<MultiSelectChip> {

  List<String> selectedSpecials = List();

  _buildChoiceList() {
    List<Widget> choices = List();
    
    widget.specialList.forEach((item) {
      choices.add(Container(
        padding: const EdgeInsets.all(2.0),
        child: ChoiceChip(
          label: Text(item),
          selected: selectedSpecials.contains(item),
          onSelected: (selected) {
            setState(() {
              selectedSpecials.contains(item) ? selectedSpecials.remove(item) : selectedSpecials.add(item);
              widget.onSelectionChanged(selectedSpecials);
            });
          },
        ),
      ));
    }); 
    return choices;
  }

  @override
  Widget build(BuildContext context) {
    return Wrap(
      children: _buildChoiceList(),
    );
  }
}