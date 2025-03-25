# Hyperion by codbex

[![Build Status](https://github.com/codbex/codbex-hyperion/actions/workflows/build.yaml/badge.svg)](https://github.com/codbex/codbex-hyperion/actions/workflows/build.yaml)
[![Eclipse License](https://img.shields.io/badge/License-EPL%202.0-brightgreen.svg)](https://github.com/codbex/codbex-hyperion/blob/main/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.codbex.hyperion/codbex-hyperion-application.svg)](https://central.sonatype.com/namespace/com.codbex.hyperion)

Hyperion Edition provides the Business Processes modeling, management and operation components.

It is good for exploration BPMN scenarios based on Flowable framework.

<!-- TOC -->

* [codbex-hyperion](#codbex-hyperion)
    * [Run steps](#run-steps)
        * [Start using Docker and released image](#start-using-docker-and-released-image)
        * [Start using Docker and local sources](#start-using-docker-and-local-sources)
            * [Build the project jar](#build-the-project-jar)
            * [Build and run docker image locally](#build-and-run-docker-image-locally)
        * [Java standalone application](#java-standalone-application)
            * [Start the application](#start-the-application)
            * [Start the application **in debug** with debug port
              `8000`](#start-the-application-in-debug-with-debug-port-8000)
        * [Run unit tests](#run-unit-tests)
        * [Run integration tests](#run-integration-tests)
        * [Run all tests](#run-all-tests)
        * [Format the code](#format-the-code)
    * [Access the application](#access-the-application)
    * [REST API](#rest-api)
        * [Quick start](#quick-start)

<!-- TOC -->

## Run steps

__Prerequisites:__

- Export the following variables before executing the steps
  ```shell
  export GIT_REPO_FOLDER='<path-to-the-git-repo>'
  export IMAGE='ghcr.io/codbex/codbex-hyperion:latest'
  export CONTAINER_NAME='hyperion'
  ```

### Start using Docker and released image

```shell
# optionally remove the existing container with that name
docker rm -f "$CONTAINER_NAME"
docker pull "$IMAGE"

docker run --name "$CONTAINER_NAME" -p 80:80 "$IMAGE"
```

---

### Start using Docker and local sources

#### Build the project jar

```shell
cd $GIT_REPO_FOLDER
mvn -T 1C clean install -P quick-build
```

#### Build and run docker image locally

__Prerequisites:__ [Build the project jar](#build-the-project-jar)

  ```shell
  cd "$GIT_REPO_FOLDER/application"
  
  docker build . --tag "$IMAGE"
  
  # optionally remove the existing container with that name
  docker rm -f "$CONTAINER_NAME"

  docker run --name "$CONTAINER_NAME" -p 80:80 "$IMAGE"
  ```

--- 

### Java standalone application

__Prerequisites:__ [Build the project jar](#build-the-project-jar)

#### Start the application

```shell
cd "$GIT_REPO_FOLDER"

java \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    --add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
    --add-opens=java.base/java.nio=ALL-UNNAMED \
    -jar application/target/codbex-hyperion-*.jar
```

#### Start the application **in debug** with debug port `8000`

```shell
cd "$GIT_REPO_FOLDER"

export PHOEBE_AIRFLOW_WORK_DIR="$AIRFLOW_WORK_DIR"
java \
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    --add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
    --add-opens=java.base/java.nio=ALL-UNNAMED \
    -jar application/target/codbex-hyperion-*.jar
```

---

### Run unit tests

```shell
cd "$GIT_REPO_FOLDER"
mvn clean install -P unit-tests
```

---

### Run integration tests

```shell
cd "$GIT_REPO_FOLDER"
mvn clean install -P integration-tests
```

---

### Run all tests

```shell
cd "$GIT_REPO_FOLDER"
mvn clean install -P tests
```

---

### Format the code

```shell
cd "$GIT_REPO_FOLDER"
mvn verify -P format
```

---

## Access the application

- Open URL [http://localhost:80](http://localhost:80)
- Login with the default credentials username `admin` and password `admin`

## REST API

```
http://localhost/swagger-ui/index.html
```

### Quick start

To quickly create a new project, follow the steps bellow:

- Start the application by following the steps in [Build and Run](#build-and-run)
- Select `BPM Project Starter` template from the `Welcome` view
  ![bpm-starter-template](misc/images/bpm-starter-template.png)
- add the required template params
  ![bpm-template-params](misc/images/bpm-template-params.png)
- Click on `Ok` button
- Your first project is generated
  ![bpm-generated-project](misc/images/bpm-generated-project.png)
- Generate a form for process triggering
    - Open `trigger-new-process.form` file
    - Click on `Regenerate` button
      ![bpm-generate-form](misc/images/bpm-generate-form.png)
- Now you can test your first process
    - Refresh the workspace by clicking on `Refresh` button
    - Navigate to the generated form and open the `Preview` tab
      ![bpm-trigger-form](misc/images/bpm-trigger-form.png)
    - Add process params and click on `Trigger` button
      ![bpm-trigger-process-instance](misc/images/bpm-trigger-process-instance.png)
    - Now, your process is triggered
    - You can check the logs for a message from the default process service task
      ![bpm-task-log](misc/images/bpm-task-log.png)
- Next steps are to modify the process to fit your needs
