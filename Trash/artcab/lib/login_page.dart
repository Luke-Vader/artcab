import 'package:artcab/home.dart';
import 'package:artcab/main.dart';
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
  var passKey = new GlobalKey<FormFieldState>();
  final accentColor = Colors.purple[300];
  final fillColor = Colors.white;
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
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => HomePage()),
        );
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
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => HomePage()),
        );
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
      backgroundColor: primaryBlack,
      body: new Container(
        padding: const EdgeInsets.only(bottom: 16.0, left: 16.0, right: 16.0, top: 24.0),
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
          decoration: new InputDecoration(
            labelText: 'Email',
            filled: true,
            fillColor: fillColor,
            focusColor: accentColor,
          ),
          validator: (value) => value.isEmpty ? 'Email can\'t be empty' : null,
          cursorColor: accentColor,
          onSaved: (value) => _email = value,
        ),  
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            labelText: 'Password',
            fillColor: fillColor,
            filled: true,
            focusColor: accentColor,
          ),
          cursorColor: accentColor,
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
            fillColor: fillColor,
            filled: true,
            focusColor: accentColor,
          ),
          cursorColor: accentColor,
          validator: (value) => value.isEmpty ? 'Name can\'t be empty' : null,
          onSaved: (value) => _name = value,
        ), 
        sizedBoxSpace, 
        new TextFormField(
          cursorColor: accentColor,
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Contact Number*',
            focusColor: accentColor,
            filled: true,
          ),
          keyboardType: TextInputType.number,
          validator: (value) => value.isEmpty ? 'Contact Number can\t be empty' : null,
          onSaved: (value) => _phone = value,
        ),
        sizedBoxSpace,
        new TextFormField(
          key: passKey,
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Password*',
            filled: true,
            focusColor: accentColor,
          ),
          onSaved: (value) => _password = value,
          obscureText: true,
          validator: (password) {
            var result = password.length < 8 ? "Password should have at least 8 characters" : null;
            return result;
          },
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Confirm Password*',
            filled: true,
            focusColor: accentColor,
          ),
          onSaved: (value) => _cPassword = value,
          obscureText: true,
          validator: (confirmation) {
            var password = passKey.currentState.value;
            return confirmation == password ? null : "Passwords don\'t match";
          },
        ),
        sizedBoxSpace,
        new TextFormField(
          cursorColor: accentColor,
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Organisation',
            focusColor: accentColor,
            filled: true,
          ),
          onSaved: (value) => _organisation = value,
        ),
        sizedBoxSpace,
        new TextFormField(
          cursorColor: accentColor,
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Film Quote',
            focusColor: accentColor,
            filled: true,
          ),
          onSaved: (value) => _quote = value,
          maxLength: 100,
          maxLines: 3,
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Portfolio',
            filled: true,
            focusColor: accentColor,
          ),
          maxLength: 250,
          maxLines: 3,
          onSaved: (value) => _portfolio = value,
        ),
        sizedBoxSpace,
        new TextFormField(
          decoration: new InputDecoration(
            fillColor: fillColor,
            labelText: 'Links',
            focusColor: accentColor,
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
          elevation: 4,
          color: Colors.purple[900],
          textColor: Colors.white,
          child: new Text('Login', style: new TextStyle(fontSize: 14),),
          onPressed: login,
        ),
        new FlatButton(          
          child: new Text(
            'Become a Member Now!',
            style: new TextStyle(
              fontSize: 14,
              color: fillColor
            )
          ),
          onPressed: switchToRegistration,
        )
      ];
    } else {
      return [
        new RaisedButton(
          color: Colors.purple[900],
          textColor: Colors.white,
          child: new Text('Become a Member', style: new TextStyle(fontSize: 14),),
          onPressed: register,
        ),
        new FlatButton(          
          child: new Text(
            'Already a Member? Login',
            style: new TextStyle(
              fontSize: 14,
              color: fillColor
            )
          ),
          onPressed: switchToLogin,
        )
      ];
    }
  }

}