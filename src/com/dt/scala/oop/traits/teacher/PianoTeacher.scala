package com.dt.scala.oop.traits.teacher

class PianoTeacher extends Human with TTeacher with PianoPlayer {
  override def teach = {println("I’m training students. ")} 
}