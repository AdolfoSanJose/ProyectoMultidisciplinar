import 'package:flutter/material.dart';
import 'package:ftpconnect/ftpconnect.dart';
import 'dart:io';
import 'package:file_picker/file_picker.dart';

class FileSharingPage extends StatefulWidget {
  final String userName;

  void Function()? onTap;
  FileSharingPage(this.userName, {super.key});

  @override
  State<FileSharingPage> createState() => _FileSharingPageState();
}

class _FileSharingPageState extends State<FileSharingPage> {
  // FTP Service attributes
  final String ftpHost = '192.168.56.1';
  final String ftpUser = 'Gisela';
  final String ftpPassword = 'medicoGisela';
  final int ftpPort = 21;

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
              mainAxisAlignment:
                  MainAxisAlignment.start, // Align text to the top
              children: [
                const SizedBox(height: 20), // Adjusted height

                // Bienvenido! text
                Text(
                  '¡Bienvenido, ${widget.userName}!',
                  style: const TextStyle(
                    fontSize: 26,
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                  ),
                ),

                // Files zone
                Container(
                  height: 220,
                  width: double.infinity,
                  color: const Color.fromARGB(37, 0, 134, 243),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      _buildClickableSquare(
                        "Subir archivo",
                        Icons.upload,
                        ftpHost,
                        ftpUser,
                        ftpPassword,
                        ftpPort,
                      ),
                      _buildSquare("Bajar archivo", Icons.download),
                    ],
                  ),
                ),
                const SizedBox(height: 10),
                Container(
                  height: 220,
                  width: double.infinity,
                  color: const Color.fromARGB(37, 0, 134, 243),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      _buildSquare("Modificar archivo", Icons.edit),
                      _buildSquare("Eliminar archivo", Icons.delete),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildSquare(String text, IconData icon) {
    return Container(
      width: 150,
      height: 150,
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border.all(color: Colors.lightBlue, width: 2),
        borderRadius: BorderRadius.circular(20),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(
            icon,
            size: 50,
            color: Colors.lightBlue,
          ),
          const SizedBox(height: 10),
          Text(
            text,
            style: TextStyle(color: Colors.lightBlue),
          ),
        ],
      ),
    );
  }

  Widget _buildClickableSquare(
    String text,
    IconData icon,
    String host,
    String user,
    String password,
    int port,
  ) {
    return GestureDetector(
      onTap: () async {
        FilePickerResult? result = await FilePicker.platform.pickFiles();
        if (result != null) {
          if (result.files.single.bytes != null) {
            List<int> fileBytes = result.files.single.bytes!;
            File file =
                await File(result.files.single.name).writeAsBytes(fileBytes);
            FTPConnect ftpConnect = FTPConnect(
              host,
              user: user,
              pass: password,
              port: port,
            );

            await ftpConnect.connect();
            bool res =
                await ftpConnect.uploadFileWithRetry(file, pRetryCount: 2);
            await ftpConnect.disconnect();
            print(res);
          }
        } else {
          // User canceled the picker
        }
      },
      child: _buildSquare(text, icon),
    );
  }
}
