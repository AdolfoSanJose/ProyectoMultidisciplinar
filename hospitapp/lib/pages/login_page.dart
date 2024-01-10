import 'package:flutter/material.dart';
import '../components/my_textfield.dart';
import '../components/my_button.dart';

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

  // Sign in method
  void signIn() {}

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
                      ),

                      const SizedBox(height: 15),

                      // Password
                      MyTextField(
                        controller: passwdController,
                        hintText: 'Contraseña',
                        obscureText: true,
                      ),

                      const SizedBox(height: 25),

                      // Sign in button
                      MyButton(
                        onTap: signIn,
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
    );
  }
}
