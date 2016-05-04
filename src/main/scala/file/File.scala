package main.scala.file

import java.io.File
import java.io.FileInputStream
import scala.io.Source
import java.io.PrintWriter

object File{
    
   def main(args : Array[String]){
     var source = Source.fromFile("src/main/scala/file/File.scala", "UTF-8")
     val lineIterator = source.getLines()
     for (line <- lineIterator)
     {
       println(line)
     }
     

     for (char <- source){
       print(char)
     }
     println
     
     source=Source.fromFile("src/main/scala/file/File.scala")
     source.getLines().foreach { x => println(x)}
     
     //get specific content
     source=Source.fromFile("src/main/scala/file/File.scala")
     val tokens = source.mkString.split("\\s+")
     tokens.foreach { x => println(x) }
     
/*     //read from console
     val age = readInt()
     println(age)
     
     val std = Source.stdin
     println(std.mkString)*/
     
     //read from url or string
/*     val sourcurl = Source.fromURL("http://www.baidu.com", "UTF-8")
     for (line <- sourcurl.getLines()){
       println(line)
     }*/
     
     //read binary file
     val file = new File("src/main/scala/file/Test.text")
     val in = new FileInputStream(file)
     val bytes = new Array[Byte](file.length().toInt)
     in.read(bytes)
     for(line<- bytes){
       print(line.toChar)
     }
     in.close()
     
     val out = new PrintWriter("src/main/scala/file/Test.text")
     for (i <- 0 to 100)out.println(i)
     out.close()
     
     //display directory
     val dir = new File("src")
     for (file <- listDir(dir)) println(file)
   }
   
   def listDir(dir : File) : Iterator[File] = {
     val children = dir.listFiles().filter { _.isDirectory() }
     children.toIterator ++ children.toIterator.flatMap {listDir(_)}
   }
   
   
}