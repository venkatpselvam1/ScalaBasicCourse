package lectures.part2

object OOBasics extends App{
  var obj1 = new Person1
  var obj2 = new Person2("venkat", 12)
  //println(obj2.age)
  println("We can not able to access obj.age")
  var obj3 = new Person3("venkat", 12)
  println(obj3.age)
  println("We can able to access obj.age because when we define the constructor param with var it became accessible field")
  var obj4 = new Person4("venkat", 12)
  println("Notice inside the class defination, the constructor param name is available for the use. We can think it as off private field")
  var obj5 = new Person5("venkat", 12)
  println(obj5.id)
  println("the variables/values defined inside the class defination will be available to access from the objects")
  var obj6 = new Person6("venkat", 12)
  obj6.greet("nandhu")
  println("We defined a new function in the class defination which will be available to access from the object")
  obj6.greet()
  println("The above code demonstrates the function overloading")
  var obj71 = new Person7("venkat")
  var obj72 = new Person7()
  var obj81 = new Person8("venkat")
  var obj82 = new Person8()
  println("class Person7 and Person 8 demonstrates secondary constructor and default parameter in constructor both are merely same")
}

class Person1

//constructor
class Person2(name: String, age: Int)

//constructor with var param available to access
class Person3(name: String, var age: Int)
class Person4(name: String, var age: Int)
{
  println("While constructing the obj name = " + name)
}
class Person5(name: String, var age: Int)
{
  var id = name + "-" + age
}

class Person6(name: String, var age: Int)
{
  def greet(name: String) = println(s"${this.name} says, Hello ${name}")

  def greet() = println(s"Hello! My name  is ${name}.")
}

class Person7(name: String, var age: Int)
{
  //secondary constructor-1 which calls the primary constructor
  def this(name: String) = this(name, 0)
  //secondary constructor-2 which calls the secondary constructor-1
  //in scala we can't have constructor to avoid using the primary constructor.
  def this() = this("")
}

//the class Person8 is same as class Person 7. The default parameter can work instead of the secondary constructor
class Person8(name: String = "", var age: Int = 0)