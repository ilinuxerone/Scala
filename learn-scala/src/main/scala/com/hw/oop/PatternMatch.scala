package com.hw.oop

import scala.Any

/**
  * Created by Administrator on 2017/4/29.
  */
object PatternMatch {
  def matchValue(time: Int): String = {
    time match {
      case 1 => "one"
      case 2 => "two"
      case _ => "default"
      /*  time match {
            case i if i == 1 => "one"
            case i if i == 2 => "two"
            case _ => "some other number"
           }*/
    }
  }

  def matchType(dataType : Any): Any = {
    dataType match
    {
      case i: Int if i < 0 => i - 1
      case i: Int => i + 1
      case d: Double if d < 0.0 => d - 0.1
      case d: Double => d + 0.1
      case text: String => text + "s"
      case _ => "default"
    }
  }

  def matchValue(calc: Calculator) = {
    calc match {
      case _ if calc.brand == "HP" => "financial"
      case _ if calc.brand == "HP" => "scientific"
      case _ if calc.brand == "HP" => "business"
      case _ => "unknown"
    }
  }

  def main(args: Array[String]) :Unit = {
    for(i<- 1 to 100) {
      i match {
        case 10 => println(10)
        case 50 => println(50)
        case 80 => println(80)
        //增加守卫条件
        case _ if (i % 4 == 0) => println(i + ":能被4整除")
        case _ if (i % 3 == 0) => println(i + ":能被3整除")
        case _ =>
      }
    }
  }

}
