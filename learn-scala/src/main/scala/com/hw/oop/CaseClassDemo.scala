package com.hw.oop

/**
  * Created by Administrator on 2017/5/9.
  */
//抽象类Person
abstract class Human

//case class Student
case class Student(name:String,age:Int,studentNo:Int) extends Human
//case class Teacher
case class Teacher(name:String,age:Int,teacherNo:Int) extends Human
//case class Nobody
case class Nobody(name:String) extends Human

//接受多个person类型参数的类
case class SchoolClass(classDescription:String,persons:Person*)

object CaseClassDemo{
  def main(args: Array[String]): Unit = {
    //case class 会自动生成apply方法，从而省去new操作
    val p:Human=Student("john",18,1024)
    //match case 匹配语法
    p  match {
      case Student(name,age,studentNo)=>println(name+":"+age+":"+studentNo)
      case Teacher(name,age,teacherNo)=>println(name+":"+age+":"+teacherNo)
      case Nobody(name)=>println(name)
    }

    val ps=List("spark","hive","SparkSQL")
    def sequencePattern(p:List[String])=p match {
      //只需要匹配第二个元素
      case List(_,second,_*) => second
      case _ => "Other"
    }
    println(sequencePattern(ps))

    val m=Map("china"->"beijing","dwarf japan"->"tokyo","Aerican"->"DC Washington")
    //利用for循环对Map进行模式匹配输出，
    for((nation,capital)<-m)
      println(nation+": " +capital)

    val t=("spark","hive","SparkSQL")
    def tuplePattern(t:Any)=t match {
      case (one,_,_) => one
      case _ => "Other"
    }
    println(tuplePattern(t))
  }



}