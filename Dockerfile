FROM openjdk:11
VOLUME /tmp
EXPOSE 8090
ADD ./target/zuul-gateway-1.0.0-SNAPSHOT.jar zuul-gateway.jar
ENTRYPOINT ["java", "-jar", "/zuul-gateway.jar"]