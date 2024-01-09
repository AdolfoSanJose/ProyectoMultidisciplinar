# Enfoque del sistema de mensajería
Los mensajes enviados se alojan en una tabla Mensajes con los siguientes campos:

## Tabla mensajes

**Remitente** - texto: *emisor del mensaje*

**Destinatario** - texto: *receptor del mensaje*

**Asunto** - texto: *resumen del contenido*

**Fecha-Hora** - texto/date (según convenga)

**Contenido** - texto largo: *el cuerpo del mensaje*

---

Al enviar el mensaje desde la app, se hace un **POST** con la API para que aloje un nuevo registro en la tabla Mensajes. Adicionalmente, se podría intentar implementar que se envíe un correo electrónico genérico que avise a un usuario si ha recibido un mensaje cuando este es enviado.

## Bandeja de entrada y salida

Se haría un GET al iniciar sesión y quizá al cambiar entre vistas: para la bandeja de entrada se cogen los mensajes donde tú eres el destinatario, mientras que en la de salida aquellos en los que eres el remitente, siendo listados en sus respectivas bandejas con la posibilidad de eliminar los de la bandeja de entrada haciendo un **DELETE**.