Akka Http Docker
----------------
>Akka Http server housed in docker instance.

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
2. ./target/universal/stage/bin/akka-http-docker
3. curl http://localhost:7979

Reference
---------
1. https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/