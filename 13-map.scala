
sealed trait LinkedList[A] {
  def map[B](fn: A => B): LinkedList[B] =
    this match {
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
      case End() => End[B]()
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

final case class End[A]() extends LinkedList[A]

val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))

val doubledListResult = Pair(2, Pair(4, Pair(6, End())))

val doubleListShortenedWay = list.map(_ * 2)
assert(doubleListShortenedWay == doubledListResult)

val doubleListLongerWay = list.map((elem) => elem * 2)
assert(doubleListShortenedWay == doubledListResult)


val increasedListResult = Pair(2, Pair(3, Pair(4, End())))

val increasedList = list.map(_ + 1)
assert(increasedList == increasedListResult)


val dividedListResult = Pair(0, Pair(0, Pair(1, End())))
assert(list.map(_ / 3) == dividedListResult);


sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }

  def map[B](fn: A => B): Maybe[B] =
    this match {
      case Full(v) => Full(fn(v))
      case Empty() => Empty[B]()
    }

  def mapFlatMap[B](fn: A => B): Maybe[B] =
    flatMap[B](v => Full(fn(v)))

}

final case class Full[A](value: A) extends Maybe[A]

final case class Empty[A]() extends Maybe[A]


val full = Full("User")
val flatMapRes = full.flatMap(user => Full("dishes"))
assert(flatMapRes == Full("dishes"))

val mapRes = full.map(_ * 2)
assert(mapRes == Full("UserUser"))


val mapFlatMapRes = Full("User").flatMap(user => Full("dishes"))
assert(mapFlatMapRes == Full("dishes"))


val answer = List(1, -1, 2, -2, 3, -3)
val intList = List(1, 2, 3)
val res = intList.flatMap(x => List(x, -x));

assert(res == answer)

