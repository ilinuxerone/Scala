package com.dt.scala.basics
/** 
 * 第一种方式 
 * 在内部类通过【外部类.this.成员名称】 访问外部类成员 
 */  
class OuterClass(val name:String){  
    
   class InnerClass(val name:String){  
       
      def info = println("Outer name :" + OuterClass.this.name + ",Inner Name :" + name);  
   }  
}  
  
/** 
 * 第二种方式 
 * 在内部类通过【外部类别名】 访问外部类成员 
 */  
class OuterClass2(val name:String){  
  outer =>  
   class InnerClass2(val name:String){  
       
      def info = println("Outer name :" + outer.name + ",Inner Name :" + name);  
   }  
}  
  
object OuterAndInnerClassTest {  
    
  def main(args: Array[String]): Unit = {  
      
    println("第一种访问方式：")  
    val outer1 = new OuterClass("yy")  
    val inner1 = new outer1.InnerClass("xx")  
    inner1.info  
    println("第二种访问方式：")  
    val outer2 = new OuterClass2("yy2")  
    val inner2 = new outer2.InnerClass2("xx2")  
    inner2.info  
      
  }  
}  