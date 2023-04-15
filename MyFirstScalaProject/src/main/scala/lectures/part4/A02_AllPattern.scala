package lectures.part4

import lectures.part3.A05_HOFsAndCurriesExercise.{EmptyList, VenkatList, VenkatListImpl}

object A02_AllPattern extends App{
  //1-constant
  var x: Any = 1
  var constant = x match{
    case 1 =>  "one"
    case "scala" => "The SCALA"
    case true => "true;;;"
    case A02_AllPattern => "the current class"
  }
  //2-match anything
  //2.1 wild card
  var matchAnything = x match{
    case _ => println("anything")
  }

  //2.2 match variable
  var matchAVariable = x match {
    case some => s"This is some ${some}"
  }

  //3 Tuples
  var aTuple = (1, 2)
  var tupleMatch = aTuple match{
    case (1, 1) => println("1 and 1")
    //case ("Venkat", 1) => println("it is not possible")
    case (sme, 1) => println(s"it is  possible  with $sme and 1")
    case _ => println("anything")
  }

  var nestedTuple = (1, (2, 3))
  var nestedTupleMatch = nestedTuple match{
    case (_, (2, v)) => println(s"it is first match with $v")
  }

  //4 CaseClasses - Constructor matching
  var aList: VenkatList[Int] = new VenkatListImpl[Int](2, new VenkatListImpl[Int](3, EmptyList))
  var matchAList = aList match{
    case EmptyList => println("It is empty")
    case VenkatListImpl(2, VenkatListImpl(head, tail)) => println(s"This is my list with second element $head ans $tail")
  }

  //5 List pattern
  var aStandardList = List(1,2,3,42)
  var standardListMatch =aStandardList match {
    case List(1, _ , _, _) => println("starts with 1")
    case List(1, _*) => println("list of arbitary length")
    case 1 :: List(_) => println("infix pattern")
    case List(1,2,3) :+ 42  => println("infix pattern")
  }

  //6 - Type specifier
  var unknown: Any = 2
  var unknownMatch = unknown match{
    case list: List[Int] => println("It is a list")
    case map: Map[Int, String] => println("It is a map")
    case _ => println("anything")
  }

  //7 - name binding
  var nameBindingMathc = aList match {
    case nonEmpty @ VenkatListImpl(value, tailList) => println("we can use later")
    case VenkatListImpl(1, rest @ VenkatListImpl(2, _)) => println("it is second type")
  }

  //8 Multi Pattern
  var multiPattern = aList match {
    case EmptyList | VenkatListImpl(0, _) => println("combine two pattern")
    case _ => println("anything")
  }

  //9 if guard
  var secondSpecialElement = aList match {
    case VenkatListImpl(_, VenkatListImpl(x, _)) if x % 2 == 0 => println("list with second element as even")
    case _ => println("anything")
  }

  var numbers = List(1, 2, 3 )

  var numberMatch = numbers match {
    case lStr : List[String] => println("it is a string list")
    case lNumber : List[Int] => println("it is a number list")
    case _ => println("othes")
  }

  println(numberMatch)

  //JAVA didn't have generics. so this problems happen

  /*
  var numberMatch = numbers match {
    case lStr : List => println("it is a string list")
    case lNumber : List => println("it is a number list")
    case _ => println("othes")
  }
   */
}
