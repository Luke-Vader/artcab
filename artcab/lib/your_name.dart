import 'package:flutter/material.dart';
import 'package:artcab/your_contact.dart';

class Name extends StatefulWidget {
  @override
  _NameState createState() => _NameState();
}

class _NameState extends State<Name> {

  final questionColor = Colors.white;
  final formKey = new GlobalKey<FormState>();

  void validateSend() {
    final form = formKey.currentState;
    if(form.validate()) {
      form.save();
      print('Valid');
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Contact()));
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
                  'Your Name?',
                  style: TextStyle(
                    fontSize: 36,
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
                  validator: (value) => value.isEmpty ? 'Name can\'t be empty' : null,
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
