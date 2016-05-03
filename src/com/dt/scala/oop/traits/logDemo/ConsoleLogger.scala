package com.dt.scala.oop.traits.logDemo

trait ConsoleLogger extends Logged{
  override def log(msg : String ){println(msg)}
}