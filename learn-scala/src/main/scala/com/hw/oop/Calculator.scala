package com.hw.oop

import scala.beans.BeanProperty

/**
  * Created by Administrator on 2017/4/29.
  */
class Calculator(brandinput : String) {
  val brand  : String = if ("TI".equals(brandinput)) {
    "blue"
  } else if ("HP".equals(brandinput)) {
    "black"
  } else {
    "white"
  }
  def add(a : Int, b : Int) : Int = {
    a + b
  }

  //BeanProperty于生成getXxx,setXxx方法
  @BeanProperty var name : String = ""

  override def toString = s"Calculator($name)"
}

object Calculator {
  def apply(brand: String): Calculator = new Calculator(brand)
}
