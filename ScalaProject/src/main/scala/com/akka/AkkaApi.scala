package com.akka

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json._
import java.util.UUID

case class Series(name: String, releaseYear: Int, country: String, genre: String)
case class SeriesAdded(id: String, timestamp: Long)

trait ConvertToJsonProtocol extends DefaultJsonProtocol {
  implicit val seriesFormat = jsonFormat4(Series) //to convert the case class to Json format
  implicit val seriesAddedFormat = jsonFormat2(SeriesAdded) //to convert the case class to Json format
}

object AkkaApi extends ConvertToJsonProtocol with SprayJsonSupport{

  implicit val system = ActorSystem(Behaviors.empty, "AkkaApi")

  val route: Route = (path("api" / "series") & post) {
    entity(as[Series]) { series =>
      complete(SeriesAdded(UUID.randomUUID().toString, System.currentTimeMillis())) //to handle the response
    }
  }

  def main(args: Array[String]): Unit = {
    Http().newServerAt("localhost", 8080).bind(route)
  }
}
