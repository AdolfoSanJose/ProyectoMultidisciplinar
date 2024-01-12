import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_1/controllers/roles.dart';
import 'package:flutter_application_1/controllers/user.dart';
import 'package:flutter_application_1/pages/login_or_register_page.dart';
import '../components/my_textfield.dart';
import '../components/my_button.dart';
import 'package:http/http.dart' as http;

// const List<String> list = <String>['Selecciona a tu médico', 'Artem'];

class RegisterPage extends StatefulWidget {
  void Function()? onTap;
  RegisterPage({super.key, required this.onTap});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  // Text controllers
  final nameController = TextEditingController();
  final dniController = TextEditingController();
  final mailController = TextEditingController();
  final passwdController = TextEditingController();
  final confirmPasswdController = TextEditingController();

  // User List
  List<dynamic> userList = [];
  String selectedUserId = '';

  final formKey = GlobalKey<FormState>();
  User user = User(name: "");
  Uri urlRegister = Uri.parse("http://10.0.2.2:8080/user/register");
  Uri urlUserDataByRole =
      Uri.parse("http://10.0.2.2:8080/user/getUserDataByRole");

  // @override
  // void initState() {
  //   super.initState();
  //   getUserDataByRole(Roles(idRol: 1));
  // }

  // Future<void> getUserDataByRole(Roles idRol) async {
  //   try {
  //     var res = await http.post(
  //       urlUserDataByRole,
  //       headers: {'Content-Type': 'application/json'},
  //       body: json.encode(
  //         {'idRol': idRol.idRol},
  //       ),
  //     );

  //     print(idRol.idRol);

  //     if (res.statusCode == 200) {
  //       print(res.body);
  //       final List<dynamic>? jsonData = json.decode(res.body);
  //       print(jsonData?[0]);

  //       if (jsonData != null) {
  //         List<User> users =
  //             jsonData.map<User>((json) => User.fromJson(json)).toList();
  //         print(users[1].name);
  //         setState(() {
  //           userList = users;
  //           print(userList[1]);
  //         });
  //       } else {
  //         print('Error: La respuesta no es una lista válida.');
  //       }
  //     } else {
  //       print('Error al obtener usuarios. Código de estado: ${res.statusCode}');
  //       print('Cuerpo de la respuesta: ${res.body}');
  //     }
  //   } catch (error) {
  //     print('Error en getUsers: $error');
  //   }
  // }

  String getBaseUrl() {
    return kIsWeb ? 'http://localhost:8080' : 'http://10.0.2.2:8080';
  }
  // String dropdownValue = list.first;

//https://www.youtube.com/watch?v=u4qmtlrXQNg
  // Sign up method
  Future signUp() async {
    String baseUrl = getBaseUrl();
    Uri url = Uri.parse("$baseUrl/user/register");
    try {
      user.name = nameController.text;
      user.dni = dniController.text;
      user.email = mailController.text;
      user.password = passwdController.text;
      if (user.password != confirmPasswdController.text) {
        openDialog("Las contraseñas no coinciden.", Colors.red);
      } else {
        var res = await http.post(url,
            headers: {'Content-Type': 'application/json'},
            body: json.encode({
              'name': user.name,
              'email': user.email,
              'password': user.password,
              'dni': user.dni,
              'id_rol': user.dni,
            }));
        print(res.body);
        if (res.statusCode == 200) {
          openDialog("¡Cuenta creada con éxito!", Colors.greenAccent);
          return Navigator.of(context).pushReplacement(
              MaterialPageRoute(builder: (_) => const LoginOrRegisterPage()));
        } else {
          print("Credenciales incorrectas.");
        }
      }
    } catch (error) {
      print("Error in signIn: $error");
    }
  }

  // Error Dialog
  Future openDialog(String text, Color color) => showDialog(
      context: context,
      builder: (context) => AlertDialog(
            title: Text(text),
            titleTextStyle: TextStyle(
              color: color,
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
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const SizedBox(height: 15),

                  //  icon
                  Icon(
                    Icons.app_registration_rounded,
                    size: 35,
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
                    height: 700,
                    width: 350,
                    decoration: BoxDecoration(
                        color: const Color.fromARGB(37, 0, 134, 243),
                        borderRadius: BorderRadius.circular(20)),
                    child: Column(children: [
                      const SizedBox(height: 30),

                      // Nombre
                      MyTextField(
                        controller: nameController,
                        hintText: 'Nombre',
                        obscureText: false,
                        errorText: 'Campo nombre no puede estar vacío',
                      ),

                      const SizedBox(height: 15),

                      MyTextField(
                        controller: dniController,
                        hintText: 'DNI',
                        obscureText: false,
                        errorText: 'Campo DNI no puede estar vacío',
                      ),

                      const SizedBox(height: 15),

                      // Dropdown
                      // Row(
                      //   children: [
                      //     DropdownButton<String>(
                      //       hint: const Text("Selecciona a tu médico"),
                      //       value: selectedUserId,
                      //       icon: const Icon(Icons.arrow_downward_outlined),
                      //       iconSize: 25,
                      //       elevation: 16,
                      //       style: const TextStyle(color: Colors.black),
                      //       padding: const EdgeInsets.only(left: 30),
                      //       iconEnabledColor: Colors.blueAccent,
                      //       underline: Container(
                      //         height: 2,
                      //         color: Colors.blue,
                      //       ),
                      //       onChanged: (String? newValue) {
                      //         setState(() {
                      //           selectedUserId = newValue!;
                      //         });
                      //       },
                      //       items: (userList as List<User>)
                      //           .map<DropdownMenuItem<String>>((User user) {
                      //         return DropdownMenuItem<String>(
                      //           value: user.idUser.toString(),
                      //           child: Text(""),
                      //         );
                      //       }).toList(),
                      //     ),
                      //   ],
                      // ),

                      const SizedBox(height: 15),

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

                      const SizedBox(height: 15),

                      // Confirm Password
                      MyTextField(
                        controller: confirmPasswdController,
                        hintText: 'Confirmar Contraseña',
                        obscureText: true,
                        errorText:
                            'Campo repetir contraseña no puede estar vacío',
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
