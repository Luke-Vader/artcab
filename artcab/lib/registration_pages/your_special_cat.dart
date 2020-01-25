import 'package:artcab/registration_pages/your_genre.dart';
import 'package:flutter/material.dart';

class SCategory extends StatefulWidget {
  final List<String> categories;

  SCategory({Key key, this.categories}) : super(key: key);

  @override
  _SCategoryState createState() => _SCategoryState();
}

class _SCategoryState extends State<SCategory> {
  final questionColor = Colors.white;
  final formKey = new GlobalKey<FormState>();

  void validateSend() {
    final form = formKey.currentState;
    if (form.validate()) {
      form.save();
      print('Valid');
      /*Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Genre()));*/
    } else {
      print('Invalid');
    }
  }

  List<String> director = [
    "Short Film",
    "Feature Film",
    "Documentary",
    "Web Series",
    "TV Series",
    "Music Video",
  ];

  List<String> editor = ["Adobe Premier", "FCP", "Avid"];

  List<String> producer = [
    "Producer",
    "Executive Producer",
    "Line Producer",
    "Supervising Producer",
    "Co-Producer",
    "Associate Producer"
  ];

  List<String> actor = [
    "Classical Acting",
    "Chekhov Acting",
    "Method Acting",
    "Stanislavski's System",
    "Meisner Technique",
    "Brechtian Method",
  ];

  List<String> writer = [
    "Short Film",
    "Feature Film",
    "Documentary",
    "TV Series",
    "Web Series",
    "Music Video",
  ];

  List<String> music = [
    "Original Score",
    "Songs",
    "Sound Design and Mixing",
    "Soud Recording and Dubbing",
  ];

  List<String> visual = [
    "Special Effects",
    "Visual Effects",
    "Motion Capture",
    "Animation",
    "Composting",
  ];

  List<String> selectedSpecials = List();

  _showDialog() {
    if (widget.categories.contains("Director")) {
      selectedSpecials += director;
    }
    if (widget.categories.contains("Writer")) {
      selectedSpecials += writer;
    }
    if (widget.categories.contains("Actor")) {
      selectedSpecials += actor;
    }
    if (widget.categories.contains("Producer")) {
      selectedSpecials += producer;
    }
    if (widget.categories.contains("Music and Sound")) {
      selectedSpecials += music;
    }
    if (widget.categories.contains("Visual Effects")) {
      selectedSpecials += visual;
    } else {
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (BuildContext context) => Genre(
                    specialisations: widget.categories,
                    specialCategories: null,
                  )));
    }
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text('Specialisation'),
            content: Container(
              child: SingleChildScrollView(
                child: MultiSelectChip(
                  selectedSpecials,
                  onSelectionChanged: (selectedList) {
                    setState(() {
                      selectedSpecials = selectedList;
                    });
                  },
                ),
              ),
            ),
            actions: <Widget>[
              FlatButton(
                child: Text("Save"),
                onPressed: () {
                  Navigator.of(context).pop();
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (BuildContext context) => Genre(
                                specialisations: widget.categories,
                                specialCategories: selectedSpecials,
                              )));
                  print(selectedSpecials.length);
                },
              )
            ],
          );
        });
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
                    'Specialisation Sub Categories?',
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
        ));
  }
}

class MultiSelectChip extends StatefulWidget {
  final List<String> specialList;
  final Function(List<String>) onSelectionChanged;

  MultiSelectChip(this.specialList, {this.onSelectionChanged});

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
              selectedSpecials.contains(item)
                  ? selectedSpecials.remove(item)
                  : selectedSpecials.add(item);
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
