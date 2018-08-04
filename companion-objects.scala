{
  class Timestamp(val seconds: Long)

  object Timestamp {
    def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
      new Timestamp(hours * 60 * 60 + minutes * 60 + seconds)
  }

  assert(Timestamp(1, 1, 1).seconds == 3661)

}

{
  class Person(val firstName: String, val lastName: String)

  object Person {
    def apply(fullName: String): Person = {
      val parts = fullName.split(" ")
      new Person(parts(0), parts(1))
    }
  }

  val person =  Person("Bill Johnson")
  assert(person.lastName == "Johnson")

}

{

  class Director(val firstName:String, val lastName:String, val yearOfBirth:Int)

  object Director{
    def apply(firstName:String, lastName:String, yearOfBirth:Int): Director = {
      new Director(firstName, lastName, yearOfBirth)
    }

    def older(first: Director, second: Director): Director = {
      if(first.yearOfBirth < second.yearOfBirth) first else second
    }
  }

  val barry = new Director("Barry", "Spring", 1978)
  val nick = new  Director("Nick", "Dolton", 1977)

  assert(Director.older(barry, nick).firstName == nick.firstName)

  class Film(val name:String, val yearOfRelease:Int, val imdbRating:Double, val director:Director)

  object Film{
    def apply(name:String,  yearOfRelease:Int,  imdbRating:Double,  director:Director): Film = {
     new Film(name, yearOfRelease, imdbRating, director)
    }

    def highestRating(first: Film, second: Film): Double =
      if(first.imdbRating > second.imdbRating) first.imdbRating else second.imdbRating

    def oldestDirectorAtTheTime(first: Film, second: Film): Director = Director.older(first.director, second.director)
  }

  val rockLee = new Film("Rock", 1976, 6.7, barry)
  val saskeDong = new Film("Saske", 1986, 9, nick)

  assert(Film.highestRating(rockLee, saskeDong) == saskeDong.imdbRating )
  assert(Film.oldestDirectorAtTheTime(rockLee, saskeDong).firstName == nick.firstName )

}