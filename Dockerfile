FROM depauna/maven:latest as builder
ADD . employee-management/
WORKDIR employee-management
RUN mvn package
RUN mv target/spring-boot-natand*.jar /employee-management.jar

FROM depauna/jdk8:latest
COPY --from=builder /employee-management.jar .

ENTRYPOINT ["java","-jar","/employee-management.jar"]
