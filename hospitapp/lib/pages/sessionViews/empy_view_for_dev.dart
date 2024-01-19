import 'package:flutter/material.dart';

class EmptyViewForDev extends StatefulWidget {
  void Function()? onTap;
  EmptyViewForDev({super.key});

  @override
  State<EmptyViewForDev> createState() => _EmptyViewForDevState();
}

class _EmptyViewForDevState extends State<EmptyViewForDev> {
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
          child: null),
    );
  }
}
