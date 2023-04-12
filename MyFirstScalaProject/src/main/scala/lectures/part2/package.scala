package lectures

package object part2 {
  def helper = println("This helper method resides inside the package object in scala." +
    "This is accessible throughout this package without any fully qualified name")
  var g = 9.81f
}
