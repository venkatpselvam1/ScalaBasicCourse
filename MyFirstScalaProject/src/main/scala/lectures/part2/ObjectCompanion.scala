package lectures.part2

object ObjectCompanion extends App{
  object Student{
    var age = 12
    val maxLife = 100
    println("venkat")
    def greet = "Hello Man"
    def greet(name: String) = s"Hello $name"

    def factory(student1: Student, student2: Student) = new Student(student1.name+student2.name, Math.max(student1.age, student2.age))

  }

  println(Student.age)
  Student.age = 24
  println(Student.age)
  //Student.maxLife = 12
  println(Student.maxLife)
  println(Student.greet)
  println(Student.greet("venkat"))

  class Student(var name: String, var age: Int)
  {
    def greet = s"Hi, I am $name and I am $age years old"
    def greet(name: String) = s"Hi $name, I am ${this.name} and I am $age years old. My max age is ${Student.maxLife}"

  }
  var venkat = new Student("venkat", 12)
  println(venkat.greet)
  println(venkat.greet("nandhu"))

  var s1 = Student
  var s2 = Student
  println(s1 == s2)
  var s3 = new Student("v", 1)
  var s4 = new Student("v2", 1)
  println(s3 == s4)
  var s5 = Student.factory(s3, s4)
  println(s5.greet)


}

//SCALA APPLICATION - Scala Object with
// def main(args: Array[String]) : Unit

object VenkatApp {
  def main(args: Array[String]): Unit = {
    println("Hello world.")
  }
}
