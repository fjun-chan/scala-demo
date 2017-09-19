package com.fjun.demo.impatient.exercises

import scala.beans.BeanProperty

object Chapter5 {
  def main(args: Array[String]): Unit = {
    val student:Student = new Student("test", 1)
    println(student.id)
    println(student.getId)
  }


  class Student(@BeanProperty var name:String, @BeanProperty var id: Long) {
  }

  class Time(val hours:Int, val minutes:Int) {
    val time: Long = hours * 60 + minutes
    def before(other: Time): Boolean = {
      time < other.time
    }
  }

  class BankAccount(var deposit: String, var withdraw: String, val balance: String) {
  }
}
