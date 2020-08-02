### Running the application by maven

`mvn spring-boot:run` 

and go to a browser at [http://localhost:8080](http://localhost:8080).

### Deploying and running the application by docker

`mvn clean install`

`docker build -t cubeia-test:1.0 .`

`docker run -p 8080:8080 -t cubeia-test:1.0`

and go to a browser at [http://localhost:8080](http://localhost:8080).