FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD target/myapp.jar myapp.jar
ENTRYPOINT ["java", "-jar", "myapp.jar"]
