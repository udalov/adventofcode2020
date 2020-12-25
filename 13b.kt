fun main() {
    val a = generateSequence(::readLine).toList()
    val b = a[1].split(",").map { if (it == "x") -1 else it.toInt() }
    val c = b.withIndex().filterNot { it.value == -1 }.sortedByDescending { it.value }
        .map { (index, value) ->
            value.toLong() to ((value - index % value) % value).toLong()
        }
    var x = 0L
    var inc = 1L
    for ((mod, rem) in c) {
        while (x % mod != rem) x += inc
        inc *= mod
    }
    println(x)
}
