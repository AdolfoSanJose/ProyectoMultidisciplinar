import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_1/pages/login_or_register_page.dart';
import 'package:flutter_application_1/pages/sessionViews/user_data_view.dart';
import 'package:flutter_application_1/controllers/user.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_application_1/pages/sessionViews/file_sharing_page.dart';
import 'package:flutter_application_1/pages/sessionViews/messages_view.dart';

// Menú base para la barra de navegación al iniciar sesión en la aplicación
class MainScreen extends StatefulWidget {
  final String email;
  const MainScreen(this.email, {super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int selectedIndex = 0;
  User loggedUser = User(name: null);
  String userName = "";
  String userEmail = "";
  String userDni = "";

  @override
  void initState() {
    super.initState();
    initializeUserData(widget.email);
  }

  Future<void> initializeUserData(String email) async {
    try {
      await getUserData(email);
    } catch (error) {
      print('Error fetching user data: $error');
    }
  }

  Future<void> getUserData(String email) async {
    String baseUrl = getBaseUrl();
    Uri url = Uri.parse("$baseUrl/user/getUserDataByEmail");
    var res = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: json.encode({'email': email}), // Use the parameter email here
    );
    if (res.statusCode == 200) {
      String body = utf8.decode(res.bodyBytes);

      final jsonData = jsonDecode(body);

      setState(() {
        userName = jsonData["name"];
        userDni = jsonData["dni"];
        userEmail = jsonData["email"];
      });

      Navigator.of(context).pushReplacement(
        MaterialPageRoute(builder: (_) => FileSharingPage(userName)),
      );
    } else {
      print('Failed to fetch user data. Status code: ${res.statusCode}');
    }
  }

  String getBaseUrl() {
    return kIsWeb ? 'http://localhost:8080' : 'http://10.0.2.2:8080';
  }

  @override
  Widget build(BuildContext context) {
    // Al meter en esta lista las vistas, aparecerán en ese mismo orden en el menú de navegación
    final screens = [
      FileSharingPage(userName),
      const MessagesView(),
      UserDataView(userName, userEmail, userDni),
    ];
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "HospitApp",
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
            icon: const Icon(
              Icons.logout_outlined,
              size: 35,
              color: Colors.black,
            ),
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
            label: userName,
            backgroundColor: Colors.blue.shade700,
          ),
          // <----AÑADE AQUÍ TANTOS BottomNavigationBarItem como vistas añadidas a la lista 'screens'
          // BottomNavigationBarItem(
          //   icon: const Icon(Icons.file_open_outlined),
          //   activeIcon: const Icon(Icons.file_open_rounded),
          //   label: 'Logs',
          //   backgroundColor: Colors.blue.shade700,
          // ),
        ],
      ),
    );
  }
}
