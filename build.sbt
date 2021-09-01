enablePlugins(JavaAppPackaging)

name := "akka.http.docker"
organization := "objektwerks"
version := "0.1"
scalaVersion := "2.13.6"
libraryDependencies ++= {
  val akkaVersion = "2.6.16"
  val akkkHttpVersion = "10.2.6"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkkHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe" % "config" % "1.4.1",
    "ch.qos.logback" % "logback-classic" % "1.2.5"
  )
}

dockerExposedPorts ++= Seq(9000)
dockerBaseImage := "openjdk:8-jre-alpine"