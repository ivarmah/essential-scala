object Oswald {
  val colour: String = "Black"
  val food: String = "Milk"
}

object Henderson {
  val colour: String = "Ginger"
  val food: String = "Chips"
}

object Quentin {
  val colour: String = "Tabby and white"
  val food: String = "Curry"
}

object Test7 {
  val simpleField = {
    println("Evaluating simpleField")
    42
  }

  def noParameterMethod = {
    println("Evaluating noParameterMethod")
    42
  }
}

object Test4 {
  val name = "Noel"

  def hello(other: String): String =
    name + " says hi to " + other
}

object calc {
  def square(double: Double): Double = double * double

  def cube(double: Double): Double = square(double) * double
}

object calc2 {
  def square(double: Double): Double = double * double

  def cube(double: Double): Double = square(double) * double

  def square(int: Int): Int = int * int

  def cube(int: Int): Int = square(int) * int
}

object argh {
  def a = {
    println("a")
    1
  }

  val b = {
    println("b")
    a + 2
  }

  def c = {
    println("c")
    a
    b + "c"
  }
}


// res: String = 3c31
println(argh.c + argh.b + argh.a)

println(Oswald.colour, Oswald.food)

// "Evaluating simpleField" is printed
println(Test7)

// field value will be always the same
// 42
println(Test7.simpleField)
// 42
println(Test7.simpleField)

//4.0
println(calc.square(2.0))
// 8.0
println(calc.cube(2.0))

//4
println(calc2.square(2))
// 8
println(calc2.cube(2))