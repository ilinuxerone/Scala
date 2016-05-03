package com.dt.scala.oop.traits.logDemo

object Test {
  def main(args : Array[String]) : Unit = {
    val log1 = new ConcreteLogger with TraitLogger
    log1.concreteLog
    
    val acct1 = new SavingsAccount with ConsoleLogger with ShortLogger with TimestampLogger
    
    val acct2 = new SavingsAccount with ConsoleLogger with TimestampLogger with ShortLogger
    
    acct1.withdraw(100)
    acct2.withdraw(100)
  }
}