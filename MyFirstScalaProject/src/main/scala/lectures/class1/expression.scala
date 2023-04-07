package lectures.class1

import scala.util.chaining.scalaUtilChainingOps

object expression extends App{
  var a = 1 + 2
  println("a = 1+ 2 => evaluate and set the value of a as 3. this is called expression")
  println("1. Mathematical Expressions")
  println("     + , - , / , * , % , &, |, ^, >> , << , >>>> (>>>> only available is java)  ")
  println("2. Comparison Expression")
  println("     == , > , >= , < , <= ")
  println("3. Logical Expression")
  println("     !, &&, ||")
  println("4. Other Expression")
  println("     += , -= , *= , etc")
  var b = 5
  var c = a + 5 * 24 - b
  var d = a%2 == 0
  var e = !d && a < 34
  c += b

  //Instructions VS Expressions
  //Instruction -> Do something
  //Expression -> give something by doing something
  //Everything is Scala is Expression!
  //That means every line will return something. it will either return any values or return unit.
  //Unit is equivalent to void in other languages

  //while, println, variable - all are expressions which given unit as return types
  //Expression which gives Unit as return type is called SideEffects
  val wriredval = ( c = 7)
  println(wriredval)
  var e1 = println("venkat")
  println(e1.getClass() + " " + e1)
  var even = if (5%2 == 0) "even" else "odd";
  println(even.getClass())
  var printEven = if(5%2 == 0) println("even") else println("odd")
  println(printEven.getClass())
  var cnt = 10
  var sum = 0
  var whileVar = while(cnt > 0)
    {
      sum+=cnt
      cnt-=1
    }
  println(whileVar.getClass())
  "Hello World"

  // Code blocks
  println("Code blocks are expression. Difference is ->")
  println("It will contains multiple line surrounded by { and }")
  println("The value of the code block is : The value of the last line in the code block (or surrounded by return ")
  var isEven = {
    var a = 5
    var b = 6
    println("start here")
    if(a + b < 35 )
    {
      true
    }
    else{
      println("reach here")
      false
    }

  }
  println(isEven)
}
