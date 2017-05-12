package com.hw.dataType.complex

/**
  * Created by Administrator on 2017/5/6.
  */
object TupleTest {
  def main(args : Array[String]) :Unit = {
    var tuple=("Hello","China",1)
    //访问元组内容
    println(tuple._3)
    //通过模式匹配获取元组内容
    val (first, second, third)=tuple
  }
}
