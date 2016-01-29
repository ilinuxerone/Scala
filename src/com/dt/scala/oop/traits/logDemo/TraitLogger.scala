package com.dt.scala.oop.traits.logDemo

trait TraitLogger extends Logger{
  override def log (msg : String){
    println(" TraitLogger Log content is : " + msg)
  }
}