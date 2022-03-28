Akka Http Docker Kubernetes
---------------------------
>Akka-Http server built as Docker image, deployable to Docker ***and*** Kubernetes via Minikube.

Warning
-------
>Kubernetes is complex and has failed many. Consequently, the anti-Kubernetes movement is growing.
>Here are just a few articles detailing some of the issues with Kubernetes:
1. https://b.amy.gg/kubernetes-sucks
2. https://blog.tilt.dev/2019/08/21/why-does-developing-on-kubernetes-suck.html
3. https://news.ycombinator.com/item?id=26271470
>With enough money and time, though, some do succeed with Kubernetes.:)

Install
-------
1. Docker - https://docs.docker.com/get-docker/
2. Microsoft VSCode Docker ( a more functional alternative to Docker Dashboard! )
3. Kubectl - https://kubernetes.io/docs/tasks/tools/
4. Minicube - https://kubernetes.io/docs/tasks/tools/

Test
----
2. sbt clean test

Run
---
1. sbt run
2. curl http://localhost:7979/health
3. curl http://localhost:7979/now

Docker
------
1. sbt clean compile stage
2. sbt docker:stage  ( see target/docker/stage/Dockerfile )
3. sbt docker:publishLocal
4. docker images  ( note akka-http-server:0.1 listed )
5. docker run --rm -it -d -p 7979:7979/tcp akka-http-server:0.1
6. docker ps  ( note akka-http-server:0.1 listed )
7. docker exec -it container-id /bin/bash
   * curl http://localhost:7979/now  ( via docker container )
8. curl http://localhost:7979/now ( via localhost )
9. docker stop container-id  ( obtain container-id via docker ps listing )
10. docker ps  ( note akka-http-server:0.1 not listed )

Docker Commands
---------------
1. list images - docker images
2. remove image - docker image rm image-id --force
3. list containers - docker ps
4. logs - docker logs container-id

Docker Push
-----------
>To push an image to DockerHub, consider one of these options:
1. Docker Dashboard
2. Microsoft VSCode Docker
3. sbt -Ddocker.username=user-name -Ddocker.registry=registry-url docker:publish

Kubernetes
----------
1. sbt clean compile stage
2. sbt docker:clean  ( optional step that will throw an exception if the local image repo is empty )
3. sbt docker:stage  ( see target/docker/stage/Dockerfile )
4. sbt docker:publishLocal
5. see above Docker Push section  ( the deployment.yml, below, points to your docker hub )
6. sbt kubeyml:gen  ( see target/kubeyml/deployment.yml )
7. minikube start | minikube status
8. minikube dashboard
9. verify deployment of akka-http-server via minikube dashboard
   * kubectl create -f ./target/kubeyml/deployment.yml  ( invoke if akka-http-server is not deployed )
   * ***WARNING*** Can't get past this point. The above step works, but deployment ultimately fails!
10. minikube ip  ( insert minikube ip in curl url in next step )
11. curl http://$minikube-ip:7979/now
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
5. Probes - https://itnext.io/kubernetes-readiness-probes-examples-common-pitfalls-136e3a9a058d
>AWS
1. https://faun.pub/deploying-docker-images-to-ecs-b43058dc0456
