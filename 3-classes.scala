
// In Scala everything is an Object (unlike in Java)

{
  class Person {
    val firstName = "Ivar"
    val lastName = "Mahhonin"

    def name = firstName + " " + lastName
  }

  object alien {
    def greetings(person: Person) = "Greetings, " + person.name
  }

  val human = new Person
  assert(human.firstName == "Ivar")
  assert(human.name == "Ivar Mahhonin")
  assert(alien.greetings(human) == "Greetings, Ivar Mahhonin")
}

{
  class Person(first: String, last: String) {
    val firstName = first
    val lastName = last
    def name = firstName + " " + lastName
  }

  val human = new Person("Ivar", "Mahhonin")
  assert(human.firstName == "Ivar")
  assert(human.name == "Ivar Mahhonin")
}


{
  class Person(val firstName: String, val lastName: String) {
    def name = firstName + " " + lastName
  }

  val human = new Person("Ivar", "Mahhonin")
  assert(human.firstName == "Ivar")
  assert(human.name == "Ivar Mahhonin")

  val anotherHuman = new Person(lastName = "Mahhonin", firstName = "Mahhonin")
  assert(human.firstName == "Ivar")
  assert(human.name == "Ivar Mahhonin")
}

// keyword parameters

{
  def greet(firstName:String = "Some", lastName:String = "Body") = "Greetings, " + firstName + " " + lastName
  assert(greet("Ivar", "Mahhonin") == "Greetings, Ivar Mahhonin")
  assert(greet("Ivar") == "Greetings, Ivar Body")
  assert(greet(lastName = "Mahhonin") == "Greetings, Some Mahhonin")
}

{
  def greet(title: String = "Citizen", firstName: String = "Some", lastName: String = "Body") = "Greetings, " + title + " " + firstName + " " + lastName + "!"
  assert(greet("Awesome") == "Greetings, Awesome Some Body!")
  assert(greet(firstName = "Awesome") == "Greetings, Citizen Awesome Body!")
}

{
  // Type Nothing
  try{
    def badness = throw new Exception("Error")
    // badness: Nothing


    val bar = if(true) 123 else badness
    // bar: Int = 123

    val nullValue = if(false) "it worked" else null
    // baz: String = null
  }
  catch {
    case e:Throwable => ()
  }
}


{
  class Cat(val name:String, val colour:String, val food:String){
  }

  val oswald:Cat = new Cat("Oswald", "Black", "Milk")
  assert(oswald.name == "Oswald")

  object ChipShow{
    def willServe(cat:Cat):Boolean = if(cat.food == "Chips") true else false
  }

  assert(ChipShow.willServe(oswald) == false)

  val mori:Cat = new Cat("Mori", "Melamori", "Chips")

  assert(ChipShow.willServe(mori) == true)
}

{
  class Director(val firstName:String, val lastName:String, val yearOfBirth:Int){
    def name:String = firstName + " " + lastName
  }

  class Film(val name:String, val yearOfRelease:Int, val imdbRating:Double, val director:Director){
    def directorsAge:Int =  yearOfRelease - director.yearOfBirth
    def isDirectedBy (dir:Director):Boolean = dir.name == director.name
    def copy (
               name:String = this.name,
               yearOfRelease:Int = this.yearOfRelease,
               imdbRating:Double = this.imdbRating,
               director:Director = this.director):Film = new Film(name, yearOfRelease, imdbRating, director)
  }


  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "Some Body", 1990)
  val memento = new Film("Memento", 2000, 8.5, nolan)
  val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
  val inception = new Film("Inception", 2010, 8.8, nolan)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
  val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val invictus = new Film("Invictus", 2009, 7.4, eastwood)
  val predator = new Film("Predator", 1987, 7.9, mcTiernan)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

  assert(eastwood.yearOfBirth == 1930)
  assert(dieHard.director.name == "John McTiernan")
  assert(invictus.isDirectedBy(nolan) == false)

  val highPlainsDrifterCopy = highPlainsDrifter.copy(name = "L'homme des hautes plaines")
  assert(highPlainsDrifterCopy.name == "L'homme des hautes plaines")
  assert(highPlainsDrifterCopy.yearOfRelease == highPlainsDrifter.yearOfRelease)


  val thomasCrownAffairCopy = thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926))

  assert(thomasCrownAffairCopy.name == thomasCrownAffair.name)
  assert(thomasCrownAffairCopy.yearOfRelease == 1968)
  assert(thomasCrownAffairCopy.director.name == "Norman Jewison")

  val inceptionDoubleCopy = inception.copy().copy().copy()
  assert(inceptionDoubleCopy.name == inception.name)
  assert(inceptionDoubleCopy.yearOfRelease == inception.yearOfRelease)
  assert(inceptionDoubleCopy.director.name == inception.director.name)
}


{
  class Counter(i:Int){
    def inc:Counter = new Counter(i + 1)
    def dec:Counter = new Counter(i - 1)
    def count:Int = i
  }
  assert(new Counter(10).inc.dec.inc.inc.count == 12)
}

{
  class Adder(amount: Int) {
    def add(in: Int) = in + amount
  }

  class Counter(i:Int, step:Int = 1){
    def inc:Counter = new Counter(i + step, step)
    def dec:Counter = new Counter(i - step, step)
    def count:Int = i
    def adjust(adder: Adder):Counter = new Counter(adder.add(count))
  }
  assert(new Counter(10).inc.dec.inc.inc.count == 12)
  assert(new Counter(10, 2).inc.dec.inc.inc.count == 14)
  assert(new Counter(10).adjust(new Adder(2)).count == 12)
  assert(new Counter(10,2).adjust(new Adder(2)).count == 12)
}


{
  class Adder(amount: Int) {
    def apply(in: Int): Int = in + amount
  }
  assert(new Adder(2).apply(2) == 4)

  val addr = new Adder(3)
  assert(addr(2) == 5)
}