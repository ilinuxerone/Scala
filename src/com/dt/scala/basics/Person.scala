package com.dt.scala.basics

class Person(val sex : String) {
   private var name : String = _
   val id : String = "id"
   var age : String = _
   
   class InnerClass(val sex : String){
     def info() {println("Outer sex: " + Person.this.sex)}
   }
   
}


