FROM openjdk:8

LABEL description="Imagen de compilacion de tutumback" \
  version="1.0.0" \
  maintainer="Efrain Hernandez <chernanf@nttdata.com>"

WORKDIR /app

COPY target/TutumBack-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","app.jar"]


#ENTRYPOINT ["bin/bash"]