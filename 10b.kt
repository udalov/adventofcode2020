fun main() {
    val a = generateSequence(::readLine).map(String::toInt).toSet()
    val n = a.maxOf { it }
    val b = LongArray(n + 1)
    b[0] = 1
    for (i in 1..n) {
        if (i in a) b[i] = b[i - 1] + (b.getOrNull(i - 2) ?: 0) + (b.getOrNull(i - 3) ?: 0)
    }
    println(b[n])
}
