package objektwerks

import akka.actor.ActorSystem

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest

import com.typesafe.config.ConfigFactory

import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ServerTest extends AnyWordSpec with Matchers with ScalatestRouteTest with BeforeAndAfterAll {
  val actorRefFactory = ActorSystem.create("now", ConfigFactory.load("test.conf"))
  val server = Http()
    .newServerAt("localhost", 0)
    .bindFlow(Router.routes)

  override protected def afterAll(): Unit = {
    server
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

  "Server" should {
    "get health and now" in {
      Get("/health") ~> Router.routes ~> check {
        status shouldBe StatusCodes.OK
      }
      Get("/now") ~> Router.routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}