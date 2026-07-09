FROM eclipse-temurin:21-jdk-alpine

# 2. Directorio de trabajo interno
WORKDIR /app

# 3. Copiamos tu archivo .jar de build/libs y lo renombramos como app.jar adentro
COPY build/libs/pnc-parcial-final-restaurante-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponer el puerto de Spring Boot
EXPOSE 8080

# 5. Comando para ejecutar la app apuntando exactamente a app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
