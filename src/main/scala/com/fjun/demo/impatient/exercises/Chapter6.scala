package com.fjun.demo.impatient.exercises

object Chapter6 {
  def main(args: Array[String]): Unit = {
    val feline = new Cat with Tiger with SmallTiger
    feline.say // Cat 和 Tiger 都与 say 方法, 调用时从右往左调用, 是 Tiger 在叫
    feline.king // 可以看到即使没有 cat 中没有 king 方法, Tiger 特质也能将自己的方法混入 Cat 中

    println(TrafficLightColor.Red)
  }

  object TrafficLightColor extends Enumeration {
    val Red, Yellow, Green = Value
  }

  // Feline 猫科动物
  abstract class Feline {
    def say()
  }

  trait SmallTiger extends Feline {
    // 在特质中重写抽象方法, 需要在方法前添加 abstract override 2个关键字
    abstract override def say() = println("嗷嗷嗷~~~")
    def king() = println("I will be king of here")
  }

  trait Tiger extends Feline {
    // 在特质中重写抽象方法, 需要在方法前添加 abstract override 2个关键字
    abstract override def say() = println("嗷嗷嗷!!!!")
    def king() = println("I'm king of here")
  }

  class Cat extends Feline {
    override def say() = println("喵喵喵")
  }
}
