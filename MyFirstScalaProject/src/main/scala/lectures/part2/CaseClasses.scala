package lectures.part2

object CaseClasses extends App{
  case class Developer(name: String, age: Int, married: Boolean)

  //1. All the constructor params became field, so we don't need to mark the param with var to access them
  var developer1 = new Developer("venkat", 12, true)
  println(developer1.name+", "+developer1.age)

  //2. They have sensible tostring implemented
  println(developer1)

  //3. They have the equal and hash method overrided
  var developer2 = new Developer("venkat", 12, true)
  println(developer1 == developer2)
  println(developer1.hashCode()+", "+developer2.hashCode())

  //4. They have default copy method
  var developer3 = developer2.copy()
  var developer4 = developer2.copy(married = false)
  println(developer3)
  println(developer4)

  //5. They have the object companion created with factory methods
  //NTUCE: We didn't use the new keyword. becase, we were calling the apply method in the Developer companion to create this instance
  var developer5 = Developer("test", 45, true)
  println(developer5)

  //6. Deserialization and serialization
  //AKKA

  //7. They have extractor pattern - CCs can be used in pattern matching
}
