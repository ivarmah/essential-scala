val seq = Seq(1, 2, 3)
assert(seq.apply(0) == 1)
assert(seq.tail == List(2, 3))
assert(seq.head == 1)

val emptySeq = Seq()
assert(emptySeq.headOption == None)
assert(seq.headOption == Some(1))
assert(seq.length == 3)

assert(seq.contains(2))
assert(!seq.contains(15))

assert(seq.find(_ == 3) == Some(3))
assert(seq.find(_ > 5) == None)

assert(seq.filter(_ % 2 == 0) == List(2))

assert(seq.sortWith(_ < _) == List(1, 2, 3))
assert(seq.sortWith(_ > _) == List(3, 2, 1))

val updatedSeq = seq.:+(4)
assert(updatedSeq == Seq(1, 2, 3, 4))

assert((seq :+ 4) == List(1, 2, 3, 4))

assert((updatedSeq.+:(0)) == List(0, 1, 2, 3, 4))
assert((updatedSeq :+ 0) == List(1, 2, 3, 4, 0))
assert((0 +: updatedSeq) == List(0, 1, 2, 3, 4))
assert((seq ++ updatedSeq) == List(1, 2, 3, 1, 2, 3, 4))

val list = 1 :: 2 :: 3 :: Nil
assert(list == List(1, 2, 3))
assert((4 :: 5 :: list) == List(4, 5, 1, 2, 3))

val constructedList = List(1, 2, 3)
assert(constructedList == List(1, 2, 3))

val rightAssociativeList = List(1, 2, 3) ::: List(4, 5, 6)
assert(rightAssociativeList == List(1, 2, 3, 4, 5, 6))

import scala.collection.immutable.Vector

val vector = Vector(1, 2, 3)

import scala.collection.immutable._

val queue = Queue(1, 2, 3)

import scala.collection.immutable.Vector.apply


val appliedVector = apply(1, 2, 3)
assert(appliedVector == Vector(1, 2, 3))


// we can import things anywhere
def someMethod = {
  import scala.collection.immutable.Vector.empty
  // `empty` is bound to `Vector.empty` here
  empty[Int]
}


assert(rightAssociativeList.mkString == "123456")
assert(rightAssociativeList.toString == "List(1, 2, 3, 4, 5, 6)")

val animals: Seq[String] = Seq("cat", "dog", "penguin")
assert(("mouse" +: animals :+ "tyrannosaurus").mkString == "mousecatdogpenguintyrannosaurus")
assert((1 +: animals :+ "tyrannosaurus").head == 1)


/////
val sequence = Seq(1, 2, 3)
assert(sequence.map(_ * 2) == List(2, 4, 6))
assert(sequence.map(elt => elt * 2) == List(2, 4, 6))

assert("dog".permutations.toList.toString == "List(dog, dgo, odg, ogd, gdo, god)")

val permutations = Seq("a", "ab", "abcd").map(_.permutations.toList)
assert(
  permutations.toString
    ==
    "List(List(a), List(ab, ba), List(abcd, abdc, acbd, acdb, adbc, adcb, bacd, badc, bcad, bcda, bdac, bdca, cabd, cadb, cbad, cbda, cdab, cdba, dabc, dacb, dbac, dbca, dcab, dcba))"
)

val flatPermutations = Seq("a", "ab", "abcd").flatMap(_.permutations.toList)
assert(flatPermutations.toString == "List(a, ab, ba, abcd, abdc, acbd, acdb, adbc, adcb, bacd, badc, bcad, bcda, bdac, bdca, cabd, cadb, cbad, cbda, cdab, cdba, dabc, dacb, dbac, dbca, dcab, dcba)")

assert(Seq(1, 2, 3).flatMap(num => Seq(num, num * 10)) == List(1, 10, 2, 20, 3, 30))
assert(Vector(1, 2, 3).flatMap(num => Seq(num, num * 10)) == Vector(1, 10, 2, 20, 3, 30))


assert(Seq(1, 2, 3).foldLeft(0)(_ + _) == 6) // Operations: (((0 + 1) + 2) + 3)   Band(B, A) => B
assert(Seq(1, 2, 3).foldRight(0)(_ + _) == 6) // Operations: (1 + (2 + (3 + 0)))  Band(A, B) => B

List(1, 2, 3).foreach(num => println("And a " + num + "..."))


//

def smallestElement(seq: Seq[Int]): Int = {
  seq.sortWith((a, b) => a < b).head
}

def smallestElementV2(seq: Seq[Int]): Int = {
  seq.foldLeft(Int.MaxValue)(math.min)
}

assert(smallestElement(Seq(12312, 2, 3)) == 2)
assert(smallestElementV2(Seq(12312, 2, 3)) == 2)


def uniq(seq: Seq[Int]): Seq[Int] = {
  seq
    .foldLeft(Seq.empty[Int])((b: Seq[Int], a: Int) => if (b.contains(a)) b else a +: b)
    .sortWith(_ < _)
}

val notUniqueSequence = Seq(1, 1, 1, 2, 3, 4, 4, 4)

assert(uniq(notUniqueSequence) == Seq(1, 2, 3, 4))
assert(notUniqueSequence.distinct == Seq(1, 2, 3, 4))



def reverse(seq: Seq[Int]): Seq[Int] = {
  seq
    .foldLeft(Seq.empty[Int])((b: Seq[Int], a: Int) => a +: b)
}

assert(reverse(Seq(1, 2, 3, 4)) == Seq(4, 3, 2, 1))
assert(reverse(Seq(1, 2, 3, 4)) == List(4, 3, 2, 1))


def map[A, B](seq: Seq[A], operation: A => B): Seq[B] = {
  seq.foldLeft(Seq.empty[B])(_ :+ operation(_))
}

assert(map(Seq(1, 2, 3, 4), (a: Int) => a * 2) == Seq(2, 4, 6, 8))


def foldLeft[A, B](seq: Seq[A], zero: B, f: (B, A) => B): B = {
  var state = zero
  seq.foreach(el => state = f(state, el))
  state
}

assert(foldLeft(Seq(1, 2, 3, 4), 0, (a: Int, b: Int) => (a + b)) == 10)