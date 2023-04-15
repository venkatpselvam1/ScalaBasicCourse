package lectures.part4

object A04_Braceless extends App {
  var ifExp = if (5 % 2 == 0) "true" else "venkat"

  var ifExp2 =
    if (5 % 2 == 0) {
      "true"
    }
    else {
      "venkat"
    }


  var ifExp3 = {
    if (5 % 2 == 0) "true"
    else "venkat"
  }
  /*
  var ifExp4 =
    if 5%2==0 then
        "true"
    else
        "venkat"

   */

  //For-Comprehension

  var aComp = for{
    a <- List(1,2,3)
    b <- List("red", "black")
  } yield(a, b)
/*
var aComp =
for
  a <- List(1, 2, 3)
  b <- List("red", "black")
yield (a, b)
 */
  var age = 42
  var patternMatch = age match {
    case x if x%2 == 0 => println("even")
    case 10 => println("10")
    case _ => println("everythin")
  }

  /*
  var patternMatch2 =
    age match
      case x if x % 2 == 0 => println("even")
      case 10 => println("10")
      case _ => println("everythin")

   */

  //ethod without brace
  def compute(x: Int) = {
    var vari = 40
    x*50 + vari
  }

  /*
  def compute(x: Int) =
    var vari = 40



    x * 50 + vari

   */

  //classes, trait, object everything can be defined with braces
  class Animal {
    def eat(name: String) = {
      println(s"I am eating $name")
      println(s"I am eating2 $name")
    }
  }


  /*
  class Animal2
    def eat(name: String) =
      println(s"I am eating $name")
      println(s"I am eating2 $name")
    end eat
   end Animal2

   */

  //Anonymous class can also be defined without braced but with significant indention
  //significant indention - simply larger intendation should be bigger than last code block

}
