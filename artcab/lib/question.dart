import 'package:artcab/login_page.dart';
import 'package:flutter/material.dart';

class Questionnaire extends StatefulWidget {
  @override
  _QuestionnaireState createState() => _QuestionnaireState();
}

class _QuestionnaireState extends State<Questionnaire> {

  void skip() {
    

  }

  final questionColor = Colors.white;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            new Flexible(
              child: Container(),
              flex: 5,
            ),
            new Flexible(
              child: new Container(
                padding: const EdgeInsets.all(16.0),
                child: new Text(
                  'Your Specialisation?',
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
                  decoration: new InputDecoration(
                    fillColor: questionColor,
                    filled: true,
                    border: OutlineInputBorder()
                  ),
                ),
              ),
              flex: 3,
            ),
            new Flexible(
              child: Container(
                child: new Center(
                  child: new FlatButton(
                    child: new Text(
                      'SKIP',
                      style: TextStyle(
                        fontSize: 14,
                        color: questionColor,
                      ),
                    ),
                    onPressed: () => {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => LoginPage()),
                      ),
                    },
                  ),
                ),
              ),
              flex: 5,
            )
          ],
        ),
      ),
    );
  }
}