import 'package:artcab/login_page.dart';
import 'package:flutter/material.dart';

class Questionnaire extends StatefulWidget {
  @override
  _QuestionnaireState createState() => _QuestionnaireState();
}

enum FormType { specialisation, cinema, genre }

class _QuestionnaireState extends State<Questionnaire> {
  final questionColor = Colors.white;
  final formKey = new GlobalKey<FormState>();
  FormType _formType = FormType.specialisation;

  void moveToGenre() {
    formKey.currentState.reset();
    setState(() {
      _formType = FormType.genre;
    });
  }

  void movetoTaste() {
    formKey.currentState.reset();
    setState(() {
      _formType = FormType.cinema;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: Center(
        child: new Form(
          key: formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: buildSpecialisation(),
          ),
        ),
      ),
    );
  }

  List<Widget> buildSpecialisation() {
    if (_formType == FormType.specialisation) {
      return [
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
                  border: OutlineInputBorder()),
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
                onPressed: moveToGenre,
              ),
            ),
          ),
          flex: 5,
        )
      ];
    } else if (_formType == FormType.genre) {
      return [
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
                  border: OutlineInputBorder()),
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
                onPressed: movetoTaste,
              ),
            ),
          ),
          flex: 5,
        )
      ];
    } else {
      return [
        new Flexible(
          child: Container(),
          flex: 5,
        ),
        new Flexible(
          child: new Container(
            padding: const EdgeInsets.all(16.0),
            child: new Text(
              'Your Cinema Taste?',
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
                  border: OutlineInputBorder()),
            ),
          ),
          flex: 3,
        ),
        new Flexible(
          child: Container(
            child: new Center(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  new FlatButton(
                    child: new Text(
                      'SKIP',
                      style: TextStyle(
                        fontSize: 14,
                        color: questionColor,
                      ),
                    ),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => LoginPage()),
                      );
                    },
                  ),
                  new FlatButton(
                    child: new Text(
                      'NEXT',
                      style: TextStyle(
                        fontSize: 14,
                        color: questionColor,
                      ),
                    ),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => LoginPage()),
                      );
                    },
                  )
                ],
              ),
            ),
          ),
          flex: 5,
        )
      ];
    }
  }
}
