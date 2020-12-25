fun main() {
    val a = generateSequence({ readLine()?.toInt() }).toList()
    for (x in a) for (y in a) if (x + y == 2020) { println(x * y); return }
}
