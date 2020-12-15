package com.example

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _       => println("huh?")
  }
}

class FredActor(name: String) extends Actor {
  override def receive: Receive = {
    case "hello" => println("Hi! My name is " + name)
    case _ => println("Do I know you?")
  }
}

object TheMain extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"

  val fredActor = system.actorOf(Props(new FredActor("Fred")), name = "fredactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}
