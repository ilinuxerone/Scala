package com.dt.scala.oop.traits.logDemo

class SavingsAccount extends Logged {
  def withdraw (amount : Double){
    log("withdraw")
  }
}