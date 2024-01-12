import 'package:flutter/material.dart';
import 'package:flutter_application_1/pages/login_or_register_page.dart';
import 'package:flutter_application_1/pages/sessionViews/messages_view.dart';
import 'package:flutter_application_1/pages/sessionViews/user_data_view.dart';
import 'sessionViews/file_sharing_page.dart';

// Menú base para la barra de navegación al iniciar sesión en la aplicación
class MainScreen extends StatefulWidget {
  const MainScreen(String? email, {super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int selectedIndex = 0;

  @override
  Widget build(BuildContext context) {
    // Al meter en esta lista las vistas, aparecerán en ese mismo orden en el menú de navegación
    final screens = [
      FileSharingPage(),
      MessagesView(),
      UserDataView(),
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
            icon: const Icon(Icons.logout),
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
