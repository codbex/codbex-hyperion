# codbex-hyperion-tomcat

The `codbex` hyperion `tomcat` package

To build the docker image:

    docker build -t codbex-hyperion-tomcat:latest .

To run a container:

    docker run --name codbex --rm -p 8080:8080 -p 8081:8081 codbex-hyperion-tomcat:latest

To tag the image:

    docker tag codbex-hyperion-tomcat codbex.jfrog.io/codbex-docker/codbex-hyperion-tomcat:latest

To push to JFrog Container Registry:

    docker push codbex.jfrog.io/codbex-docker/codbex-hyperion-tomcat:latest

To pull from JFrog Container Registry:

    docker pull codbex.jfrog.io/codbex-docker/codbex-hyperion-tomcat:latest
