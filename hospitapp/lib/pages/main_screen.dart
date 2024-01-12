import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_application_1/pages/login_or_register_page.dart';
import 'package:flutter_application_1/pages/sessionViews/messages_view.dart';
import 'package:flutter_application_1/pages/sessionViews/user_data_view.dart';
import 'package:flutter_application_1/controllers/user.dart';
import 'package:http/http.dart' as http;

import 'sessionViews/file_sharing_page.dart';

// Menú base para la barra de navegación al iniciar sesión en la aplicación
class MainScreen extends StatefulWidget {
  MainScreen(String? email);

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int selectedIndex = 0;
  User loggedUser = User();
  String userName = "";
  String userEmail = "";
  String userDni = "";
  Uri urlUserDataByEmail =
      Uri.parse("http://10.0.2.2:8080/user/getUserDataByEmail");

  @override
  void initState() {
    super.initState();
    getUserData('example@gmail.com');
  }

  Future<void> getUserData(email) async {
    var res = await http.post(
      urlUserDataByEmail,
      headers: {'Content-Type': 'application/json'},
      body: json.encode({'email': email}),
    );
    if (res.statusCode == 200) {
      String body = utf8.decode(res.bodyBytes);

      final jsonData = jsonDecode(body);

      userName = jsonData["name"];
      userDni = jsonData["dni"];
      userEmail = jsonData["email"];
    }
  }

  @override
  Widget build(BuildContext context) {
    // Al meter en esta lista las vistas, aparecerán en ese mismo orden en el menú de navegación
    final screens = [
      FileSharingPage(),
      MessagesView(),
      UserDataView(
        userName: userName,
        userEmail: userEmail,
        userDni: userDni,
      ),
    ];
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
        // Comento esta línea provisionalmente, posiblemente se elimine por completo
        actions: [
          IconButton(
            onPressed: () {
              showDialog(
                context: context,
                builder: (ctx) => AlertDialog(
                  title: const Text("Cerrar sesión"),
                  content: const Text("¿Desea cerrar sesión"),
                  actions: <Widget>[
                    TextButton(
                        onPressed: () {
                          Navigator.of(ctx).pop();
                        },
                        child: const Text('No')),
                    TextButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => const LoginOrRegisterPage(),
                            ),
                          );
                        },
                        child: const Text('Sí')),
                  ],
                ),
              );
            },
            icon: Icon(Icons.logout),
          )
        ],
      ),
      body: IndexedStack(
        index: selectedIndex,
        children: screens,
      ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.shifting,
        currentIndex: selectedIndex,
        onTap: (value) {
          setState(() {
            selectedIndex = value;
          });
        },
        items: [
          BottomNavigationBarItem(
            icon: const Icon(Icons.folder_outlined),
            activeIcon: const Icon(Icons.folder_rounded),
            label: 'Archivos',
            backgroundColor: Colors.blue.shade700,
          ),
          BottomNavigationBarItem(
            icon: const Icon(Icons.message_outlined),
            activeIcon: const Icon(Icons.message_rounded),
            label: 'Mensajes',
            backgroundColor: Colors.blue.shade700,
          ),
          BottomNavigationBarItem(
            icon: const Icon(Icons.person_outline),
            activeIcon: const Icon(Icons.person_rounded),
            label: '{UserName}',
            backgroundColor: Colors.blue.shade700,
          ),
          // <----AÑADE AQUÍ TANTOS BottomNavigationBarItem como vistas añadidas a la lista 'screens'
          // BottomNavigationBarItem(
          //   icon: const Icon(Icons.person_outline),
          //   activeIcon: const Icon(Icons.person_rounded),
          //   label: 'SampleView',
          //   backgroundColor: Colors.blue.shade700,
          // ),
        ],
      ),
    );
  }
}
