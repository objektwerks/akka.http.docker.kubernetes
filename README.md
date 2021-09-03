Akka Http Docker Kubernetes
---------------------------
>Akka Http server built as Docker image, deployable to Docker and Kubernetes via Minikube.

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
5. docker run --rm -it -d -p 7979:7979/tcp akka-http-server:0.1
6. docker ps
7. docker exec -it container-id /bin/bash
   * curl http://localhost:7979  (via docker container )
8. curl http://localhost:7979 ( via localhost )
9. docker stop container-id

Docker Commands
---------------
1. list images - docker images
2. remove image - docker image rm image-id --force
3. list containers - docker ps
4. logs - docker logs container-id

Docker Push
-----------
>To push akka-http-server:0.1 to your DockerHub use one of these options:
1. Docker Dashboard
2. Microsoft VSCode Docker
3. sbt -Ddocker.username=user-name -Ddocker.registry=registry-url docker:publish

Kubernetes
----------
1. sbt clean compile stage
2. sbt docker:clean  ( optional, throws exception if no local image in repository )
3. sbt docker:stage  ( see Dockerfile in target/docker/stage/ )
4. sbt docker:publishLocal
5. see Docker Push section above to publish to Docker Hub
6. sbt kubeyml:gen ( see target/kubeyml/deployment.yml )
7. minikube start | minikube status
8. minikube dashboard  ( CTRL-C to stop )
9. Verify deployment of akka-http-server via Minikube Dashboard.
   * kubectl create -f ./target/kubeyml/deployment.yml ( if akka-http-server is not deployed )
10. minikube ip  ( insert ip in curl url in next step )
11. curl http://192.168.49.2:7979
12. minikube stop | minikube status

Resources
---------
>Docker
1. https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/
2. https://faun.pub/docker-wonderland-akka-http-server-and-postgres-db-962b971ff28a
>Kubernetes
1. Kubectl - https://kubernetes.io/docs/tutorials/kubernetes-basics/
2. Minikube - https://minikube.sigs.k8s.io/docs/
3. sbt-kubeyml - https://sbt-kubeyml.vaslabs.org
4. Tutorial - https://yzhong-cs.medium.com/getting-started-with-kubernetes-and-docker-with-minikube-b413d4deeb92