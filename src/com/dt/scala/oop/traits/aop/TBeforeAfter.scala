package com.dt.scala.oop.traits.aop

trait TBeforeAfter extends TAction {
abstract override  def doAction {
  //doAction的前置处理  
  println("/entry before-action") 
  super.doAction
  //doAction的后置处理
  println("/exit after-action") 
  }
}