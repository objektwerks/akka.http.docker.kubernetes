enablePlugins(JavaAppPackaging, DockerPlugin, KubeDeploymentPlugin)

name := "akka.http.docker.kubernetes"
organization := "objektwerks"
version := "0.1"
scalaVersion := "2.13.6"
libraryDependencies ++= {
  val akkaVersion = "2.6.16"
  val akkaHttpVersion = "10.2.6"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe" % "config" % "1.4.1",
    "ch.qos.logback" % "logback-classic" % "1.2.5"
  )
}

Docker / packageName := "akka-http-server"
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

import kubeyml.deployment._
import kubeyml.deployment.api._
import kubeyml.deployment.plugin.Keys._

kube / namespace := "default"
kube / application := "akka-http-server"
kube / dockerImage := "akka-http-server"
kube / envs := Map(
  EnvName("JAVA_OPTS") -> EnvRawValue("-Xms256M -Xmx1024M"),
)
kube / resourceLimits := Resource(Cpu.fromCores(2), Memory(1024))
kube / resourceRequests := Resource(Cpu(500), Memory(512))