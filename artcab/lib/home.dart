import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  int _selectedIndex = 0;

  static const List<Widget> _widgetOptions = <Widget>[
    Text(
      'Index 0: Feed',
      style: TextStyle(color: Colors.white),
    ),
    Text(
      'Index 1: Ideas',
      style: TextStyle(color: Colors.white),
    ),
    Text(
      'Index 2: Profile',
      style: TextStyle(color: Colors.white),
    ),
  ];

void _onItemTapped(int index) {
  setState(() {
    _selectedIndex = index;
  });
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: new AppBar(
        title: new Text(
          'ArtCab',
          style: TextStyle(color: Colors.white, fontSize: 20),
        ),
      ),
      backgroundColor: Colors.black,
      body: new Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _selectedIndex,
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
        onTap: _onItemTapped,
      ),
    );
  }
}