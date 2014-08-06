package controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Akka


import language.postfixOps

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Terminated
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Play.current

import scala.concurrent.duration._


import actors._

object Application extends Controller {

  def index = Action.async { // aysnc lift Result to Future[Result]
    val frontend = Akka.system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2552/user/frontend")
    // import system.dispatcher    
    implicit val timeout = Timeout(5 seconds)
    val f = (frontend ? TransformationJob("hello"))
    f.map(i => Ok(views.html.index(i.toString)))
  }

}
