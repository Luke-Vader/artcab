import 'package:artcab/artists_die.dart';
import 'package:artcab/home.dart';
import 'package:artcab/signup_or_login.dart';
import 'package:flutter/material.dart';
import 'artists_die.dart';
import 'dart:async';

void main() {
  runApp(new MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Login',
      debugShowCheckedModeBanner: false,
      theme: new ThemeData(
        primarySwatch: primaryBlack,
        fontFamily: 'Monaco'
      ),
      home: new SplashScreen(),
      routes: <String, WidgetBuilder>{
        '/Login': (BuildContext context) => new HomePage(),
      },
    );
  }
}

class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  startTime() async {
    var _duration = new Duration(seconds: 4);
    return new Timer(_duration, navigationPage);
  }

  void navigationPage() {
    Navigator.of(context).pushReplacementNamed('/Login');
  }

  @override
  void initState() {
    super.initState();
    startTime();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        fit: StackFit.expand,
        children: <Widget>[
          Container(
            decoration: BoxDecoration(
              color: Colors.black,
            ),
          ),
          Center(
            child: Text(
              'ArtCab',
              style: TextStyle(
                color: Colors.white,
                fontSize: 48,
              ),
            ),
          )
        ],
      ),
    );
  }
}

const MaterialColor primaryBlack = MaterialColor(
  _blackPrimaryValue,
  <int, Color>{
    50: Color(0xFF000000),
    100: Color(0xFF000000),
    200: Color(0xFF000000),
    300: Color(0xFF000000),
    400: Color(0xFF000000),
    500: Color(_blackPrimaryValue),
    600: Color(0xFF000000),
    700: Color(0xFF000000),
    800: Color(0xFF000000),
    900: Color(0xFF000000),
  },
);
const int _blackPrimaryValue = 0xFF000000;