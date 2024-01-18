import 'package:flutter/material.dart';

class UserDataView extends StatefulWidget {
  String userName, userEmail, userDni;
  void Function()? onTap;

  UserDataView(
    this.userName,
    this.userEmail,
    this.userDni, {
    super.key,
  });

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
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Icon(
                      Icons.person_pin,
                      size: 70,
                    ),
                    const SizedBox(
                      height: 40,
                    ),
                    Text(
                      widget.userName,
                      style: const TextStyle(
                        fontSize: 26,
                      ),
                    ),
                    const SizedBox(
                      height: 40,
                    ),
                    Text(
                      widget.userEmail,
                      style: const TextStyle(
                        fontSize: 20,
                      ),
                    ),
                    const SizedBox(
                      height: 40,
                    ),
                    Text(
                      widget.userDni,
                      style: const TextStyle(
                        fontSize: 26,
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
