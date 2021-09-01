package akka.http

import akka.actor.ActorSystem

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes.OK

import com.typesafe.config.ConfigFactory

import java.time.LocalTime

import scala.io.StdIn

object Server {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load("server.conf")
    implicit val system = ActorSystem.create(conf.getString("server.name"), conf)
    implicit val executor = system.dispatcher
    
    val route = get { complete( OK -> LocalTime.now.toString ) }

    val host = conf.getString("server.host")
    val port = conf.getInt("server.port")
    val server = Http()
      .newServerAt(host, port)
      .bindFlow(route)

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