package com.dt.scala.oop.traits.logDemo

trait TimestampLogger extends Logged{
  override def log(msg : String) {
    super.log(new java.util.Date() + " " + msg)
  }
}