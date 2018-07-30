if (1 < 2) println("Yes") else println("No")

val blockValue = {
  println("side effect 1")
  println("side effect 2")
  println("side effect 3")
  3
}

println("Block value:", blockValue);

// return type is ANY
println(if(1 > 2) "alien" else 2018)

// return type is Unit ()
println(if(1 > 2) "alien")