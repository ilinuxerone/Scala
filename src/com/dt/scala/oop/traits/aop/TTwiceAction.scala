package com.dt.scala.oop.traits.aop

trait TTwiceAction extends TAction {
  abstract override def doAction {
    for (i <- 1 to 2){
      super.doAction
      println("==>NO. " + i)
    }
  }
}