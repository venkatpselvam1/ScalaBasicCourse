package lectures.part2

import java.nio.{BufferOverflowException, BufferUnderflowException}

object Exceptions extends App{
  var str : String = null
  //println(str.length)
  println("This will crash")

  //throw new Exception()
  println("This also will throw error")

  //var s: String = throw new Exception()
  println("This also will throw error")

  def getInt(withException: Boolean): Int = {
    if(withException) throw  new Exception()
    else  10
  }

  var tryCatchType = try{
    getInt(true)
  }catch{
    case e : RuntimeException => println("IT is runtime exception")
    case e : Exception => println("It is ordinary exception")
  }finally {
    println("Finally it will always execute")
  }

  println("tryCatchType is anyvalue")
  var tryCatchType2 = try {
    getInt(true)
  } catch {
    case e: Exception => 5
  } finally {
    //always will be executed
    //will not effect the return type
    //optional
    println("Finally it will always execute")
  }

  println("tryCatchType2, is type int "+ tryCatchType2)


  class MyException extends Exception

  //throw new MyException
  println("This will throw excpetion")

  class SimpleCalc{
    def add(a: Int, b: Int) = {
      var c: Long = a.longValue() + b.longValue()
      if(c > Int.MaxValue) throw new BufferOverflowException()
      else c.intValue()
    }
    def sub(a: Int, b: Int) = {
      var c: Long = a.longValue() - b.longValue()
      if(c < Int.MinValue ) throw new BufferUnderflowException()
      else c.intValue()
    }
    def mul(a: Int, b: Int) = a*b
    def div(a: Int, b: Int) ={
      if(b == 0) throw new ArithmeticException()
      else a/b
    }
  }

  var cal = new SimpleCalc
  //OOM
  //var array = Array.ofDim(Int.MaxValue)

  def infinite : Int = 1 + infinite
  //println(infinite)
  //println(cal.add(Int.MaxValue, Int.MaxValue))
  //println(cal.sub(Int.MinValue, Int.MaxValue))
  //println(cal.div(Int.MinValue, 0))

  class SimpleCalc2 {
    def add(a: Int, b: Int) = {
      var c = a + b
      if( a > 0 && b > 0 && c < 0) throw new BufferOverflowException()
      else c
    }

    def sub(a: Int, b: Int) = {
      var c = a - b
      if( a > 0 && b < 0 && c < 0 ) throw new BufferOverflowException()
      if( a < 0 && b > 0 && c > 0 ) throw new BufferUnderflowException()
      else c
    }

    def mul(a: Int, b: Int) ={
      var c = a * b
      if( a > 0 && b > 0 && c < 0) throw new BufferOverflowException()
      if( a < 0 && b < 0 && c < 0) throw new BufferOverflowException()
      if( a > 0 && b < 0 && c > 0) throw new BufferUnderflowException()
      if( a < 0 && b > 0 && c > 0) throw new BufferUnderflowException()
      else c
    }

    def div(a: Int, b: Int) = {
      if (b == 0) throw new ArithmeticException()
      else a / b
    }
  }

  var cal2 = new SimpleCalc2
  //println(cal2.add(Int.MaxValue, Int.MaxValue))
  //println(cal2.sub(Int.MinValue, Int.MaxValue))
  //println(cal2.sub(Int.MaxValue, Int.MinValue))
  println(cal2.mul(Int.MaxValue, Int.MaxValue))
  println(cal2.mul(Int.MaxValue, Int.MinValue))
  println(cal2.mul(Int.MinValue, Int.MaxValue))
  println(cal2.mul(Int.MinValue, Int.MinValue))
  //println(cal2.div(Int.MinValue, 0))

}
