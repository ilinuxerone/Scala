package com.dt.scala.oop.traits.logDemo

class ConcreteLogger extends Logger with Cloneable{
   //override def log(msg : String) = println("Log: " + msg)
   def concreteLog{
    log("It's me !!!")
  }
}