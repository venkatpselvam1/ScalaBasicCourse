package lectures.class1

object typeInference extends App{
  val a = 5
  val b = a + "venkat"
  def square(n: Int) = n * n //scala interpret the return type for all the above 3 cases
  def fact(n: Int):Int = if(n == 1) 1 else n * fact(n-1) //It is mandatory to specify the return type for rec function
}
