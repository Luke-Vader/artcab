import 'package:artcab/welcome.dart';
import 'package:flutter/material.dart';
import 'welcome.dart';

class Artists extends StatefulWidget {
  @override
  _ArtistsState createState() => _ArtistsState();
}

class _ArtistsState extends State<Artists> {
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
              "People live and die.\nArtists die, but live.",
              style: TextStyle(
              color: Colors.white,
                fontSize: 24,
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
                MaterialPageRoute(builder: (context) => Welcome())
              );
            },
            ),
          )
        ],
      )
    );
  }
}
