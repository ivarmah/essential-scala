{
  case class Person(firstName: String, lastName: String) {
    def name = firstName + " " + lastName
  }

  val dave = new Person("Dave", "Bek")
  assert(dave.firstName == "Dave")

  val nick = Person("Nick", "Nox")
  assert(nick.firstName == "Nick")

  assert(dave.toString() == "Person(Dave,Bek)")
  assert(nick.toString() == "Person(Nick,Nox)")

  assert(new Person("Noel", "Welsh").equals(new Person("Noel", "Welsh")))
  assert(new Person("Noel", "Welsh") == new Person("Noel", "Welsh"))

  // eq for checking reference equality, ne is opposite to eq
  assert(!(new Person("Noel", "Welsh") eq (new Person("Noel", "Welsh"))))
  assert(new Person("Noel", "Welsh") ne (new Person("Noel", "Welsh")))


  assert(dave == dave.copy())
  assert(nick == nick.copy())
  assert(dave != nick.copy())


  assert(dave.copy(firstName = "Dave2").toString() == "Person(Dave2,Bek)")
  assert(dave != dave.copy(firstName = "Dave2"))

  assert(!(new Person("Noel", "Welsh") eq (new Person("Noel", "Welsh"))))
}

{
  case class Cat(colour:String, food:String)
  val cat = Cat("black","chips")
  assert(cat.colour == "black")
}


case class Director(firstName: String, lastName: String, yearOfBirth: Int){
  def name:String = s"$firstName $lastName"
}

object Director {
  def older(first: Director, second: Director): Director = {
    if(first.yearOfBirth < second.yearOfBirth) first else second
  }
}

case class Film(name:String, yearOfRelease:Int, imdbRating:Double, director:Director){
  def directorsAge:Int =  yearOfRelease - director.yearOfBirth
  def isDirectedBy (dir:Director):Boolean = dir.name == director.name
}

object Film{
  def newer(first: Film, second: Film):Film = if(first.yearOfRelease < second.yearOfRelease) first else second
  def highestRating(first: Film, second: Film): Double =
    if(first.imdbRating > second.imdbRating) first.imdbRating else second.imdbRating
  def oldestDirectorAtTheTime(first: Film, second: Film): Director = Director.older(first.director, second.director)
}

val barry = Director("Barry", "Spring", 1978)
val nick = Director("Nick", "Dolton", 1977)

val rockLee = Film("Rock", 1976, 6.7, barry)
val saskeDong = Film("Saske", 1986, 9, nick)

assert(Film.highestRating(rockLee, saskeDong) == saskeDong.imdbRating )
assert(Film.oldestDirectorAtTheTime(rockLee, saskeDong).firstName == nick.firstName)

assert(barry.copy().firstName == barry.copy().firstName)
assert(barry.copy() == barry.copy())


{
  class Adder(amount: Int) {
    def add(in: Int) = in + amount
  }

  case class Counter(count:Int = 0){
    def inc:Counter = copy(count = count + 1)
    def dec:Counter = copy(count = count - 1)
    def adjust(adder: Adder):Counter = copy(count = adder.add(count))
  }
  assert(Counter(10).inc.dec.inc.inc.count == 12)
  assert(Counter(10).adjust(new Adder(2)).count == 12)
  assert(Counter().adjust(new Adder(2)).count == 2)
  assert(Counter().inc.dec == Counter().dec.inc)
}



case class Person(firstName: String, lastName: String){
  def name = firstName + " " + lastName
}

object Person {
  def apply(fullName: String): Person = {
    val parts = fullName.split(" ")
    new Person(parts(0), parts(1))
  }

  // def apply(firstName:String, lastName:String):Person
}

val person =  Person("Bill Johnson")
assert(person.lastName == "Johnson")

val nextPerson =  Person("Bill", "Johnson")
assert(nextPerson.lastName == "Johnson")
