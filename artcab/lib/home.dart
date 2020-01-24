import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  int _cIndex = 0;

  void _incrementTab(index) {
    setState(() {
      _cIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _cIndex,
        backgroundColor: Colors.black,
        type: BottomNavigationBarType.shifting,
        items: [
          BottomNavigationBarItem(
            backgroundColor: Colors.black,
            icon: new Icon(
              Icons.filter_list,
              color: Colors.white,
            ),
            title: new Text(
              'Feed',
              style: TextStyle(
                color: Colors.white
              ),
            )
          ),
          BottomNavigationBarItem(
            backgroundColor: Colors.black,
            icon: new Icon(
              Icons.lightbulb_outline,
              color: Colors.white,
            ),
            title: new Text(
              'Ideas',
              style: TextStyle(
                color: Colors.white
              ),
            )
          ),
          BottomNavigationBarItem(
            backgroundColor: Colors.black,
            activeIcon: new Icon(Icons.person, color: Colors.white,),
            icon: new Icon(
              Icons.person_outline,
              color: Colors.white,
            ),
            title: new Text(
              'Profile',
              style: TextStyle(
                color: Colors.white
              ),
            )
          ),
        ],
        onTap: (index) {
          _incrementTab(index);
        },
      ),
    );
  }
}