package lectures.part2

object MethodNotation extends App{
  class MoviePerson(name: String, favMovie: String)
  {
    def likes(movie: String) = movie == favMovie
  }

  var marry = new MoviePerson("marry", "inception")
  println(marry.likes("inception"))
  println(marry likes "inception")
  println("This is called infux notation. Synthetic sugar. We can have any string or symbol for this. ")

  println("operators in scala")
  class MoviePerson1(var name: String, favMovie: String, var age: Int ) {
    def likes(movie: String) = movie == favMovie
    def hangs(person1: MoviePerson1) = s"${name} is hanging out with ${person1.name}"
    def +(person1: MoviePerson1) = hangs(person1)
    def +(person1: String) = s"${name} is hanging out with ${person1}"
    def +(year: Int) = age+=year
  }
  var marry1 = new MoviePerson1("marry", "inception", 2)
  var julie1 = new MoviePerson1("julie", "batman", 3)
  println(marry1.hangs(julie1))
  println(marry1 hangs julie1 )
  println(marry1 + julie1 )
  println(marry1 + "test" )
  println("before " + marry1.age)
  marry1+45
  println("after " + marry1.age)

  println("unary operators in scala")
  println("The below example show the example of unary operator. Unary operator can only be used with !,-, ?, ~ ")
  class MoviePerson2(name: String, favMovie: String, var age: Int) {
    def unary_!  : String = s"${name} what the heck."

    def unary_+ : MoviePerson2 = {
      age += 1
      this
    }
  }
  var marry2 = new MoviePerson2("marry", "inception" , 10)
  println(!marry2)
  println("Before "+marry2.age)
  +(+marry2)
  println("After "+marry2.age)

  var x = -5
  var y = 5.unary_-
  var w = 2 + 3
  var z = 2.+(3)
  println("x = -5 and y = 5.unary_- both are equal")
  println("2 + 3 and 2.+(3) both are equal. All the operators in the scala are a method that defined in the class")

  println("ALL OPERATORS ARE METHODS")
  println("ACTORS are -, !, ~, ?")
  class MoviePerson3(name: String, favMovie: String) {
    def apply() : String = s"${name} we are applying."
    def apply(x: Int) : String = s"${name} we are applying with the value ${x}."
    def apply(x: Int, y: Int) : Int = x + y + name.length
  }
  var marry3 = new MoviePerson3("marry", "inception")
  println("apply in scala")
  println(marry3.apply())
  println(marry3())
  println(marry3(4))
  println(marry3(2, 4))

  println("Prefix notation")

  class MoviePerson4(name: String, favMovie: String) {
    def fullName(): String = s"${name} likes ${favMovie}"
    def learn(str: String) = s"${name} is learning $str"
    def learnScale = this learn "scala"
  }
  println("This shows example for prefix notation")
  var marry4 = new MoviePerson4("marry", "inception")
  println(marry4.fullName)
  println(marry4 learn "scale")
  println(marry4.learnScale )
}

