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
      body: Stack(
        children: <Widget>[
          new Center(
            child: new Image.asset(
              'assets/images/welcome_2.jpg',
              fit: BoxFit.contain,
            ),
          ),
          new Center(
            child: new Text(
            "People live and die.\nArtists die, but live.",
            style: TextStyle(
              color: Colors.white,
              fontSize: 22,
              backgroundColor: Colors.green,
            ),
          )
        ),
      ],
    ));
  }
}
