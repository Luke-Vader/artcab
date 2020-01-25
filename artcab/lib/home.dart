import 'package:flutter/material.dart';
//import 'package:cloud_firestore/cloud_firestore.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  //Firestore _firestore = Firestore.instance;
  int _selectedIndex = 0;

  List<Widget> _widgetOptions = <Widget>[
    new Container(
      child: Scrollable(
        axisDirection: AxisDirection.down,
        viewportBuilder: cards,
      ),
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

  Widget cards(BuildContext context, int carouselIndex) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        Text('Carousel $carouselIndex'),
        SizedBox(
          // you may want to use an aspect ratio here for tablet support
          height: 200.0,
        )
      ],
    );

}