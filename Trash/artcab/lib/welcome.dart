import 'package:artcab/nicreate.dart';
import 'package:flutter/material.dart';

class Welcome extends StatefulWidget {
  @override
  _WelcomeState createState() => _WelcomeState();
}

class _WelcomeState extends State<Welcome> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: Column(
        children: <Widget>[
          new Flexible(
            child: Container(),
            flex: 3,
          ),
          new Flexible(
            flex: 1,
            child: new Text(
              "Welcome to",
              style: TextStyle(
              color: Colors.white,
                fontSize: 36,
              ),
            )
          ),
          new Flexible(
            flex: 1,
            child: new Text(
              "ArtCab",
              style: TextStyle(
              color: Colors.white,
                fontSize: 48,
              ),
            )
          ),
          new Flexible(
            flex: 2,
            child: Container(),
          ),
          new Flexible(
            flex: 1,
            child: new FlatButton(
              child: new Icon(
                Icons.arrow_upward,
                color: Colors.white,
              ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => NiCreate())
              );
            },
            ),
          )
        ],
      )
    );
  }
}