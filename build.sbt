enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

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

dockerExposedPorts ++= Seq(7979)
dockerBaseImage := "openjdk:8-jre-alpine"

import com.typesafe.sbt.packager.docker._
dockerCommands ++= Seq(
  Cmd("USER", "root"),
  ExecCmd("RUN", "apk", "add", "--no-cache", "bash"),
  ExecCmd("RUN", "apk", "add", "--no-cache", "curl")
)

import com.typesafe.sbt.packager.docker.DockerChmodType
dockerChmodType := DockerChmodType.UserGroupWriteExecute