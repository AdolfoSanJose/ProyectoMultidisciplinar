import 'package:flutter/material.dart';
import 'package:flutter_application_1/controllers/user.dart';

class UserDataView extends StatefulWidget {
  void Function()? onTap;
  String userName, userEmail, userDni;
  UserDataView(
      {super.key,
      required this.userName,
      required this.userEmail,
      required this.userDni});

  @override
  State<UserDataView> createState() => _UserDataViewState();
}

class _UserDataViewState extends State<UserDataView> {
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
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                height: 500,
                width: 350,
                decoration: BoxDecoration(
                  color: const Color.fromARGB(37, 0, 134, 243),
                  borderRadius: BorderRadius.circular(20),
                ),
                child: const Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(
                      Icons.person_pin,
                      size: 70,
                    ),
                    SizedBox(
                      height: 40,
                    ),
                    Text(
                      '{UserName}',
                      style: TextStyle(
                        fontSize: 40,
                      ),
                    ),
                    SizedBox(
                      height: 40,
                    ),
                    Text(
                      '{Correo}',
                      style: TextStyle(
                        fontSize: 40,
                      ),
                    ),
                    SizedBox(
                      height: 40,
                    ),
                    Text(
                      '{NIF/NIE}',
                      style: TextStyle(
                        fontSize: 40,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          )),
    );
  }
}
