final case class Box[A](value: A)

val b = Box(2)
// res0: Box[Int] = Box(2)

assert(b.value == 2)

var box = Box("hi")
assert(box.value == "hi")

def generic[A](in: A): A = in

val stringResult = generic[String]("foo")
assert(stringResult == "foo")

val intResult = generic(1)
assert(intResult == 1)

{

  sealed trait Calculation
  final case class Success(result: Double) extends Calculation
  final case class Failure(reason: String) extends Calculation

}

  /*
----> converting to more generic approach
*/

{
  sealed trait Result[A]
  final case class Success[A](result: A) extends Result[A]
  final case class Failure[A](reason: String) extends Result[A]
}



{
  sealed trait LinkedList[A]{
    def apply(index: Int): A = {
      this match {
        case Pair(hd, tl) =>
          if(index == 0)
            hd
          else
            tl(index - 1)
        case End() =>
          throw new Exception("Attempted to get element from an Empty list") }
    }

    def length(): Int = this match{
      case End() => 0
      case Pair(hd, tl) =>  1 + tl.length
    }

    def contains(searchable: A): Boolean = this match{
      case End() => false
      case Pair(hd, tl) =>  if(hd == searchable) true else{ tl.contains(searchable) }
    }
  }
  final case class End[A]() extends LinkedList[A]
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]


  val example = Pair(1, Pair(2, Pair(3, End())))

  assert(example.length == 3)
  assert(example.tail.length == 2)
  assert(End().length == 0)

  assert(example.contains(3) == true)
  assert(example.contains(4) == false)
  assert(End().contains(0) == false)

  // This should not compile
  // example.contains("not an Int")

  assert(example(0) == 1)
  assert(example(1) == 2)
  assert(example(2) == 3)
  assert(try {
    example(3)
    false
  } catch {
    case e: Exception => true
  })
}




sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]


sealed trait LinkedList[A] {
  def apply(index: Int): Result[A] =
    this match {
      case Pair(hd, tl) =>
        if(index == 0)
          Success(hd)
        else
          tl(index - 1)
      case End() =>
        Failure("Index out of bounds")
    }
}

final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]


val example = Pair(1, Pair(2, Pair(3, End())))
assert(example(0) == Success(1))
assert(example(1) == Success(2))
assert(example(2) == Success(3))
assert(example(3) == Failure("Index out of bounds"))
