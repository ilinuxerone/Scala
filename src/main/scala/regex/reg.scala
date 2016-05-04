package main.scala.regex

object reg {
  def main(args : Array[String]){
    val numPattern = "[0-9]+".r
    //如果正则表达式包含反斜杠或者引号的话用"""..."""
    val wsnumwsPattern = """\s+[0-9]+\s+"""
    for (matchString <-  numPattern.findAllIn("99 bottles, 98 bottles"))
      println(matchString)
      
    val matchesToArray = numPattern.findAllIn("99 bottles, 98 bottles").toArray
    
    //正则表达式组
    val numitemPattern = "([0-9]+) ([a-z]+)".r
    val numitemPattern(num, item)="99 bottles"
    for (numitemPattern(num, item) <- numitemPattern.findAllIn("99 bottles, 98 bottles"))
      println(num + "  " + item)
  }
}