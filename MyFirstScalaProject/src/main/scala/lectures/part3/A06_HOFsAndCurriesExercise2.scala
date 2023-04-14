package lectures.part3

import lectures.part3.A05_HOFsAndCurriesExercise.{EmptyList, VenkatList}

object A06_HOFsAndCurriesExercise2 extends App{
  println("In this file, we have 3 sections.")
  println("1. Implement tocurry, fromcurry, compose, andThen")
  println("2. Test tocurry, fromcurry, compose, andThen")
  println("3. Try convert def fucntion with lambda functions")
  //Exercise implement tocurry, fromcurry, compose, andThen
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
    x => y => f(x, y)
  }

  def fromCurry(f: (Int => Int => Int)): ((Int, Int) => Int) = {
    (x, y) => f(x)(y)
  }

  def compose[A, B, T](f: T => B, g: A => T): A => B = x => f(g(x))

  def andThen[A, B, T](f: (A => T), g: T => B): A => B = x => g(f(x))

  //Test tocurry, fromcurry, compose, andThen
  def superAdder(x: Int)(y: Int): Int =  x + y
  def superAdder2(x: Int, y: Int): Int =  x + y
  def superAdderCurry(x: Int): (Int => Int) =  y => x + y


  println(superAdder2(2, 9) + ", " + toCurry(superAdder2)(2)(9) + " Hence, to curry works fine")
  println(superAdderCurry(2)(11) + ", " + fromCurry(superAdderCurry)(2, 11) + " Hence, from curry works fine")

  var add3: Int => Int = x => x + 3
  var mul5: Int => Int = x => x * 5

  println(add3(mul5(4)) + ", " + compose(add3, mul5)(4) + " Hence, compose works fine")
  println(mul5(add3(4)) + ", " + andThen(add3, mul5)(4) + " Hence, andThen works fine")

  println("The below is the attempt to convert all the function I wrote to lambda")
  //var superAdderLambda : (Int   , Int) => Int = (x, y) =>  x + y(x)

  var superAdder2Lambda: (Int, Int) => Int = (x, y) => x + y

  var superAdderCurryLambda: Int => (Int => Int) = x => y => x + y

  var superAdderCurryByFunc = toCurry(superAdder2)
  var superAdderByFunc = fromCurry(superAdderCurry)

  var defIntToStr : Int => String = x => ""+ x
  var defStrToList : String => VenkatList[Int] = x => EmptyList

  var defIntToListByCompose : Int => VenkatList[Int] = compose(defStrToList, defIntToStr)
  var defIntToListByAndThen : Int => VenkatList[Int] = andThen(defIntToStr, defStrToList)


}
