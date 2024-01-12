import 'package:flutter/material.dart';

class FileSharingPage extends StatefulWidget {
  void Function()? onTap;
  FileSharingPage({super.key});

  @override
  State<FileSharingPage> createState() => _FileSharingPageState();
}

class _FileSharingPageState extends State<FileSharingPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
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
                const SizedBox(height: 50),

                Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        ElevatedButton(
                            style: ElevatedButton.styleFrom(
                                textStyle: const TextStyle(
                                  fontSize: 20,
                                ),
                                backgroundColor: Colors.lightBlue),
                            onPressed: () => {},
                            child: const Text('Subir archivo')),
                        const SizedBox(
                          width: 10,
                        ),
                        ElevatedButton(
                            style: ElevatedButton.styleFrom(
                                textStyle: const TextStyle(
                                  fontSize: 20,
                                ),
                                backgroundColor: Colors.lightBlue),
                            onPressed: () => {},
                            child: const Text('Crear directorio'))
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
                    borderRadius: BorderRadius.circular(20),
                  ),
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
