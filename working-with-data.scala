// POLYMORPHISM
{
  sealed trait A{
    def foo: String
  }
  final case class B() extends A{
    def foo: String = "It's B"
  }
  final case class C() extends A{
    def foo: String = "It's C"
  }

  val aB: A = B()
  assert(aB.foo == "It's B")

  val aC: A = C()
  assert(aC.foo == "It's C")
}

{
  sealed trait A{
    def foo: String = "It's A"
  }
  final case class B() extends A{
    override def foo: String = "It's B"
  }
  final case class C() extends A{
    override def foo: String = "It's C"
  }

  final case class Z() extends A

  val aB: A = B()
  assert(aB.foo == "It's B")

  val aC: A = C()
  assert(aC.foo == "It's C")

  val aZ: A = Z()
  assert(aZ.foo == "It's A")
}

{
  //Polymorphism
  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFood extends Food
  final case object Licorice extends Food
  final case class CatFood(food: String) extends Food

  sealed trait Feline {
    def dinner: Food
  }

  final case class Lion() extends Feline{
    def dinner: Food = Antelope
  }
  final case class Tiger() extends Feline{
    def dinner: Food = TigerFood
  }
  final case class Panther() extends Feline{
    def dinner: Food = Licorice
  }
  final case class Cat(favouriteFood: String) extends Feline{
    def dinner: Food = CatFood(favouriteFood)
  }
}

{
  //Pattern Matching
  sealed trait Food
  final case object Antelope extends Food
  final case object TigerFood extends Food
  final case object Licorice extends Food
  final case class CatFood(food: String) extends Food

  sealed trait Feline {
    def dinner: Food =
      this match {
        case Lion() => Antelope
        case Tiger() => TigerFood
        case Panther() => Licorice
        case Cat(favouriteFood) => CatFood(favouriteFood)
      }
  }

  object Diner {
    def dinner(feline: Feline): Food =
      feline match {
        case Lion() => Antelope
        case Tiger() => TigerFood
        case Panther() => Licorice
        case Cat(food) => CatFood(food)
      }
  }

  final case class Lion() extends Feline
  final case class Tiger() extends Feline
  final case class Panther() extends Feline
  final case class Cat(favouriteFood: String) extends Feline
}


{
  // With pa􏰂ern matching

  sealed trait TrafficLight{
    def next: TrafficLight = this match{
      case Red => Green
      case Green => Yellow
      case Yellow => Green
    }
  }
  final case object Red extends TrafficLight
  final case object Green extends TrafficLight
  final case object Yellow extends TrafficLight
}

{
  // With pa􏰂ern matching
  sealed trait TrafficLight

  object TrafficLight{
    def next(current: TrafficLight): TrafficLight = current match{
      case Red => Green
      case Green => Yellow
      case Yellow => Green
    }
  }

  final case object Red extends TrafficLight
  final case object Green extends TrafficLight
  final case object Yellow extends TrafficLight
}

{
  // With polymorphism:
  sealed trait TrafficLight{
    def next: TrafficLight
  }
  final case object Red extends TrafficLight{
    def next: TrafficLight = Green
  }
  final case object Green extends TrafficLight{
    def next: TrafficLight = Yellow

  }
  final case object Yellow extends TrafficLight{
    def next: TrafficLight = Red
  }
}

{
  sealed trait Calculation
  final case class Success(result: Int) extends Calculation
  final case class Failure(reason: String) extends Calculation

  case object Calculator {
    def +(calculation: Calculation, operand: Int): Calculation = calculation match {
      case Success(result) => Success(result + operand)
      case Failure(reason) => Failure(reason)
    }

    def -(calculation: Calculation, operand: Int): Calculation = calculation match {
      case Success(result) => Success(result - operand)
      case Failure(reason) => Failure(reason)
    }

    def /(calculation: Calculation, operand: Int): Calculation = calculation match {
      case Success(result) => operand match{
        case 0 =>  Failure("Division by zero")
        case _ => Success(result / operand)
      }
      case Failure(reason) => Failure(reason)
    }
  }

  assert(Calculator.+(Success(1), 1) == Success(2))
  assert(Calculator.-(Success(1), 1) == Success(0))
  assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))

  assert(Calculator./(Success(4), 2) == Success(2))
  assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
  assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))
}