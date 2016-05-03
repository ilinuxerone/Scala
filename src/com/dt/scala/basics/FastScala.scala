package com.dt.scala.basics
import scala.util.matching.Regex
import scala.collection.mutable.ArrayBuffer

object FastScala {
  var factor = 3
  def main(args : Array[String]) {
    println("---TEST FUNCTION---")
    println("Test Function Closure")
    println("mutiplier(2) value = " + multiplier(2))
    
    println("---TEST STRING---")
    testString()
    
    println("----TEST ARRAY----")
    
    println("---TEST Regular---")
    testRegx()
  }
  
  def arrayTest() : Unit = {
    val b = ArrayBuffer[Int]()
    
    b += 1
    
    b += (2,3,4,5)
    
    b ++= Array(6,7)
  }
  
  def multiplier = (i : Int) => i * factor
  
  def testString() : Unit = {
    val buf = new StringBuilder
    buf += 'a'
    buf ++= "bcde"
    println("buf is : " + buf.toString()) 
    
    println("buf length is :" + buf.length)
    
    var string1 : String = "hello world"
    var string2 = string1.concat("haha")
    println(string2)
    
    var floatVar = 12.456
    var intVar = 2000
    var stringVar = "haha"
    var fs = printf("浮点型变量为 " + "%f, 整型变量为 %d, 字符串为 " + " %s", floatVar, intVar, stringVar)
  }
  
      def testRegx(){
      val pattern = "Scala".r()
      val pattern1 = new Regex("(S|s)cala")
      val str = "Scala is Scalable and cool"
      
      println(pattern findFirstIn str)
      println((pattern1 findAllIn str).mkString(","))
      
      val pattern3 = new Regex("abl[ae]\\d+")
      val str2 = "ablaw is able1 and cool"
      
      println((pattern3 findAllIn str2).mkString(","))
    }
}