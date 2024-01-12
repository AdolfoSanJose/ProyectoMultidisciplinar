class Roles {
  int? idRol;

  Roles({this.idRol});

  factory Roles.fromJson(Map<String, dynamic> json) {
    return Roles(
      idRol: json['id_rol'],
    );
  }
}
