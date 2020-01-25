import 'package:artcab/registration_pages/your_picture.dart';
import 'package:flutter/material.dart';

class Links extends StatefulWidget {

  final List<String> specialisations;
  final List<String> specialCategories;
  final List<String> genre;
  final List<String> taste;
  final String name;
  final String contact;
  final String email;
  final String instagram;
  final String organisation;
  final String quote;
  final String portfolio;

  Links({
    Key key,
    this.specialisations,
    this.specialCategories,
    this.genre,
    this.email,
    this.contact,
    this.taste,
    this.name,
    this.instagram,
    this.organisation,
    this.quote,
    this.portfolio,
  }) : super (key: key);

  @override
  _LinksState createState() => _LinksState();
}

class _LinksState extends State<Links> {

  final questionColor = Colors.white;
  final formKey = new GlobalKey<FormState>();
  final link1 = TextEditingController();
  final link2 = TextEditingController();
  final link3 = TextEditingController();

  void validateSend() {
    final form = formKey.currentState;
    if(form.validate()) {
      form.save();
      print('Valid');
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Picture(
               genre: widget.genre,
                    specialCategories: widget.specialCategories,
                    specialisations: widget.specialisations,
                    taste: widget.taste,
                    name: widget.name,
                    email: widget.email,
                    contact: widget.contact,
                    instagram: widget.instagram,
                    quote: widget.quote,
                    portfolio: widget.portfolio,
                    link1: link1.text,
        ))
      );
    } else {
      print('Invalid');
    }
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
                  'Your Portfolio?',
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
                child: new TextFormField(
                  controller: link1,
                  validator: (value) => value.isEmpty ? 'Email can\'t be empty' : null,
                  decoration: new InputDecoration(
                      fillColor: questionColor,
                      filled: true,
                      border: OutlineInputBorder()),
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
