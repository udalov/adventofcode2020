fun main() {
    var s = readLine()!!.map { it - '0' }.toMutableList()
    val n = s.size
    repeat(100) {
        val x1 = s[1]
        val x2 = s[2]
        val x3 = s[3]
        var next = (s[0] - 2 + n) % n + 1
        while (next == x1 || next == x2 || next == x3) next = (next - 2 + n) % n + 1
        s.remove(x1)
        s.remove(x2)
        s.remove(x3)
        var dest = 1
        while (s[dest] != next) dest = (dest + 1) % s.size
        s = (s.subList(0, dest + 1) + listOf(x1, x2, x3) + s.subList(dest + 1, n - 3)).toMutableList()
        s = (s.subList(1, s.size) + listOf(s[0])).toMutableList()
    }
    while (s[0] != 1) 
        s = (s.subList(1, s.size) + listOf(s[0])).toMutableList()
    println(s.joinToString("").substring(1))
}
