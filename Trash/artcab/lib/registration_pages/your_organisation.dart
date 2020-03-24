import 'package:artcab/registration_pages/your_quote.dart';
import 'package:flutter/material.dart';

class Organisation extends StatefulWidget {

  final List<String> specialisations;
  final List<String> specialCategories;
  final List<String> genre;
  final List<String> taste;
  final String name;
  final String contact;
  final String email;
  final String instagram;

  Organisation({
    Key key,
    this.specialisations,
    this.specialCategories,
    this.genre,
    this.email,
    this.contact,
    this.taste,
    this.name,
    this.instagram,
  }) : super (key: key);

  @override
  _OrganisationState createState() => _OrganisationState();
}

class _OrganisationState extends State<Organisation> {

  final questionColor = Colors.white;
  final formKey = new GlobalKey<FormState>();
  final organisation = TextEditingController();

  void validateSend() {
    final form = formKey.currentState;
    if(form.validate()) {
      form.save();
      print('Valid');
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Quote(
                    genre: widget.genre,
                    specialCategories: widget.specialCategories,
                    specialisations: widget.specialisations,
                    taste: widget.taste,
                    name: widget.name,
                    email: widget.email,
                    contact: widget.contact,
                    instagram: widget.instagram,
                    organisation: organisation.text,
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
                  'Your Instagram?',
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
                  controller: organisation,
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
