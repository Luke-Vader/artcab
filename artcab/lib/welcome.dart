import 'package:flutter/material.dart';

class Welcome extends StatefulWidget {
  @override
  _WelcomeState createState() => _WelcomeState();
}

class _WelcomeState extends State<Welcome> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Stack(
          children: <Widget>[
            new Container(
              child: new Image.asset(
                'assets/images/welcome_1.jpg',
                fit: BoxFit.fill,
              ),
            ),
            new Text(
              "People live and die.\nArtists die, but live.",
              style: TextStyle(
                color: Colors.white,
                fontSize: 24,
              ),
            ),
          ],
          )
        );
  }
}
