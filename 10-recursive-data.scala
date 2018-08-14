import scala.annotation.tailrec

sealed trait IntList{
  def length(): Int = this match{
    case End => 0
    case Pair(hd, tl) =>  1 + tl.length
  }
}
final case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList{
}

val example = Pair(1, Pair(2, Pair(3, End)))

// recursive call
def recursiveSum(list: IntList): Int =
  list match {
    case End => 0
    case Pair(hd, tl) => hd + recursiveSum(tl)
  }

// tail recursion
@tailrec
def tailRecursiveSum(list: IntList, total: Int = 0): Int =
  list match {
    case End => total
    case Pair(hd, tl) => tailRecursiveSum(tl, total + hd)
  }

// testing recursion
assert(recursiveSum(example) == 6)
assert(recursiveSum(example.tail) == 5)
assert(recursiveSum(End) == 0)

// testing tail recursion
assert(tailRecursiveSum(example) == 6)
assert(tailRecursiveSum(example.tail) == 5)
assert(tailRecursiveSum(End) == 0)

assert(example.length == 3)
assert(example.tail.length == 2)
assert(End.length == 0)


