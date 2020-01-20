import 'package:artcab/signup_or_login.dart';
import 'package:flutter/material.dart';

class NiCreate extends StatefulWidget {
  @override
  _NiCreateState createState() => _NiCreateState();
}

class _NiCreateState extends State<NiCreate> {
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
            flex: 2,
            child: new Text(
              "Network\nIdeate\nCreate",
              style: TextStyle(
              color: Colors.white,
                fontSize: 36,
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
                MaterialPageRoute(builder: (context) => LoginSignup())
              );
            },
            ),
          )
        ],
      )
    );
  }
}
