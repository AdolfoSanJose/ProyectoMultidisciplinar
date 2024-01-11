import 'package:flutter/material.dart';

class FileSharingPage extends StatefulWidget {
  void Function()? onTap;
  FileSharingPage({super.key});

  @override
  State<FileSharingPage> createState() => _FileSharingPageState();
}

class _FileSharingPageState extends State<FileSharingPage> {
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
        actions: [IconButton(onPressed: () => {}, icon: Icon(Icons.logout))],
      ),
      body: Container(
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
                const Text(
                  '{nombreUsuario}', // Aquí podría ir dinámicamente el nombre del usuario
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 15,
                  ),
                ),

                const SizedBox(height: 50),

                Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        ElevatedButton(
                            onPressed: () => {}, child: Text('Subir archivo'))
                      ],
                    ),
                  ],
                ),

                // Files zone
                Container(
                  height: 500,
                  width: 350,
                  decoration: BoxDecoration(
                      color: const Color.fromARGB(37, 0, 134, 243),
                      borderRadius: BorderRadius.circular(20)),
                  child: Column(children: []),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
