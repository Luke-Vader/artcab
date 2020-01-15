import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

enum FormType {
  login,
  register
}

class _LoginPageState extends State<LoginPage> {

  final formKey = new GlobalKey<FormState>();
  final cursorColor = Colors.red;
  final sizedBoxSpace = SizedBox(height: 24);
  String _name;
  String _phone;
  String _organisation;
  String _quote;
  String _portfolio;
  String _links;
  String _email;
  String _password;
  String _cPassword;
  FormType _formType = FormType.login;

  bool validate() {
    final form = formKey.currentState;
    if(form.validate()) {
      form.save();
      print('Valid Form');
      return true;
    } else {
      print('Invalid Form');
      return false;
    }
  }

  void login() async {
    if(validate()) {
      try {
        final AuthResult _result = await FirebaseAuth.instance.signInWithEmailAndPassword(email: _email, password: _password);
        print('Signed in: ${_result.user.uid}');
      } catch (e) {
        print('$e');
      }
    }
  }

  void register() async {
    if (validate()) {
      try {
        final AuthResult _result = await FirebaseAuth.instance.createUserWithEmailAndPassword(email: _email, password: _password);
        print('Created User: ${_result.user.uid}');
      } catch(e) {
        print('$e');
      }
    }
   }

  void switchToLogin() {
    formKey.currentState.reset();
    setState(() {
      _formType = FormType.login;
    });
  }

  void switchToRegistration() {
    formKey.currentState.reset();
    setState(() {
      _formType = FormType.register;
    });
  }

  @override
  Widget build(BuildContext context) {

    return new Scaffold(
      body: new Container(
        padding: const EdgeInsets.all(16.0),
        child: new Form(
          key: formKey,
          child: Scrollbar(
            child: SingleChildScrollView(
              dragStartBehavior: DragStartBehavior.down,
              child: new Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: buildInputs() + buildSubmitButtons(),
              ),
            ),
          ),
        ),
      ),
    );
  }

  List<Widget> buildInputs() {
    if (_formType == FormType.login) {
      return [
        new TextFormField(
          decoration: new InputDecoration(labelText: 'Email'),
          validator: (value) => value.isEmpty ? 'Email can\'t be empty' : null,
          cursorColor: cursorColor,
          onSaved: (value) => _email = value,
        ),  
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(labelText: 'Password'),
          cursorColor: cursorColor,
          validator: (value) => value.isEmpty ? 'Password can\'t be empty' : null,
          onSaved: (value) => _password = value,
          obscureText: true,
        ),
      ];
    } else {
      return [
        new TextFormField(
          decoration: new InputDecoration(
            labelText: 'Name*',
            filled: true,
            focusColor: Colors.red,
          ),
          cursorColor: cursorColor,
          validator: (value) => value.isEmpty ? 'Name can\'t be empty' : null,
          onSaved: (value) => _name = value,
        ), 
        sizedBoxSpace, 
        new TextFormField(
          cursorColor: cursorColor,
          decoration: new InputDecoration(
            labelText: 'Contact Number*',
            focusColor: Colors.red,
            filled: true,
          ),
          validator: (value) => value.isEmpty ? 'Contact Number can\t be empty' : null,
          onSaved: (value) => _phone = value,
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            labelText: 'Password*',
            filled: true,
            focusColor: Colors.red,
          ),
          onSaved: (value) => _password = value,
          obscureText: true,
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            labelText: 'Confirm Password*',
            filled: true,
            focusColor: Colors.red,
          ),
          onSaved: (value) => _cPassword = value,
          obscureText: true,
        ),
        sizedBoxSpace,
        new TextFormField(
          cursorColor: cursorColor,
          decoration: new InputDecoration(
            labelText: 'Organisation',
            focusColor: Colors.red,
            filled: true,
          ),
          onSaved: (value) => _organisation = value,
        ),
        sizedBoxSpace,
        new TextFormField(
          cursorColor: cursorColor,
          decoration: new InputDecoration(
            labelText: 'Film Quote',
            focusColor: Colors.red,
            filled: true,
          ),
          onSaved: (value) => _quote = value,
          maxLines: 3,
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            labelText: 'Portfolio',
            filled: true,
            hintMaxLines: 250,
            focusColor: Colors.red,
          ),
          maxLength: 250,
          maxLines: 3,
          onSaved: (value) => _portfolio = value,
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            labelText: 'Links',
            focusColor: Colors.red,
            filled: true,
          ),
          onSaved: (value) => _links = value,
          maxLines: 2,
        ),
      ];
    }
  }

  List<Widget> buildSubmitButtons() {
    if (_formType == FormType.login) {
      return [
        new RaisedButton(
          child: new Text('Login', style: new TextStyle(fontSize: 14),),
          onPressed: login,
        ),
        new FlatButton(          
          child: new Text('Become a Member Now!', style: new TextStyle(fontSize: 14)),
          onPressed: switchToRegistration,
        )                    
      ];
    } else {
      return [
        new RaisedButton(
          child: new Text('Become a Member', style: new TextStyle(fontSize: 14),),
          onPressed: register,
        ),
        new FlatButton(          
          child: new Text('Already a Member? Login', style: new TextStyle(fontSize: 14)),
          onPressed: switchToLogin,
        )
      ];
    }
  }

}