package com.fjun.demo.impatient.exercises

import scala.beans.BeanProperty

/**
  * <p>
  * <br> =========================
  * <br> 公司：阿里移动事业群-国际业务部-国际研发部
  * <br> 系统：U盟业务线-中台系统
  * <br> @author：陈飞君（feijun.cfj@alibaba-inc.com）
  * <br> @date： 2017-09-18
  * <br> =========================
  */
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
