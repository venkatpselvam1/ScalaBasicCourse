package lectures.part2

import lectures.class1.expression
import lectures._
import java.util.{Date => jdate}
import java.sql.{Date => sdate}

object PackageAndImport extends App{
  println("when we are in a pacakge we can access all the class and object inside the package." +
    "\n if we want to access the class form other package we need to use the imort keyword or full qualified name")

  var exp = expression
  println("Here expression is imported from lectures.class1.expression")
  var playGround = playground.PlayGround
  println("here we are using the fully qualified name and avoided using the import")

  var jdate = new jdate()
  var sdate = new sdate(2012, 1, 1)
  println("We can use import lectures._ to import all the classes int the package ")

  println("Default imports java.lang" )
  println("Default imports Int, Nothing, Function" )
  println("Default imports scala.Predef" )
  helper
  println(g)
}
