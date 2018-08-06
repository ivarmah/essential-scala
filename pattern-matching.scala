{
  case class Person(firstName: String, lastName: String)

  object Stormtrooper {
    def inspect(person: Person): String =
      person match {
        case Person("Luke", "Skywalker") => "Stop, rebel scum!"
        case Person("Han", "Solo") => "Stop, rebel scum!"
        case Person("Ivar", _) => s"You are not from this reality, Ivar!"
        case Person(first, last) => s"Move along, $first!"
      }
  }

  val luke = Person("Luke", "Skywalker")
  val solo = Person("Han", "Solo")
  val dandy = Person("Dandy", "Newton")
  val ivar = Person("Ivar", "Mahhonin")
  val ivarLinux = Person("Ivar", "Lunux")

  assert(Stormtrooper.inspect(luke) == "Stop, rebel scum!")
  assert(Stormtrooper.inspect(solo) == "Stop, rebel scum!")
  assert(Stormtrooper.inspect(dandy) == "Move along, Dandy!")
  assert(Stormtrooper.inspect(ivar) == "You are not from this reality, Ivar!")
  assert(Stormtrooper.inspect(ivarLinux) == "You are not from this reality, Ivar!")
}

{
  case class Cat(colour:String, food:String)

  object ChipShop{
    def willServe(cat: Cat): Boolean = {
      cat match{
        case Cat(_, "chips") => true
        case Cat(_, _) => false
      }
    }
  }

  val kitty = Cat("black", "fish")
  val helloKitty = Cat("pink", "chips")

  assert(ChipShop.willServe(kitty) == false)
  assert(ChipShop.willServe(helloKitty) == true)
}


{

  case class Director(firstName: String, lastName: String, yearOfBirth: Int){
    def name:String = s"$firstName $lastName"
  }

  case class Film(name:String, yearOfRelease:Int, imdbRating:Double, director:Director)

  object Dad{
    def rate(film: Film): Double = {
      film match{
        case Film(_, _, _, Director("Clint", "Eastwood", _)) => 10.0
        case Film(_, _, _, Director("John", "McTiernan", _)) => 7.0
        case _ => 3.0
      }
    }
  }

  val clint = Director("Clint", "Eastwood", 1969)
  val john = Director("John", "McTiernan", 1950)
  val ivar = Director("Ivar", "Mahhonin", 1990)


  val clintFilm = Film("Clint Film", 1990, 7, clint)
  val johnFilm = Film("John Film", 1990, 10, john)
  val ivarFilm = Film("Ivar Film", 2006, 10, ivar)


  assert(Dad.rate(clintFilm) == 10.0)
  assert(Dad.rate(johnFilm) == 7.0)
  assert(Dad.rate(ivarFilm) == 3.0)
}