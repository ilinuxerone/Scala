package main.scala.process

import scala.sys.process._
import java.io.File

object command {
  def main(args : Array[String]){
    "ls -al .." !    
      
    "ls -al .." #| "grep sec" !
    
    "ls -al .."  #>  new File("src/main/scala/process/output.txt") !
    //注释地方有问题
  //  val result = "ls -al .." !!
  //  for (res <- result)print(res)
 //    "ls -al .."  #>>  new File("src/main/scala/process/output.txt") !
     
     "grep Apr" #< new File("src/main/scala/process/output.txt") !
  }
}