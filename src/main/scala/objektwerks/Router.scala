package objektwerks

import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._

import java.time.LocalDateTime

object Router {
  val health = path("health") {
    get { complete(OK -> "ok") }
  }
  val now = path("now") {
    get { complete(OK -> LocalDateTime.now.toString) }
  }
  val routes = health ~ now
}