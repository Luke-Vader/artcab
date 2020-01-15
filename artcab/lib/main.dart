import 'package:flutter/material.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {

  Widget titleSection = Container(
    padding: const EdgeInsets.all(31),
    child: Row(
      children: <Widget>[
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                padding: const EdgeInsets.only(bottom: 8),
                child: Text(
                  'Something Up',
                  style: TextStyle(
                    fontWeight: FontWeight.bold
                  ),
                ),
              ),
              Text(
                'Something Down',
                style: TextStyle(
                  color: Colors.green[100],
                ),
              )
            ],
          ),
        )
        
      ],
    ),
  );

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Layout Demo',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Flutter Layout Demo'),
        ),
        body: Center(
          child: Text('Hello World'),
        ),
      ),
    );
  }
}