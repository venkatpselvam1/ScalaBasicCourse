package lectures.class1

object VariablesAndValues extends App{
  println("venkat")
  val x : Int = 42
  println(x+" is a variable. Variables are immutable. We can't change it.")
  //x = 34 // This line will cause compile time error

  val y = 42
  println(y + " is a variable with the initialized value as 42. We don't need to explicitly mention it as int")

  val z = 25;
  println(z + " is a variable with ';' . ';' is not mandatory in scala. " )

  val aInt = 4
  val aString = "venkat"
  val aChar = 'N'
  val aBoolean = true

  val aShort = 12
  val aLong = 1343439988977889L
  val aFloat: Float = 1.344f//By default 1.3444 is considered a double. here we need explicit mention as Float
  val aDouble = 1.3

  println(aInt +" is an Int")
  println(aString + " is an String")
  println(aChar + " is an Char")
  println(aBoolean + " is an Boolean")
  println(aShort + " is an Short")
  println(aLong + " is an Long")
  println(aFloat + " is an Float")
  println(aDouble + " is an Double")

  //Variable
  var a = 25
  println(a + " - a is a variable. So we can change the value of a ")
  a = 45
  println(a +" - a value is changed. This kind of variables are called SIDE EFFECT in the functional programing")
}
