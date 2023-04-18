# codbex-hyperion

Hyperion Edition provides the Business Processes modeling, management and operation components.

It is good for exploration BPMN scenarios based on Flowable framework.

#### Docker

```
docker pull ghcr.io/codbex/codbex-hyperion:latest
docker run --name codbex-hyperion --rm -p 8080:8080 ghcr.io/codbex/codbex-hyperion:latest
```

#### Build

```
mvn clean install
```
	
#### Run

```
java -jar application/target/codbex-hyperion-application-1.0.0-SNAPSHOT.jar
```

#### Debug

```
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar application/target/codbex-hyperion-application-1.0.0-SNAPSHOT.jar
```
	
#### Web

```
http://localhost:8080
```

#### REST API

```
http://localhost:8080/swagger-ui/index.html
```
