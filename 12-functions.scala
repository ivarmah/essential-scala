{
  val sayHi = () => "Hi!"
  assert(sayHi() == "Hi!")

  val log = (message: String) => message
  assert(log("Hi!") == "Hi!")

  val typedLog = (message: String) => message: String
  assert(log("Hi!") == "Hi!")
}


sealed trait IntList {
  def double: IntList =
    this match {
      case End => End
      case Pair(hd, tl) => Pair(hd * 2, tl.double)
    }

  def length: Int = fold(0, (hd, tl) => 1 + tl)

  def product: Int = fold(1, (hd, tl) => hd * tl)

  def sum: Int = fold(0, (hd, tl) => hd + tl)

  def fold(end: Int, f: (Int, Int) => Int): Int =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
}

final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

val example = Pair(1, Pair(2, Pair(3, End)))

assert(example.sum == 6)
assert(example.product == 6)
assert(example.length == 3)


def shortMultiply = ((_: Int) * 2)
assert(shortMultiply(2) == 4)


object MathStuff {
  def add1(num: Int) = num + 1
}

def example1(x: Int)(y: Int) = x + y
assert(example1(1)(2) == 3)


def example2(x: Int)(y: Int)(z: Int) = x + y + z
assert(example2(1)(2)(4) == 7)
