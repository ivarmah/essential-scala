import scala.annotation.tailrec

sealed trait IntList{
  def length(): Int = this match{
    case End => 0
    case Pair(hd, tl) =>  1 + tl.length
  }

  def double: IntList = this match {
      case End => End
      case Pair(hd, tl) => Pair(hd * 2, tl.double)
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
def tailRecursiveSum(list: IntList, total: Int = 0): Int = list match {
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

//
assert(example.double == Pair(2, Pair(4, Pair(6, End))))
assert(example.tail.double == Pair(4, Pair(6, End)))
assert(End.double == End)


object TreeOps {
  def sum(tree: Tree): Int =
    tree match {
      case Leaf(elt) => elt
      case Node(l, r) => sum(l) + sum(r)
    }
  def double(tree: Tree): Tree =
    tree match {
      case Leaf(elt) => Leaf(elt * 2)
      case Node(l, r) => Node(double(l), double(r))
    }
}


sealed trait Tree{
  def sum: Int
  def double: Tree
}

final case class Node(val l: Tree, val r: Tree) extends Tree{
  def sum: Int = l.sum + r.sum
  def double: Tree = Node(l.double, r.double)
}

final case class Leaf(val element: Int) extends Tree{
  def sum: Int = element
  def double: Tree = Leaf(element * 2)
}


assert(TreeOps.sum(Node(Leaf(2), Leaf(3))) == 5)
assert(TreeOps.sum(Node(Node(Leaf(2),Leaf(1)), Leaf(3))) == 6)

assert(TreeOps.double(Node(Leaf(2), Leaf(3))) == Node(Leaf(4), Leaf(6)))
