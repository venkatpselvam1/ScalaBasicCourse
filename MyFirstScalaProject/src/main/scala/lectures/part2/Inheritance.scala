package lectures.part2

object Inheritance extends App{
  class Animal1
  {
    var age: Int = 23
    def eat() = println("Animal is eating")
    private def eatPrivate() = println("Animal is eating in private")
    protected def eatProtected() = println("Animal is eating with protection")
  }
  class Dog1 extends Animal1
  var dog1 = new Dog1
  {
    def proxyEatProtected = {
      println("This is proxy method to eatProtected in animal")
      eatProtected()
    }
  }
  dog1.eat()
  println(dog1.age)
  //dog1.eatPrivate()
  println("dog1.eatPrivate() can't be called as Animal base class defined the method eatPrivate as private")
  //dog1.eatProtected()
  dog1.proxyEatProtected
  println("dog1.eatProtected() can't be called as Animal base class defined the method eatProtected as protected." +
    " But this method protected can be called from dog1 class")
  println("This is inheritance")

  class Animal2{
    val length = "12"
    val width = 23
    def eat = println("Animal2 is eating")
  }
  class Dog2(override val width : Int = 230) extends Animal2
  {
    override val length = "120"
    override def eat = {
      println("Dog2 is eating")
      super.eat//super keyword is used to call the based method from derived class
    }
  }
  var dog2 = new Dog2
  dog2.eat

  println("The above example shows example of override. 'Super' keyword is used to call the based method from derived class ")
  var andimal2: Animal2 = dog2
  andimal2.eat
  println("This above shows example of polymorphism." +
    "\nWhen the subclass is casted to base class, subclass still calls its eat method instead of super class's eat method")

  println(dog2.width+", "+dog2.length)
  println(andimal2.width+", "+andimal2.length)
  println("This above example shows how to override the val in super class." +
    "\nNOTICE: we can't override the var(variables) only val(values) can be overridden in scala")
  class Animal3(name: String, age: Int)
  {
    def this(name: String) = this(name, 0)
  }
  class Dog3A(name: String, age: Int) extends Animal3(name, age)
  class Dog3B(name: String, age: Int) extends Animal3(name)
  println("The above example shows how to call the primary and secondary constructor of base class from the derived class")

  class Animal4
  {
    final def eat = println("Animal4 is eating")
  }
  class Dog4 extends Animal4
  {
    //def override eat = println("can't override the final method")
  }
  println("This above example shows we can't override the method which marked with final keyword the same goes for class. ")
  final class Animal5
  //class Dog5 extends Animalf5
  println("There is special keyword seal, which allows the super class can be extended only in the same file. " +
    "\nIt means the animal class can have Dog and Cat impl in the same file, but we can't have any other impl for Animal")
  sealed class Animal6
  class Dog6 extends Animal6
  class Cat6 extends Animal6
  println("We can't have any other class which inherits the Animal")
}
