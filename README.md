Akka Http Docker
----------------
>Akka Http server housed in docker instance.

Install
-------
1. Docker - https://docs.docker.com/get-docker/

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
>Use optional Docker Dashboard as required. Image name is: akka-http-docker:0.1
1. sbt clean compile stage
2. sbt docker:stage
3. sbt docker:publishLocal
4. docker images
5. docker run -d -p 7979:7979 akka-http-docker:0.1
6. docker ps
7. curl --request GET http://localhost:7979
8. docker stop akka-http-docker:0.1

Docker Notes
------------
1. list images - docker images
2. remove image - docker image rm <image-id> --force
3. list running images - docker ps

Reference
---------
1. https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/