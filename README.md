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
1. sbt clean compile
2. sbt docker:stage
3. sbt docker:publishLocal ( view akka-http-docker image via docker dashboard )
4. docker images  ( note akka-http-docker:0.1 )
5. docker run -d -p 7979:7979 akka-http-docker:0.1  ( may have to run via docker dashboard )
6. docker ps  ( akka-http-docker:0.1 should be running )
7. curl --request GET http://localhost:7979
8. docker stop akka-http-docker:0.1  ( may have to stop via docker dashboard )

Docker Notes
------------
1. list images - docker images
2. remove image - docker image rm <image-id> --force
3. list running images - docker ps

Reference
---------
1. https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/