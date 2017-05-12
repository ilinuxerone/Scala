package com.hw.oop

import scala.beans.BeanProperty

/**
  * Created by Administrator on 2017/5/1.
  */
class Person( name: String, age :Int = 18) {
   var id : String = null

   //定义成私有成员，其getter、setter方法也是私有的
   //类的成员域是val类型的变量，则只会生成getter方法
   private val carrar :  String = null;

   //将成员定义成private[this]则不会生成getter、setter方
   private[this] var hometown : String = "henan"

   //beanProperty用于生成setXxx()、getXxx()方法
   @BeanProperty var degree : String = null

   //构建器中的一部分，在创建对象时被执行
   println("---constructing person---")


   // override def toString() = { id + ":"+ degree}
   override def toString = s"Person($id, $degree)"

   def walk():Unit=println("walk like a normal person")
}


//Student继承Person类
class Student1(name:String,age:Int,var studentNo:String) extends Person(name,age){
   override def walk():Unit={
      super.walk()//调用父类的walk方法
      println("walk like a elegant swan")//增加了自己的实现
   }
}
object demo{
   def main(args: Array[String]): Unit = {
      val s=new Student1("john",18,"1024")
      s.walk()
   }
}