package lectures.part2

object Abstract extends App{
  abstract class AbstractHumanClass{
    var age: Int
    val age2: Int
    def eat : Unit
    def eatWithImpl = {
      println("This is eating in eatWithImpl")
      eat
    }
  }


  class OrdinaryHuman extends AbstractHumanClass {
    var age = 12
    override val age2: Int = 120

    def eat = println("I am implementation of eat in OrdinaryHuman")
  }

  //var abs = new AbstractHumanClass()
  println("Abstract class will have some unimplemented method or field or both." +
    "\nThey can't be instantiated")
  var ordinaryHuman = new OrdinaryHuman
  ordinaryHuman.eat
  ordinaryHuman.eatWithImpl

  //Traits
  trait Cornivour
  {
    def eatAnimal(name: String) : Unit
  }
  class Badman extends OrdinaryHuman with Cornivour
  {
    override def eatAnimal(name: String): Unit = {
      println(s"I am eating the animal $name")
      eat
    }
  }
  var badman = new Badman
  badman.eatAnimal("chicken")

  println("Trait and Abstract can both have implemented and un-implemented method")
  println("One class can have multiple traits ")
}
