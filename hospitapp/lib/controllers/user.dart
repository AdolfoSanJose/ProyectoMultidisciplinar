import 'package:flutter_application_1/controllers/roles.dart';

class User {
  int? idUser;
  String? name;
  String? email;
  String? password;
  String? dni;
  Roles? idRol;
  User? medico;

  User({
    this.idUser,
    required this.name,
    this.email,
    this.password,
    this.dni,
    this.idRol,
    this.medico,
  });

  @override
  String toString() {
    return 'User{id: $idUser, name: $name}';
  }

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      idUser: json['id_usuario'],
      name: json['nombre'],
      email: json['correo'],
      password: json['contrasegna'],
      dni: json['dni'],
      idRol: json['id_rol'],
      medico: json['medico'],
    );
  }
}
