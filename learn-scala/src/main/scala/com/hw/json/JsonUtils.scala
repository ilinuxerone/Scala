package com.hw.json
//隐式转换必须要导入
import org.json4s._
import org.json4s.jackson.JsonMethods._

/**
  * Created by Administrator on 2017/5/15.
  */
//class Book (author: String, content: String, id: String, time: Long, title: String)
case class Book(val author: String,val content: String,val id: String, val time: Long, val title: String)

object JsonUtils {

  def main(args : Array[String]) : Unit = {
    val json = "{\"author\":\"hll\",\"content\":\"ES即etamsports\",\"id\":\"693\",\"time\":1490165237200,\"title\":\"百度百科\"}"
/*    val mapper: ObjectMapper = new ObjectMapper()
    val book: Book = mapper.readValue(json, classOf[Book])*/

    //导入隐式值
    implicit val formats = DefaultFormats
    val book: Book = parse(json).extract[Book]
    println(book.content)
  }
}
