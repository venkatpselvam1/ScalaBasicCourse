package lectures.class1

object StringsOps  extends  App{
  var str = "Hello! I am learning scala."

  println("The below are java string methods")
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(' ').toList)
  println(str.startsWith("Hello"))
  println(str.replace("he", "be"))
  println(str.toLowerCase())
  println(str.length)

  println("These are scala methods")
  var aNumberStr = "34"
  var aInt = aNumberStr.toInt
  println(aNumberStr+" after the conv "+aInt)
  println("a" +: aNumberStr :+ "b")
  println(aNumberStr)
  println(str.reverse)
  println(str.take(5))

  println("These below are scala S-interpolation")
  var name = "venkat"
  var age = 12
  var greeting = s"$name and ${age+1} is string interpolation"
  println(greeting)

  println("These beloew are scala f-Interpolation")
  var speed = 12.4f
  var myth = f"$name with $age can eat $speed burgers"
  println(myth)
  var myth1 = f"$name%s with $age can eat $speed%5.5f burgers"
  println(myth1)

  println("These beloew are scala raw interpolation")
  println("I am venkat\nand my age is \t\t\t 34")
  println(raw"I am venkat\nand my age is \t\t\t 34")
}
