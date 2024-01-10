
# Creación de API para Flutter app
En este sprint, el objetivo principal es desarrollar una API utilizando Spring (Maven) para facilitar a los usuarios una pantalla de inicio de sesión y registro en nuestro sistema.

## API Rest
**Objetivos**
- Crear una API con Spring (Maven): Implementaremos servicios de inicio de sesión y registro que validarán la existencia del correo electrónico y la contraseña en la base de datos de usuarios.

- Integración con Flutter: Consumiremos los servicios proporcionados por la API en una aplicación Flutter. La aplicación Flutter enviará y recibirá datos para realizar la autenticación y registro de usuarios.

- Análisis de servicios: Revisaremos los servicios disponibles y evaluaremos si es necesario agregar algún servicio adicional a la API. Una vez completados los servicios esenciales de inicio de sesión y registro, nos enfocaremos en la creación de la página principal después del inicio de sesión.

## Métodos de Servicio
Utilizaremos principalmente dos métodos **HTTP**:

**POST:** Utilizado para enviar datos al servidor. En nuestro caso, se usará para el registro de nuevos usuarios y la autenticación.

**GET:** Utilizado para solicitar datos del servidor. En este contexto, se puede utilizar para verificar la existencia de usuarios o cualquier otra información necesaria para la aplicación Flutter.

## Validación de Datos
Garantizaré que no se permita la creación de cuentas con correos electrónicos que ya estén registrados en el sistema. Esto se logrará mediante la implementación adecuada en el servicio de registro.

Con estos pasos, espero establecer una sólida base para la autenticación de usuarios en nuestra aplicación Flutter a través de una API eficiente y segura, facilitando así una experiencia fluida y segura para los usuarios finales.