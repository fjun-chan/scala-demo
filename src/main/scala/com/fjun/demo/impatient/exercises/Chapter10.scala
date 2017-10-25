package com.fjun.demo.impatient.exercises

import java.io.PrintStream

object Chapter10 {
  def main(args: Array[String]): Unit = {
    val account:Account = new Account()
    account.test()
    val account2:Account2 = new Account2()
    account2.test()

    val account3: Account3 = new Account3()
    account3.test()
  }

  class Account3 extends {
    val fileName: String = "/tmp/test.log"
  } with FileLogger {
    def test(): Unit = {
      log("Hello world! This is my world!!")
    }
  }

  trait FileLogger extends Logger {
    val fileName: String
    val out: PrintStream = new PrintStream(fileName)
    def log(msg: String): Unit = {
      out.println(msg)
      out.flush()
    }
  }

  class Account2 extends ShortLogger with TimestampLogger  {
    def test(): Unit = {
      log("Hello world! This is my world!!")
    }
  }

  class Account extends TimestampLogger with ShortLogger  {
    def test(): Unit = {
      log("Hello world! This is my world!!")
    }
  }
}
trait DiffLogger {
  def log(msg: String): Unit = println("Other logger: " + msg)
}
trait Logger {
  def log(msg:String)
}

trait Logged extends Logger {
  override def log(msg: String): Unit = println(msg)
}

trait TimestampLogger extends Logged {
  override def log(msg: String): Unit = {
    super.log(new java.util.Date() + ": " + msg)
  }
}

trait ShortLogger extends Logged {
  val maxLength = 35
  override def log(msg: String): Unit = {
    super.log(if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
  }
}