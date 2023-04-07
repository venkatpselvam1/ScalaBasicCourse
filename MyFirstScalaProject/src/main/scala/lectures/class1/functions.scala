package lectures.class1

object functions extends App {

  println("functions are like expression with input and output parameters defined")
  def add(a: Int, b: Int): Int = {
    a + b
  }
  println("we don't have to mention the return type unless for recursion function")
  def printMyName() : Unit = {
    println("venkat")
  }
  printMyName
  println("parameterless functions can be called by only the function name")

  def repeat(str: String, time: Int) : String = {
    if(time == 1) str
    else str + repeat(str, time-1)
  }
  println(repeat("venkat", 5))
  /*
  Exercises:
  1. Greeting Function : Takes a name and age and print "My name is $name and I am $age year old"
  2. Factorial Function:
  3. N-th Fibanaci function
  4. Prime number function
   */
  def greetings(name: String, age: Int) : Unit =
    println("My name is "+name+" and I am " + age + " years old.")

  greetings("venkat", 12)

  def fact(n: Int): Int = {
    if(n == 1) 1
    else n * fact(n-1)
  }
  println(fact(5))

  def fib(n: Int): Int = {
    if(n <= 2)  1
    else fib(n-1) + fib(n-2)
  }
  var fibNumber = 1
  while(fibNumber < 20)
    {
      print(fib(fibNumber)+", ")
      fibNumber+=1
    }
    println()

  def prime(n: Int): Boolean = {
    if(n == 1) return false
    if(n <= 3) return true
    def primeWithDiv(q: Int): Boolean = {
      if(q == 1)  true
      else if(n%q == 0)  false
      else primeWithDiv(q-1)
    }
    primeWithDiv(n-1)
  }
  var number = 0;
  while(number < 100)
    {
      println(number + " - "+prime(number))
      number+=1
    }
}
