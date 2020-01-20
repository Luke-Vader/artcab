import 'package:flutter/material.dart';

class LoginSignup extends StatefulWidget {
  @override
  _LoginSignupState createState() => _LoginSignupState();
}

class _LoginSignupState extends State<LoginSignup> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: new Center(
        child: new Column(
          children: <Widget>[
            new Flexible(
              child: Text(
                'ArtCab',
                style: TextStyle(
                  fontSize: 48,
                  color: Colors.white,
                ),
              ),
              flex: 1,
            ),
            new Flexible(
              child: new Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                new OutlineButton(
                borderSide: BorderSide(
                  color: Colors.white,
                ),
                shape: new RoundedRectangleBorder(
                borderRadius: new BorderRadius.circular(10.0)),
                child: new Text(
                  'Become a Member',
                  style: TextStyle(
                    fontSize: 24,
                    color: Colors.white,
                  ),
              ),
              onPressed: () {},
            ),
            new Container(
              height: 16,
            ),
            new OutlineButton(
              borderSide: BorderSide(
                color: Colors.white,
              ),
              shape: new RoundedRectangleBorder(
                  borderRadius: new BorderRadius.circular(10.0)),
              child: new Text(
                'Already a Member',
                style: TextStyle(
                  fontSize: 24,
                  color: Colors.white,
                ),
              ),
              onPressed: () {},
            )
          ],
        ),
              flex: 1,
            )
          ],
        )
      ),
    );
  }
}
