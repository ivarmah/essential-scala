import java.util.Date

{
  trait Visitor {
    def id: String

    def createdAt: Date

    def age: Long = new Date().getTime - createdAt.getTime
  }

  case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor
  case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

  def older(v1: Visitor, v2: Visitor): Boolean = v1.createdAt.before(v2.createdAt)

  val anonymous = Anonymous("1")
  val testUser = User("2", "test@example.com")

  assert(anonymous.id == "1")
  assert(older(anonymous, testUser))
}

{
  trait Feline {
    def colour: String

    def sound: String
  }

  trait BigCat extends Feline {
    def sound = "roar"
  }

  case class Cat(colour: String, food: String) extends Feline {
    val sound = "meow"
  }
  case class Tiger(colour: String) extends BigCat
  case class Lion(colour: String, maneSize: Int) extends BigCat
  case class Panther(colour: String) extends BigCat

  val cat = Cat("black", "chips")
  val tiger = Tiger("orange")

  assert(cat.sound == "meow")
  assert(tiger.sound == "roar")
}

{
  trait Shape {
    def sides: Int
    def perimeter: Double
    def area: Double
  }

  trait Quadrilateral extends Shape {
    def sides = 4
  }

  case class Circle(radius: Double) extends Shape {
    val sides = 0
    def perimeter: Double = Math.round(radius * 2 * Math.PI)
    def area: Double = Math.round(Math.PI * radius * radius)
  }

  case class Rectangle(height: Double, width: Double) extends Quadrilateral {
    def perimeter: Double = Math.round((height + width) * 2)
    def area: Double = Math.round(height * width)
  }

  case class Square(size: Double) extends Quadrilateral {
    def perimeter: Double = size * sides
    def area: Double = Math.pow(size, 2)
  }

  val circle = Circle(100)
  assert(circle.sides == 0)
  assert(circle.perimeter == 628.0)
  assert(circle.area == 31416.0)

  val rectangle = Rectangle(10, 20)
  assert(rectangle.sides == 4)
  assert(rectangle.perimeter == 60.0)
  assert(rectangle.area == 200.0)

  val square = Square(10)
  assert(square.sides == 4)
  assert(square.perimeter == 40.0)
  assert(square.area == 100.0)
}

{
  trait Shape {
    def sides: Int

    def perimeter: Double

    def area: Double
  }

  trait Rectangular extends Shape {
    def sides = 4

    def height: Int

    def width: Int

    def perimeter: Double = Math.round((height + width) * 2)

    def area: Double = Math.round(height * width)
  }

  case class Rectangle(height: Int, width: Int) extends Rectangular

  case class Square(size: Int) extends Rectangular {
    val height = size
    val width = size
  }

  val rectangle = Rectangle(10, 20)
  assert(rectangle.sides == 4)
  assert(rectangle.perimeter == 60.0)
  assert(rectangle.area == 200.0)

  val square = Square(10)
  assert(square.sides == 4)
  assert(square.perimeter == 40)
  assert(square.area == 100)
}

