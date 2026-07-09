Herramienta de IA usada (ChatGPT, Claude, Copilot, etc.).
El prompt exacto (o un resumen fiel si fue muy largo).
Qué generó la IA (resumen).
Qué tuvieron que corregir, rechazar o completar manualmente, y por qué.

1.
Gemini
Para la parte de usuario y rol , para manejar el caso de id autoicrementales se usa el identity asi como lo indique en las clases verdad?
La IA basicamente dijo que si:Es correcto. En JPA (Java Persistence API) con Spring Boot, para manejar identificadores autoincrementables generados directamente por la base de datos (como SERIAL en PostgreSQL o AUTO_INCREMENT en MySQL), se utiliza la estrategia GenerationType.IDENTITY.
No corregi nada , solo era para recordarme

Gemini
Para la parte de servicios y repositorios le pedi que hiciera algo generico para modificar algo ya hecho
La IA se salto varias cosas y me toco corregir e implementar la logica de servicio , por ejemplo hice componentes para simplicar la logica de negocio con el preAuthorize en el controllodor , cosa que hice en mi proyecto

Gemini
Le pedi el ci-cd y me toco modificarle cosas que tienen que ver con el .env

Gemini
Le pedi que me hiciera los mappers y dtos de cada entidad
En los dtos request en algunos no servia para implementar la logica de negocio 