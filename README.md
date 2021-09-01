Akka Http Docker Kubernetes
---------------------------
>Akka Http server in Docker image and container, optionally deployed with Kubernetes.

Install
-------
1. Docker - https://docs.docker.com/get-docker/
2. Microsoft VSCode Docker ( a more functional alternative to Docker Dashboard! )
3. Kubectl - https://kubernetes.io/docs/tasks/tools/
4. Minicube - https://kubernetes.io/docs/tasks/tools/

Build
-----
1. sbt clean compile

Run
---
1. sbt run

Curl
----
1. curl http://localhost:7979

Docker
------
1. sbt clean compile stage
2. sbt docker:stage  ( see Dockerfile in target/docker/stage/ )
3. sbt docker:publishLocal
4. docker images
5. docker run --rm -it -d -p 7979:7979/tcp akka-http-docker:0.1
6. docker ps
7. docker exec -it container-id /bin/bash
   * curl http://localhost:7979  (via docker container )
8. curl http://localhost:7979 ( via localhost )
9. docker stop container-id

Docker Script
-------------
1. target/universal/stage/bin/akka-http-docker
2. curl http://localhost:7979

Docker Notes
------------
1. list images - docker images
2. remove image - docker image rm image-id --force
3. list running containers - docker ps
4. logs - docker logs container-id

Docker Push
-----------
>To push akka-http-docker:0.1 to your DockerHub use one of these options:
1. Docker Dashboard
2. Microsoft VSCode Docker
3. sbt -Ddocker.username=user-name -Ddocker.registry=registry-url docker:publish

Kubernetes
----------
1. sbt kubeyml:gen ( see target/kubeyml/deployment.yml )

Links
-----
1. https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/
2. https://faun.pub/docker-wonderland-akka-http-server-and-postgres-db-962b971ff28a
