import java.util.Date

{
  sealed trait Visitor {
    def id: String
    def createdAt: Date
    def age: Long = new Date().getTime - createdAt.getTime
  }

  final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor
  final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor
}

{
  sealed trait Color{
    def red: Double
    def green: Double
    def blue: Double
    def isLight = (red + green + blue) / 3.0 > 0.5
    def isDark = !isLight
  }

  final case object Red extends Color {
    val red = 1
    val green = 0
    val blue = 0
  }

  final case object Blue extends Color {
    val red = 0
    val green = 0
    val blue = 1
  }

  final case object Green extends Color {
    val red = 0
    val green = 1
    val blue = 0
  }

  final case class CustomColor(red: Double, green: Double, blue: Double) extends Color

  sealed trait Shape {
    def sides: Int
    def perimeter: Double
    def area: Double
    def color: Color
  }

  sealed trait Quadrilateral extends Shape {
    def sides = 4
  }

  case class Circle(radius: Double, color: Color) extends Shape {
    val sides = 0

    def perimeter: Double = Math.round(radius * 2 * Math.PI)

    def area: Double = Math.round(Math.PI * radius * radius)
  }
  case class Rectangle(height: Double, width: Double, color: Color) extends Quadrilateral {
    def perimeter: Double = Math.round((height + width) * 2)

    def area: Double = Math.round(height * width)
  }

  case class Square(size: Double, color: Color) extends Quadrilateral {
    def perimeter: Double = size * sides

    def area: Double = Math.pow(size, 2)
  }

  object Draw{
    def apply(shape: Shape): String = {
      shape match  {
        case Circle(radius, color) => s"A ${Draw(color)} circle of radius ${radius}cm"
        case Rectangle(height, width, color) => s"A ${Draw(color)} rectangle of $height cm and $width cm"
        case Square(size, color) => s"A ${Draw(color)} square of $size cm"
      }
    }

    def apply(color: Color): String = {
      color match  {
        case Red => "red"
        case Blue => "blue"
        case Green => "green"
        case color => if(color.isLight) "light" else "dark"
      }
    }
  }

  val circle = Circle(100, Red)
  val rectangle = Rectangle(10, 20, Green)
  val square = Square(10, Blue)
  var darkRectangle = Rectangle(10, 20, CustomColor(0.4, 0.4, 0.6))

  assert(Draw(circle) == "A red circle of radius 100.0cm")
  assert(Draw(rectangle) == "A green rectangle of 10.0 cm and 20.0 cm")
  assert(Draw(square) == "A blue square of 10.0 cm")
  assert(Draw(darkRectangle) == "A dark rectangle of 10.0 cm and 20.0 cm")
}

{

  sealed trait DivisionResult
  final case class Finite(result: Int) extends DivisionResult
  final case object Infinite extends DivisionResult

  case object divide{
    def apply(first: Int, second: Int): DivisionResult = {
      if(second == 0) Infinite else Finite(first/second)
    }
  }

  assert((divide(1, 0) match {
    case Finite(value) => s"It's finite: ${value}"
    case Infinite      => s"It's infinite"
  }) == "It's infinite")

  assert((divide(4, 2) match {
    case Finite(value) => s"It's finite: ${value}"
    case Infinite      => s"It's infinite"
  }) == "It's finite: 2")
}



