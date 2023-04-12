package lectures.part2

import lectures.class1.expression

object PackageAndImport extends App{
  println("when we are in a pacakge we can access all the class and object inside the package." +
    "\n if we want to access the class form other package we need to use the imort keyword or full qualified name")

  var exp = expression
  println("Here expression is imported from lectures.class1.expression")
  var playGround = playground.PlayGround
  println("here we are using the fully qualified name and avoided using the import")

  helper
  println(g)
}
