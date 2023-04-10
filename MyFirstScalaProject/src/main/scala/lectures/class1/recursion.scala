package lectures.class1

import scala.annotation.tailrec
object recursion extends App{

  //@tailrec
  def fact(n: Int):Int = if (n <= 1) 1 else n * fact(n-1)
  //fact (20000)
  println("We noticed that fact of 5000 is failing")


  def anotherFact(n: Int) ={
    @tailrec
    def factWithRecursionAsLast(n: Int, prodSoFar: BigInt): BigInt =
    {
      if(n <= 1) prodSoFar
      else factWithRecursionAsLast(n-1, n * prodSoFar);
    }
    factWithRecursionAsLast(n, 1);
  }

  println(anotherFact(50000))
  println("We can see this other factorial works. It is because when the recursion is the last line of the recursion")
  println("As the second rec function, instead of building the stack, it will replace the current stack with the new method information")
  println("In other words, the recursion function don't have to wait for the result from child calls")
  println("This is called TailRecursion. We need to put the attribute @tailrec so the compiler will test if the recursion is optimized for tail recursion or not.")


  /*
  Exercise: Write the following functions as tail recursion
  1. String Repeater
  2. Prime Number
  3. Nth fibonacci
   */
  def stringRepeater(str: String, n: Int) : String = {
    @tailrec
    def stringRepeaterTailRec(ans: String, cnt: Int) : String =
      {
        if(n == cnt) ans
        else stringRepeaterTailRec(ans+str, cnt+1)
      }

    stringRepeaterTailRec("", 0)
  }
  println(stringRepeater("venkat", 12))

  def primeNumber(n : Int): Boolean = {
    @tailrec
    def primeNumberRec(divider:Int): Boolean={
      if(divider == n) true
      else if (n % divider == 0) false
      else primeNumberRec(divider+1)
    }
    if (n == 1) false
    else if(n == 2) true
    else primeNumberRec(2)
  }
  var i = 1
  while(i <= 100)
  {
    println(i+" - "+primeNumber(i))
    i+=1
  }

  def nthfibonanci(n: Int) : BigInt = {
    @tailrec
    def nthfibonanciRec(acc: BigInt, acc2: BigInt, cnt: Int): BigInt ={
      if(n == cnt)  acc + acc2
      else nthfibonanciRec(acc+acc2, acc, cnt+1)
    }
    if (n <= 2)  1
    else nthfibonanciRec(1, 1, 3)
  }

  i = 1
  while (i <= 100) {
    print(nthfibonanci(i)+", ")
    i += 1
  }
}
