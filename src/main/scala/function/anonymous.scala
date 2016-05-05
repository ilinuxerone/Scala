package main.scala.function

object anonymous {
  def main(args : Array[String]){
    val triple = (x : Double ) => 3 * x
    
    println(triple(4))
  }
}