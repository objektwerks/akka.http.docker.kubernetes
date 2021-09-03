package objektwerks

import akka.actor.ActorSystem

import akka.http.scaladsl.Http

import com.typesafe.config.ConfigFactory

import scala.io.StdIn

object Server {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load("server.conf")
    val name = conf.getString("server.name")
    val host = conf.getString("server.host")
    val port = conf.getInt("server.port")

    implicit val system = ActorSystem.create(name, conf)
    implicit val executor = system.dispatcher

    val server = Http()
      .newServerAt(host, port)
      .bindFlow(Router.routes)

    println(s"*** Server started at http://$host:$port/\nPress RETURN to stop...")

    StdIn.readLine()
    server
      .flatMap(_.unbind())
      .onComplete { _ =>
        system.terminate()
        println("*** Server stopped.")
      }
  }
}