FROM openjdk:8
LABEL matainer="alex.charco"
EXPOSE 8080
ADD target/spring-test-v1.jar app.jar
ENTRYPOINT [ "java","-jar","app.jar" ]