fun main() {
    val a = generateSequence(::readLine).toList()
    val t = a[0].toInt()
    val b = a[1].split(",").map { if (it == "x") -1 else it.toInt() }
    var time = t
    while (true) {
        val id = b.find { x -> x >= 0 && time % x == 0 }
        if (id != null) {
            println(id * (time - t))
            break
        }
        time++
    }
}
