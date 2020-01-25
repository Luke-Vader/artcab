import 'package:flutter/material.dart';
import 'package:artcab/registration_pages/your_ig.dart';


class Email extends StatefulWidget {

  final List<String> specialisations;
  final List<String> specialCategories;
  final List<String> genre;
  final List<String> taste;
  final String contact;
  final String name;

  Email({
    Key key,
    this.specialisations,
    this.specialCategories,
    this.genre,
    this.taste,
    this.contact,
    this.name,
  }) : super (key: key);

  @override
  _EmailState createState() => _EmailState();
}

class _EmailState extends State<Email> {

  final questionColor = Colors.white;
  final email = TextEditingController();
  final formKey = new GlobalKey<FormState>();

  void validateSend() {
    final form = formKey.currentState;
    if(form.validate()) {
      form.save();
      print('Valid');
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Instagram(
          genre: widget.genre,
                    specialCategories: widget.specialCategories,
                    specialisations: widget.specialisations,
                    taste: widget.taste,
                    name: widget.name,
                    contact: widget.contact,
                    email: email.text,
        )));
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
                  'Your Email address?',
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
                  controller: email,
                  keyboardType: TextInputType.emailAddress,
                  validator: (value) => value.isEmpty ? 'This can\'t be empty' : null,
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
