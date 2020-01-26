import 'package:flutter/material.dart';
//import 'package:cloud_firestore/cloud_firestore.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  //Firestore _firestore = Firestore.instance;
  static final fontColor = Colors.white;
  int _selectedIndex = 0;

  List<Widget> _widgetOptions = <Widget>[
    new SingleChildScrollView(
        scrollDirection: Axis.vertical,
        padding: const EdgeInsets.all(16.0),
        child: new Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Flexible(
              flex: 1,
              child: Text(
                'Directors',
                textAlign: TextAlign.start,
                style: TextStyle(
                  color: fontColor,
                  fontSize: 18,
                ),
              ),
            ),
            Flexible(
              flex: 4,
              child: new SingleChildScrollView(
                scrollDirection: Axis.horizontal,
                padding: const EdgeInsets.all(4.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Container(
                      width: 315,
                      height: 180,
                      child: Card(
                        margin: EdgeInsets.all(4.0),
                        color: Colors.grey[850],
                        elevation: 8.0,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: <Widget>[
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 102,
                                  height: 90.0,
                                  child: CircleAvatar(
                                    backgroundImage: AssetImage(
                                        "assets/images/icon_full.png"),
                                    radius: 43.0,
                                  ),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 205,
                                  height: 90.0,
                                  alignment: Alignment.center,
                                  child: Text(
                                    'John Doe',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      color: fontColor,
                                      fontSize: 22,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Container(
                              height: 82.0,
                              alignment: Alignment.center,
                              child: Text(
                                '"Artists die, But Live"',
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                  color: fontColor,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    Container(
                      width: 315,
                      height: 180,
                      child: Card(
                        margin: EdgeInsets.all(4.0),
                        color: Colors.grey[850],
                        elevation: 8.0,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: <Widget>[
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 102,
                                  height: 90.0,
                                  child: CircleAvatar(
                                    backgroundImage: AssetImage(
                                        "assets/images/icon_full.png"),
                                    radius: 43.0,
                                  ),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 205,
                                  height: 90.0,
                                  alignment: Alignment.center,
                                  child: Text(
                                    'John Doe 2',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      color: fontColor,
                                      fontSize: 22,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Container(
                              height: 82.0,
                              alignment: Alignment.center,
                              child: Text(
                                '"A Players, A+ Projects"',
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                  color: fontColor,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Divider(
              color: fontColor,
            ),
            Flexible(
              flex: 1,
              child: Text(
                'Actors',
                textAlign: TextAlign.start,
                style: TextStyle(
                  color: fontColor,
                  fontSize: 18,
                ),
              ),
            ),
            Flexible(
              flex: 4,
              child: new SingleChildScrollView(
                scrollDirection: Axis.horizontal,
                padding: const EdgeInsets.all(4.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Container(
                      width: 315,
                      height: 180,
                      child: Card(
                        margin: EdgeInsets.all(4.0),
                        color: Colors.grey[850],
                        elevation: 8.0,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: <Widget>[
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 102,
                                  height: 90.0,
                                  child: CircleAvatar(
                                    backgroundImage: AssetImage(
                                        "assets/images/icon_full.png"),
                                    radius: 43.0,
                                  ),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 205,
                                  height: 90.0,
                                  alignment: Alignment.center,
                                  child: Text(
                                    'John Doe',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      color: fontColor,
                                      fontSize: 22,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Container(
                              height: 82.0,
                              alignment: Alignment.center,
                              child: Text(
                                '"Artists die, But Live"',
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                  color: fontColor,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    Container(
                      width: 315,
                      height: 180,
                      child: Card(
                        margin: EdgeInsets.all(4.0),
                        color: Colors.grey[850],
                        elevation: 8.0,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: <Widget>[
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 102,
                                  height: 90.0,
                                  child: CircleAvatar(
                                    backgroundImage: AssetImage(
                                        "assets/images/icon_full.png"),
                                    radius: 43.0,
                                  ),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 205,
                                  height: 90.0,
                                  alignment: Alignment.center,
                                  child: Text(
                                    'John Doe 2',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      color: fontColor,
                                      fontSize: 22,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Container(
                              height: 82.0,
                              alignment: Alignment.center,
                              child: Text(
                                '"A Players, A+ Projects"',
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                  color: fontColor,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Divider(
              color: fontColor,
            ),
            Flexible(
              flex: 1,
              child: Text(
                'Producers',
                textAlign: TextAlign.start,
                style: TextStyle(
                  color: fontColor,
                  fontSize: 18,
                ),
              ),
            ),
            Flexible(
              flex: 4,
              child: new SingleChildScrollView(
                scrollDirection: Axis.horizontal,
                padding: const EdgeInsets.all(4.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Container(
                      width: 315,
                      height: 180,
                      child: Card(
                        margin: EdgeInsets.all(4.0),
                        color: Colors.grey[850],
                        elevation: 8.0,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: <Widget>[
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 102,
                                  height: 90.0,
                                  child: CircleAvatar(
                                    backgroundImage: AssetImage(
                                        "assets/images/icon_full.png"),
                                    radius: 43.0,
                                  ),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 205,
                                  height: 90.0,
                                  alignment: Alignment.center,
                                  child: Text(
                                    'John Doe',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      color: fontColor,
                                      fontSize: 22,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Container(
                              height: 82.0,
                              alignment: Alignment.center,
                              child: Text(
                                '"Artists die, But Live"',
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                  color: fontColor,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    Container(
                      width: 315,
                      height: 180,
                      child: Card(
                        margin: EdgeInsets.all(4.0),
                        color: Colors.grey[850],
                        elevation: 8.0,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              children: <Widget>[
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 102,
                                  height: 90.0,
                                  child: CircleAvatar(
                                    backgroundImage: AssetImage(
                                        "assets/images/icon_full.png"),
                                    radius: 43.0,
                                  ),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8.0),
                                  width: 205,
                                  height: 90.0,
                                  alignment: Alignment.center,
                                  child: Text(
                                    'John Doe 2',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      color: fontColor,
                                      fontSize: 22,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            Container(
                              height: 82.0,
                              alignment: Alignment.center,
                              child: Text(
                                '"A Players, A+ Projects"',
                                textAlign: TextAlign.center,
                                style: TextStyle(
                                  color: fontColor,
                                  fontSize: 18,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Divider(
              color: fontColor,
            ),
          ],
        )),
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
                'Networks',
                style: TextStyle(color: Colors.white),
              )),
          BottomNavigationBarItem(
              backgroundColor: Colors.black,
              icon: new Icon(
                Icons.lightbulb_outline,
                color: Colors.white,
              ),
              title: new Text(
                'Ideas',
                style: TextStyle(color: Colors.white),
              )),
          BottomNavigationBarItem(
              backgroundColor: Colors.black,
              activeIcon: new Icon(
                Icons.person,
                color: Colors.white,
              ),
              icon: new Icon(
                Icons.person_outline,
                color: Colors.white,
              ),
              title: new Text(
                'Profile',
                style: TextStyle(color: Colors.white),
              )),
        ],
        onTap: _onItemTapped,
      ),
    );
  }
}
