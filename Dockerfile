FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ADD AATransferAPI.jar AATapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","AATapi.jar"]