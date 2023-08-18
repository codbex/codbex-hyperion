# codbex-hyperion

Hyperion Edition provides the Business Processes modeling, management and operation components.

It is good for exploration BPMN scenarios based on Flowable framework.

#### Docker

```
docker pull ghcr.io/codbex/codbex-hyperion:latest
docker run --name codbex-hyperion --rm -p 80:80 ghcr.io/codbex/codbex-hyperion:latest
```

#### Build

```
mvn clean install
```
	
#### Run

```
java -jar application/target/codbex-hyperion-application-*.jar
```

#### Debug

```
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar application/target/codbex-hyperion-application-*.jar
```
	
#### Web

```
http://localhost
```

#### REST API

```
http://localhost/swagger-ui/index.html
```
