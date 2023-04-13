package lectures.part3

object A04_HOFsAndCurries extends App{
  println("Higher order function is a function which takes another function as input or output." +
    "\nThe map, flatmap, filter in MyList all are example of higher order function.")
  //var someFunction : (Int, (Int, String) => Int ) => (Int => Int) = ???
  println("The above is a a complex higher order function")
  println("We can apply a function f on x and get a return value. On the return value, we can apply the same function again.")
  println("The below example shows how to apply a function f on variable x for n times")
  //ntimes(f, x, n)
  //ntimes(f, x, 3) => f(f(f(x)))

  // var f : Int => Int = x => x * 2
  var ntimes : (Int => Int, Int, Int) => Int = { (f, x, n) =>
    if(n <= 0) x
    else ntimes(f, f(x), n-1)
  }
  println(ntimes(x => x * 2, 2, 3))
  println(ntimes(x => x * x, 2, 4))

  def ntimesGen[A](f: A => A, x: A, n: Int) : A = {
    if (n <= 0) x
    else ntimesGen(f, f(x), n - 1)
  }
  println(ntimesGen[String](x => x + x, "Ab", 5))

  println("The above two example showed normal and generic version of ntimes func impl")
  def ntimesbetter(f: Int => Int, n: Int) : Int => Int = {
    if(n == 0) x => x
    else x => ntimesbetter(f, n-1)(f(x))
  }
  println(ntimesbetter(x => x * 2, 3)(2))
  println("The above is an example for curried function")
  //create a curried function to add two number
  def superAdder(x : Int) : ( Int => Int) = y => x + y
  var adder3  = superAdder(3)
  var adder4  = superAdder(4)
  println(adder3(10))
  println(superAdder(3)(10))
  println(adder4(10))

  //functions with multiple param
  //convert the double to string with the given format
  def format(format: String)(d: Double) = format.format(d)
  def format2(f: String): (Double => String) = x => f.format(x)

  val standardFormat: (Double => String) = format("%4.2f")
  val preciseFormat: (Double => String) = format("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  val standardFormat2: (Double => String) = format2("%4.2f")
  val preciseFormat2: (Double => String) = format2("%10.8f")

  println(standardFormat2(Math.PI))
  println(preciseFormat2(Math.PI))
}
