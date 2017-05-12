package com.hw.dataType.basic

/**
  * Created by Administrator on 2017/5/6.
  */
object MapTest {
  def main(args : Array[String]) : Unit = {
    val studentInfoMutable = scala.collection.mutable.Map("john" -> 21, "stephen" -> 22,"lucy" -> 20)

    //遍历方式
    for( i <- studentInfoMutable ) println(i)
    studentInfoMutable.foreach(e=> {val (k,v)=e; println(k+":"+v)})
    studentInfoMutable.foreach(e=> println(e._1+":"+e._2))

    //定义一个空的Map
    val xMap=new scala.collection.mutable.HashMap[String,Int]()
    xMap.put("spark",1)
    println(xMap.contains("spark"))
    xMap.get("spark")
  }
}
