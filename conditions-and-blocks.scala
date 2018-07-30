if (1 < 2) println("Yes") else println("No")

val blockValue = {
  println("side effect 1")
  println("side effect 2")
  println("side effect 3")
  3
}

assert(blockValue == 3)
assert((if(1 > 2) "alien" else 2018) == 2018)
println(if(1 > 2) "alien")