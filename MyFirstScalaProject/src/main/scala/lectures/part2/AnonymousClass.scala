package lectures.part2

object AnonymousClass extends App{
  trait good {
    def service
  }
  var goodBoy = new good {
    override def service: Unit = println("I am at your service")
  }
  /*
    this is equivalent to

    class AnonymousClass$$anon$1 with good
    {
      override def service: Unit = println("I am at your service")
    }
    var goodBoy = new AnonymousClass$$anon$1

   */
  goodBoy.service
  println(goodBoy.getClass)

  class Teacher
  {
    def teach = println("Teacher is teaching")
  }

  var teacherA: Teacher = new Teacher{
    override def teach = println("Teacher is teaching life")
  }
  teacherA.teach
  println(teacherA.getClass)
}
