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
    return Scaffold(
      body: Center(
        child: FlutterLogo(
          size:  300,
        )
      )
    );
  }
}