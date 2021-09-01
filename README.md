Akka Http Docker
----------------
>Akka Http server housed in docker instance.

Install
-------
1. Docker - https://docs.docker.com/get-docker/
2. Docker for Visual Studio by Microsoft  ( much more useful than Docker Dashboard! )

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
7. docker exec -i -t <container-id> /bin/bash
8. curl http://localhost:7979
9. docker stop <container-id>

Docker Script
-------------
1. target/universal/stage/bin/akka-http-docker
2. curl http://localhost:7979

Docker Notes
------------
1. list images - docker images
2. remove image - docker image rm <image-id> --force
3. list running containers - docker ps
4. logs - docker logs <container-id>

Reference
---------
1. https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/