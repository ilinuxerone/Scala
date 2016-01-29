package com.dt.scala.oop.traits.teacher

object Test {
  def main(args : Array[String]) : Unit = {
    val t1 = new PianoTeacher 
    t1.teach
    t1.playPiano
    
    val t2  = new Human with TTeacher with PianoPlayer {
      def teach = {
        println("I'm teaching students.")
      }
    }
    t2.playPiano
    t2.teach
  }
}