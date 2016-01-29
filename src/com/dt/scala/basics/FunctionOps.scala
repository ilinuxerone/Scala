package com.dt.scala.basics

import scala.io.Source

//本地函数
object FunctionOps {
  def main(args : Array[String]){    
    val width = args(0).toInt
    for (arg <- args.drop(1))
      processData(arg, width)
  }
  
  var increase = (x : Int) => x +1
  println(increase(10))
  increase = (x: Int) => x + 9999
  
  val someNumbers = List(-11, -10, -5, 0, 5, 10)
  someNumbers.foreach { (x : Int) => println(x) }
  
  someNumbers.filter { (x : Int) => x > 0 }.foreach { x => println(x) }
  
  someNumbers.filter { _ > 0 }.foreach { x => println(x) }
  
  val f = (_ : Int) + (_ : Int)
  println(f(5,5))
  
  def processData(filename : String, width : Int){
    //内部函数，外部不能访问
    def processLine(line : String){
      if (line.length() > width)
        println(filename + ": " + line)
    }
    
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}