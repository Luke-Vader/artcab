import 'package:flutter/material.dart';
import 'welcome.dart';
import 'dart:async';

void main() {
  runApp(MaterialApp(
    home: MyApp(),
    theme: ThemeData(fontFamily: 'Monaco'),
    debugShowCheckedModeBanner: false,
  ));
} 

class MyApp extends StatefulWidget {
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();  
    Future.delayed(Duration(seconds: 3),
    () {
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => HomePage(),
        ),
      );
    },
  ); 
}

  Widget build(BuildContext context) {
<<<<<<< Updated upstream
    return Scaffold(
      body: Center(
        child: FlutterLogo(
          size:  300,
        )
      )
=======
    return new MaterialApp(
      title: 'Login',
      theme: new ThemeData(
        primarySwatch: primaryBlack,
        fontFamily: 'Monaco'
      ),
      home: new LoginPage(),
>>>>>>> Stashed changes
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