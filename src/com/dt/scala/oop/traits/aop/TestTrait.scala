package com.dt.scala.oop.traits.aop

object TestTrait {
  def main(args : Array[String]) : Unit = {
    val act1 = new RealAction with TBeforeAfter
    act1.doAction
    
    val act2 = new RealAction with TBeforeAfter  with TTwiceAction
    act2.doAction
    
    val act3 = new RealAction with TTwiceAction with TBeforeAfter
    act3.doAction
  }
}