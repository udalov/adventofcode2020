fun main() {
    val a = generateSequence({ readLine()?.toInt() }).toList()
    for (x in a) for (y in a) for (z in a) if (x + y + z == 2020) { println(x * y * z); return }
}
