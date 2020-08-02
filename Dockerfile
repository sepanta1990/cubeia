FROM java:8
EXPOSE 8080
ADD /target/cubeia-test.jar cubeia-test.jar
ENTRYPOINT ["java","-jar","cubeia-test.jar"]