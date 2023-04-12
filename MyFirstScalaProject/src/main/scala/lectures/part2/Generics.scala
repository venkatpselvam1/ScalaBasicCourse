package lectures.part2


object Generics extends App{
  class MyGenList[A]
  class MyGenMap[key, Value]

  var myIntList = new MyGenList[Int]
  var myStringList = new MyGenList[String]

  object MyGenList
  {
    def getEmptyLst[A]: MyGenList[A] = null
  }
  var myEmptyIntList = MyGenList.getEmptyLst[Int]
  var myEmptyStringList = MyGenList.getEmptyLst[String]

  //Varience
  class Animal
  class Dog extends Animal
  class Cat extends Animal

  //Type 1 => covariance
  //if cat extends animal, List[cat] extends List[animal]

  class CovariantList[+A]
  var animal:Animal = new Cat
  var animalList: CovariantList[Animal] = new CovariantList[Cat]

  //can I add animalList.add(new Dog) => yes, we can add.

  //Type 2 => InVariantList
  //If cat extends animal, List[cat] should not extend List[animal]
  class InvariantList[A]
  //var animalList2 : InvariantList[Animal] = new InvariantList[Cat]

  //Type 3 => Contravariant
  //if Cat extends animal, List[cat] will not extend List[Animal]
  //but we can assign List[Animal] to List[Cat]
  class ContraVariant[-A]
  var animalList3: ContraVariant[Cat] = new ContraVariant[Animal]

  //Bounded types
  //Adding limit on the Generic class

  //Upper bounded type
  class Dog2 extends Dog
  class Dog3 extends Dog2
  class Cage[A <: Animal](animal: A)
  class Car
  var cage1 = new Cage[Dog](new Dog)
  var cage2 = new Cage[Cat](new Cat)
  var cage3 = new Cage[Dog2](new Dog2)
  var cage4 = new Cage[Dog3](new Dog3)
  //var cage3 = new Cage(new Car)

  class Cage2[A >: Dog3](animal: A)
  var cage5 = new Cage2[Dog3](new Dog3)
  var cage6 = new Cage2[Dog2](new Dog2)
  var cage7 = new Cage2[Dog](new Dog)
  var cage8 = new Cage2[Animal](new Animal)

  class MyGenListCovariant[+A]
  {
    def add[B >: A](value: B) : MyGenListCovariant[B] = null
  }
}
