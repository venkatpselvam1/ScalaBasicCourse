package lectures.part3

object A01_FunctionProgram extends App{
  println("In OPPs, we define the class and methods inside the class." +
    "\nTo call the method, we create new instance of the class and call the method.")
  class objectClass{
    def objectMethod = println("This is a method inside a class")
  }
  println("We will instantiate the class and call the method.")
  println("JAVA/SCALA both provides generic, which will add one layer of abstraction, so the method can be very generic")

  class ObjectClass{
    def add[A](a: A, b: A) : A = {
      if (a == null) a
      else b
    }
  }
  var obj = new ObjectClass
  println(obj.add("a", "b"))
  println("The above generic method will take two parm of same type and return the first if first is not null else second")
  println("We can do one more optimization here with apply in scala")

  class ObjectClass2 {
    def apply[A](a: A, b: A): A = {
      if (a == null) a
      else b
    }
  }

  var obj2 = new ObjectClass2
  println(obj2("a", "b"))
  println("Noticed that, we didn't even use the method name. We just simplified obj.add(\"a\", \"b\") to obj2(\"a\", \"b\")")

  println("The same apply method can be with trait or abstract class")
  trait doubler[A]{
    def apply(a: A):A
  }
  var intDoubler = new doubler[Int] {
    override def apply(a: Int): Int = a + a
  }
  var strDoubler = new doubler[String] {
    override def apply(a: String): String = a + a
  }
  println(intDoubler(2))
  println(strDoubler("venkat"))
  println("noticed that, here we define the trait which have a method.  That method take a parm of type A and return value of type A")
  println("There are two implementation of the trait. intDoubler and strDoubler. And these are the instance of the anonymous class which have the trait double")
  println("We are calling intDoubler and strDoubler like a method. (thanks to apply in scala) ")

  println("Now the question is, Why do need to have the trait with 1 input and 1 output param defination?")
  println("Scala already have Function1 to Function25 which takes from 1 to 25 input param of any type and return a value")

  var intDoublerWithScalaFunction = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + v1
  }
  var strDoublerWithScalaFunction = new Function[String, String] {
    override def apply(v1: String): String = v1 + v1
  }
  println(intDoublerWithScalaFunction(2))
  println(strDoublerWithScalaFunction("venkat"))

  println("Synthetic sugar for Function[String, String] is ( (String) -> String ) ")
  var strAdder = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  var strAdderWithSyntheticSugar = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(strAdder("venkat", "test"))
  println(strAdderWithSyntheticSugar("venkat", "test"))
  println("ALL SCALA FUNCTIONS ARE OBJECTS")
}
