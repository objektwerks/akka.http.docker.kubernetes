enablePlugins(JavaAppPackaging, DockerPlugin, KubeDeploymentPlugin)

lazy val dockerAppName = "now"
lazy val dockerImageName = "akka-http-server"
lazy val dockerHubName = "objektwerks"
lazy val dockerAppVersion = "0.1"

name := "akka.http.docker.kubernetes"
organization := "objektwerks"
version := dockerAppVersion
scalaVersion := "2.13.15"
libraryDependencies ++= {
  val akkaVersion = "2.6.21" // Don't upgrade due to BUSL 1.1!
  val akkaHttpVersion = "10.2.10" // Don't upgrade due to BUSL 1.1!
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe" % "config" % "1.4.3",
    "ch.qos.logback" % "logback-classic" % "1.5.8",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}

import com.typesafe.sbt.packager.docker._
import com.typesafe.sbt.packager.docker.DockerChmodType

Docker / packageName := dockerImageName
dockerExposedPorts ++= Seq(7979)
dockerBaseImage := "zulu-openjdk-alpine:21"
dockerCommands ++= Seq(
  Cmd("USER", "root"),
  ExecCmd("RUN", "apk", "add", "--no-cache", "bash"),
  ExecCmd("RUN", "apk", "add", "--no-cache", "curl")
)
dockerChmodType := DockerChmodType.UserGroupWriteExecute

import kubeyml.deployment._
import kubeyml.deployment.api._
import kubeyml.deployment.plugin.Keys._
import scala.concurrent.duration._
import scala.language.postfixOps

kube / namespace := "default"
kube / application := dockerAppName
kube / dockerImage := s"$dockerHubName/$dockerImageName:$dockerAppVersion"
kube / ports := List(Port(dockerAppName, 7979))
kube / envs := Map(
  EnvName("JAVA_OPTS") -> EnvRawValue("-Xms256M -Xmx1024M"),
)
kube / livenessProbe := HttpProbe(
  HttpGet(path = "/health", port = 7979, httpHeaders = List.empty),
  initialDelay = 3 seconds, timeout = 3 seconds, period = 30 seconds,
  failureThreshold = 3, successThreshold = 1
)
kube / resourceLimits := Resource(Cpu.fromCores(2), Memory(1024))
kube / resourceRequests := Resource(Cpu(500), Memory(512))
