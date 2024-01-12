import 'package:flutter_application_1/controllers/roles.dart';

class User {
  late int? idUser;
  late String? name;
  late String? email;
  late String? password;
  late String? dni;
  late Roles? idRol;
  late User? medico;

  User({
    required this.idUser,
    required this.name,
    required this.email,
    required this.password,
    required this.dni,
    required this.idRol,
    this.medico,
  });

  factory User.fromJson(Map<String?, dynamic>? json) {
    return User(
      idUser: json?['id_usuario'],
      name: json?['nombre'],
      email: json?['correo'],
      password: json?['contrasegna'],
      dni: json?['dni'],
      idRol: Roles?.fromJson(json?['id_rol']),
      medico: json?['medico'] != null ? User.fromJson(json?['medico']) : null,
    );
  }
}
