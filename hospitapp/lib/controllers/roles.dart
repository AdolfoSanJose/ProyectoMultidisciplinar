class Roles {
  late int? idRol;

  Roles({required this.idRol});

  factory Roles.fromJson(Map<String?, dynamic> json) {
    return Roles(
      idRol: json?['id_rol'],
    );
  }
}
