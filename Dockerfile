FROM openjdk:8-jre-alpine
Copy app.jar .
EXPOSE 8080
CMD ["java" ,"-jar" ,"app.jar"]
