package lectures.class1

object DefaultAndNamedArgs extends  App{
  def mul(a: Int, b: Int=1): Int = {
    a+b
  }
  println( mul(10) +" it will multiply the value 10 with the default second param 1 and gives the ans as 10")
  def printImage(ty: String = "jpg", height: Int, width: Int)= println("Image is printed")
  //printImage(800, 900)//This will fail at compile time
  printImage(height=200, width = 100)//note here the parameter's order is changed

}
