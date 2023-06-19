FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ADD AATransferAPI.jar AATapi.jar
EXPOSE 8080
# COPY /*.jar AATransferAPI.jar
ENTRYPOINT ["java","-jar","AATapi.jar"]
