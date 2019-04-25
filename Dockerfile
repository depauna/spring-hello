FROM depauna/maven:latest as builder
ADD . natand/
WORKDIR natand
RUN mvn package
RUN mv target/spring-boot-natand*.jar /natand.jar

FROM depauna/jdk8:latest
COPY --from=builder /natand.jar .

ENTRYPOINT ["java","-jar","/natand.jar"]
