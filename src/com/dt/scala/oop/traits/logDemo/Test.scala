package com.dt.scala.oop.traits.logDemo

object Test {
  def main(args : Array[String]) : Unit = {
    val log1 = new ConcreteLogger with TraitLogger
    log1.concreteLog
  }
}