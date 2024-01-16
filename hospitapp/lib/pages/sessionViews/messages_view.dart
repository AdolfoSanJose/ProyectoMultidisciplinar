import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_application_1/pages/main_screen.dart';

class MessagesView extends StatefulWidget {
  final VoidCallback navigateToMainScreen;

  const MessagesView({required this.navigateToMainScreen, Key? key})
      : super(key: key);

  @override
  State<MessagesView> createState() => _MessagesViewState();
}

class _MessagesViewState extends State<MessagesView> {
  final _key = GlobalKey<FormState>();

  final TextEditingController destinatarioController = TextEditingController();
  final TextEditingController asuntoController = TextEditingController();
  final TextEditingController contenidoController = TextEditingController();

  void showSnackBar(String message) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      content: Text(message),
      duration: const Duration(seconds: 2),
    ));
  }

  Future<void> sendEmail() async {
    String baseUrl = getBaseUrl();

    final response = await http.post(
      Uri.parse('$baseUrl/api/email/send'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'to': destinatarioController.text,
        'subject': asuntoController.text,
        'content': contenidoController.text,
      }),
    );

    if (response.statusCode == 200) {
      print('Email sent successfully');
      showSnackBar('Correo enviado con Exito!');
      destinatarioController.text = '';
      asuntoController.text = '';
      contenidoController.text = '';
      widget.navigateToMainScreen(); // Call the callback to navigate back
    } else {
      print('Failed to send email');
      showSnackBar('Failed to send email');
    }
  }

  String getBaseUrl() {
    return kIsWeb ? 'http://localhost:8080' : 'http://10.0.2.2:8080';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        width: double.infinity,
        height: double.infinity, // Cover the entire screen
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            colors: [Color.fromRGBO(144, 202, 249, 1), Colors.white],
            begin: Alignment.topRight,
            end: Alignment.bottomLeft,
          ),
        ),
        child: SingleChildScrollView(
          child: Form(
            key: _key,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const SizedBox(height: 50),
                _buildText('Contacto',
                    fontSize: 32.0, fontWeight: FontWeight.bold),
                _buildTextField(destinatarioController, 'Destinatario'),
                _buildTextField(asuntoController, 'Asunto'),
                _buildTextField(contenidoController, 'Su mensaje'),
                const SizedBox(height: 25),
                _buildEmailButton(sendEmail),
                const SizedBox(height: 50),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildText(String text, {double? fontSize, FontWeight? fontWeight}) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Text(
        text,
        style: TextStyle(
          fontSize: fontSize,
          fontWeight: fontWeight,
        ),
      ),
    );
  }

  Widget _buildTextField(TextEditingController controller, String hintText,
      {double height = 60.0, double width = 1100.0}) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
        width: width,
        height: controller == contenidoController ? 150.0 : height,
        child: TextFormField(
          controller: controller,
          maxLines: controller == contenidoController
              ? 5
              : 1, // Set to 5 for more lines in contenidoController
          decoration: InputDecoration(
            hintText: hintText,
            border: const OutlineInputBorder(),
          ),
        ),
      ),
    );
  }

  Widget _buildEmailButton(VoidCallback onPressed) {
    return ElevatedButton(
      onPressed: onPressed,
      child: const Text('Enviar Correo'),
    );
  }
}
