import 'package:flutter/material.dart';
//import 'package:cloud_firestore/cloud_firestore.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  //Firestore _firestore = Firestore.instance;
  int _selectedIndex = 0;

  //font color for the contents inside the card
  static final cardContentColor = Colors.white;

  //font color for the title of the card sections
  static final specialFontColor = Colors.black;

  static final cardColor = Colors.black;

  static final fontColor = Colors.white;
  static final bgColor = Colors.white;

  List<String> title = [
    "Networks",
    "Ideas",
    "Profile",
  ];

  List<Widget> _widgetOptions = <Widget>[
    ListView.builder(
      itemBuilder: (context, index) {
        return Column(
          children: <Widget>[
            Text('Header $index'),
            ListView.builder(
              itemCount: 12,
              physics: ClampingScrollPhysics(),
              shrinkWrap: true,
              itemBuilder: (BuildContext context, int index) {
                return Text('Nested List item $index');
              },
            )
          ],
        );
      },
      itemCount: 7,
    ),
    ListView.builder(
      padding: EdgeInsets.all(16.0),
      scrollDirection: Axis.vertical,
      itemCount: 3,
      itemBuilder: (BuildContext context, int index) {
        return idea(index);
      },
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
        elevation: 0,
        title: new Text(
          title[_selectedIndex],
          style: TextStyle(color: Colors.black, fontSize: 20),
        ),
        backgroundColor: bgColor,
      ),
      backgroundColor: bgColor,
      body: new Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _selectedIndex,
        backgroundColor: bgColor,
        type: BottomNavigationBarType.shifting,
        items: [
          BottomNavigationBarItem(
              backgroundColor: bgColor,
              icon: new Icon(
                Icons.filter_list,
                color: Colors.black,
              ),
              title: new Text(
                title[0],
                style: TextStyle(color: Colors.black),
              )),
          BottomNavigationBarItem(
              backgroundColor: bgColor,
              icon: new Icon(
                Icons.lightbulb_outline,
                color: Colors.black,
              ),
              title: new Text(
                title[1],
                style: TextStyle(color: Colors.black),
              )),
          BottomNavigationBarItem(
              backgroundColor: bgColor,
              activeIcon: new Icon(
                Icons.person,
                color: Colors.black,
              ),
              icon: new Icon(
                Icons.person_outline,
                color: Colors.black,
              ),
              title: new Text(
                title[2],
                style: TextStyle(color: Colors.black),
              )),
        ],
        onTap: _onItemTapped,
      ),
    );
  }

  static Widget idea(int index) {
    return Card(
        elevation: 4,
        color: Colors.yellow,
        child: Container(
          padding: EdgeInsets.all(8.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              Text(
                '\"Idea is the most important thing in the film industry as this is what defines the creativity and ambtions of this industry $index\"',
                style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  CircleAvatar(
                    backgroundImage: AssetImage("assets/images/icon_full.png"),
                  ),
                  Text(
                    'Username',
                    textAlign: TextAlign.start,
                  ),
                  Text(
                    '18h',
                    textAlign: TextAlign.end,
                  )
                ],
              )
            ],
          ),
        ));
  }
}
