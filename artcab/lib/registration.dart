import 'package:flutter/material.dart';

class Registration extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Become a Member"),
      ),
      body: Form(),
    );
  }
}

class Form extends StatefulWidget {
  const Form({Key key}) : super(key: key);

  @override
  _FormState createState() => _FormState();
}

class PersonalData {
  String name = '';
  String contact = '';
  String email = '';
  String organisation = '';
  String quote = '';
  String portfolio = '';
  String links = '';
  String password = '';
  String confirmPassword = '';
}

class PasswordField extends StatefulWidget {
  const PasswordField({
    this.fieldKey,
    this.hintText,
    this.labelText,
    this.onSaved,
    this.validator,
    this.onFieldSubmitted,
  });

  final Key fieldKey;
  final String hintText;
  final String labelText;
  final FormFieldSetter<String> onSaved;
  final FormFieldValidator<String> validator;
  final ValueChanged<String> onFieldSubmitted;

  @override
  _PasswordFieldState createState() => _PasswordFieldState();
}

class _PasswordFieldState extends State<PasswordField> {
  bool _obscureText = true;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      key: widget.fieldKey,
      obscureText: _obscureText,
      cursorColor: Theme.of(context).cursorColor,
      maxLength: 8,
      onSaved: widget.onSaved,
      validator: widget.validator,
      onFieldSubmitted: widget.onFieldSubmitted,
      decoration: InputDecoration(
        filled: true,
        hintText: widget.hintText,
        labelText: widget.labelText,
      ),
    );
  }
}

class _FormState extends State<Form> {
  @override
  Widget build(BuildContext context) {
    return Container(
      
    );
  }
}