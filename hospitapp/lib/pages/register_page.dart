import 'package:flutter/material.dart';
import '../components/my_textfield.dart';
import '../components/my_button.dart';

const List<String> list = <String>['Medico', 'Paciente'];

class RegisterPage extends StatefulWidget {
  void Function()? onTap;
  RegisterPage({super.key, required this.onTap});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  // Text controllers
  final nameController = TextEditingController();
  final mailController = TextEditingController();
  final passwdController = TextEditingController();
  final confirmPasswdController = TextEditingController();

  String dropdownValue = list.first;

  // Sign up method
  void signUp() {}

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

                  //  icon
                  Icon(
                    Icons.app_registration_rounded,
                    size: 60,
                    color: Colors.blue.shade700,
                  ),

                  const SizedBox(height: 30),

                  // Create account text
                  const Text(
                    '¡Crea una cuenta!',
                    style: TextStyle(
                      color: Colors.black,
                      fontSize: 15,
                    ),
                  ),

                  const SizedBox(height: 15),

                  // Log in container
                  Container(
                    height: 625,
                    width: 350,
                    decoration: BoxDecoration(
                        color: const Color.fromARGB(37, 0, 134, 243),
                        borderRadius: BorderRadius.circular(20)),
                    child: Column(children: [
                      const SizedBox(height: 40),

                      // Nombre
                      MyTextField(
                        controller: nameController,
                        hintText: 'Nombre',
                        obscureText: false,
                      ),

                      const SizedBox(height: 15),

                      // Dropdown
                      Row(
                        children: [
                          DropdownButton<String>(
                            value: dropdownValue,
                            icon: const Icon(Icons.arrow_downward_outlined),
                            iconSize: 25,
                            elevation: 16,
                            style: const TextStyle(color: Colors.black),
                            padding: const EdgeInsets.only(left: 30),
                            iconEnabledColor: Colors.blueAccent,
                            underline: Container(
                              height: 2,
                              color: Colors.blue,
                            ),
                            onChanged: (String? value) {
                              // This is called when the user selects an item.
                              setState(() {
                                dropdownValue = value!;
                              });
                            },
                            items: list
                                .map<DropdownMenuItem<String>>((String value) {
                              return DropdownMenuItem<String>(
                                value: value,
                                child: Text(value),
                              );
                            }).toList(),
                          ),
                        ],
                      ),

                      const SizedBox(height: 15),

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

                      const SizedBox(height: 15),

                      // Confirm Password
                      MyTextField(
                        controller: confirmPasswdController,
                        hintText: 'Confirmar Contraseña',
                        obscureText: true,
                      ),

                      const SizedBox(height: 25),

                      // Sign up button
                      MyButton(
                        onTap: signUp,
                        text: 'Crear Cuenta',
                      ),

                      const SizedBox(height: 25),

                      // Register
                      const Padding(
                        padding: EdgeInsets.symmetric(horizontal: 20),
                        child: Divider(
                          thickness: 1,
                          color: Colors.blueGrey,
                        ),
                      ),

                      const SizedBox(height: 30),

                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          const Text('¿Ya tienes cuenta?'),
                          const SizedBox(width: 5),
                          GestureDetector(
                            onTap: widget.onTap,
                            child: const Text(
                              'Inicia sesión ahora',
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
