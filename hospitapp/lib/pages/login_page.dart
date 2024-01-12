import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter_application_1/controllers/user.dart';
import 'package:flutter_application_1/pages/main_screen.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import '../components/my_textfield.dart';
import '../components/my_button.dart';
import '../controllers/roles.dart';

class LoginPage extends StatefulWidget {
  void Function()? onTap;
  LoginPage({super.key, required this.onTap});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  // Text controllers
  final mailController = TextEditingController();
  final passwdController = TextEditingController();

  // Form key
  final formKey = GlobalKey<FormState>();

  User user = User(
      idUser: 0,
      name: '',
      email: '',
      password: '',
      dni: '',
      idRol: Roles(idRol: 0));

  // Sign in method
  Future signIn() async {
    String baseUrl = getBaseUrl();
    Uri url = Uri.parse("$baseUrl/user/login");
    try {
      user.email = mailController.text;
      user.password = passwdController.text;
      var res = await http.post(url,
          headers: {'Content-Type': 'application/json'},
          body: json.encode({'email': user.email, 'password': user.password}));
      print(res.body);
      print(user.email);
      if (res.statusCode == 200) {
        return Navigator.of(context)
            .pushReplacement(MaterialPageRoute(builder: (_) => MainScreen()));
      } else {
        openDialog("Credenciales incorrectas");
      }
    } catch (error) {
      print("Error in signIn: $error");
    }
  }

  String getBaseUrl() {
    return kIsWeb ? 'http://localhost:8080' : 'http://10.0.2.2:8080';
  }

  // Error Dialog
  Future openDialog(String message) => showDialog(
      context: context,
      builder: (context) => AlertDialog(
            title: Text(message),
            titleTextStyle: const TextStyle(
              color: Colors.red,
              fontSize: 20,
            ),
            actions: const [CloseButton()],
          ));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Hospitapp",
          style: TextStyle(fontSize: 35),
        ),
        leading: const Icon(
          Icons.local_hospital_rounded,
          size: 40,
        ),
        centerTitle: true,
        backgroundColor: Colors.blue.shade400,
      ),
      body: SafeArea(
        child: Container(
          width: double.infinity,
          decoration: BoxDecoration(
            gradient: LinearGradient(
              colors: [Colors.blue.shade200, Colors.white],
              begin: Alignment.topRight,
              end: Alignment.bottomLeft,
            ),
          ),
          child: Center(
            child: SingleChildScrollView(
              child: Form(
                key: formKey,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const SizedBox(height: 15),

                    // Log in icon
                    Icon(
                      Icons.login,
                      size: 80,
                      color: Colors.blue.shade700,
                    ),

                    const SizedBox(height: 50),

                    // Welcome back text
                    const Text(
                      '¡Bienvenido de vuelta!',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 15,
                      ),
                    ),

                    const SizedBox(height: 15),

                    // Log in container
                    Container(
                      height: 500,
                      width: 350,
                      decoration: BoxDecoration(
                          color: const Color.fromARGB(37, 0, 134, 243),
                          borderRadius: BorderRadius.circular(20)),
                      child: Column(children: [
                        const SizedBox(height: 40),

                        // Correo
                        MyTextField(
                          controller: mailController,
                          hintText: 'Correo',
                          obscureText: false,
                          errorText: 'Campo correo no puede estar vacío',
                        ),

                        const SizedBox(height: 15),

                        // Password
                        MyTextField(
                          controller: passwdController,
                          hintText: 'Contraseña',
                          obscureText: true,
                          errorText: 'Campo contraseña no puede estar vacío',
                        ),

                        const SizedBox(height: 25),

                        // Sign in button
                        MyButton(
                          onTap: () {
                            if (formKey.currentState!.validate()) {
                              signIn();
                            }
                          },
                          text: 'Iniciar Sesión',
                        ),

                        const SizedBox(height: 50),

                        // Register
                        const Padding(
                          padding: EdgeInsets.symmetric(horizontal: 20),
                          child: Divider(
                            thickness: 1,
                            color: Colors.blueGrey,
                          ),
                        ),

                        const SizedBox(height: 50),

                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            const Text('¿No eres miembro?'),
                            const SizedBox(width: 5),
                            GestureDetector(
                              onTap: widget.onTap,
                              child: const Text(
                                'Registrate ahora',
                                style: TextStyle(
                                  color: Colors.blue,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                            ),
                          ],
                        )
                      ]),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
