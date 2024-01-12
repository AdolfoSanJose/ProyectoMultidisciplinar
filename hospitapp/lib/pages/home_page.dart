import 'package:flutter/material.dart';
import 'package:flutter_application_1/components/my_button.dart';
import 'package:flutter_application_1/pages/login_or_register_page.dart';
import 'package:flutter_application_1/pages/login_page.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: MyButton(
              onTap: () => (Navigator.of(context).pushReplacement(
                  MaterialPageRoute(
                      builder: (_) => const LoginOrRegisterPage()))),
              text: "Volver")),
    );
  }
}
