package lectures.class1

object CallByValueAndCallByRef extends  App{
    def printValue(x: Long): Unit = {
      println("Call by Value " + x)
      println("Call by Value " + x)
    }
    def printValueByReference(x: => Long): Unit = {
      println("Call by reference " + x)
      println("Call by reference " + x)
    }
  printValue(System.nanoTime())
  printValueByReference(System.nanoTime())

  println("=> which indicates that this is expression should be evaluated runtime. it delays the value calculation.(Lazy)")

  var x= System.nanoTime()
  printValueByReference(x)
  println("In the above method, we didn't pass the expression we assign the expression value to a variable and passed the variable. So here, the value is same")

  println("this below code will show the lazy impl")
  def infy(): Int = 1 + infy()
  def printFirst(a: Int, b: => Int) ={
    println(a)
  }
  printFirst(5, infy())
  //printFirst(infy(), 5) // This will throw stack over flow expression
}
