
val map: Array[Array[Int]] = Array.ofDim[Int](1000,1000)
map(0)(0)
map(0)(0) = 1
map(0)(0)

//(5 to 3)
val p1Y = 3
val p2Y = 9

val yStep = if(p1Y > p2Y) -1 else 1

p1Y to p2Y by yStep