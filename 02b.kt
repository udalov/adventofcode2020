fun main() {
    var ans = 0
    while (true) {
        val s = readLine() ?: break
        val lo = s.substringBefore("-").toInt()
        val hi = s.substringAfter("-").substringBefore(" ").toInt()
        val c = s.substringAfter(" ").substringBefore(":").single()
        val t = s.substringAfter(": ")
        if ((t[lo - 1] == c) xor (t[hi - 1] == c)) ans++
    }
    println(ans)
}
