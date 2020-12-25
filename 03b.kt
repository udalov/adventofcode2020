fun main() {
    val ans = mutableListOf<Long>(0, 0, 0, 0, 0)
    val x = mutableListOf<Int>(0, 0, 0, 0, 0)
    var k = listOf(1, 3, 5, 7)
    var i = 0
    while (true) {
        val s = readLine() ?: break
        if (i > 0) {
            for (d in 0 until 4) {
                if (s[x[d]] == '#') ans[d]++
            }
            if (i % 2 == 0) {
                if (s[x[4]] == '#') ans[4]++
            }
        }
        for (d in 0 until 4) {
            x[d] = (x[d] + k[d]) % s.length
        }
        if (i % 2 == 1) {
            x[4] = (x[4] + 1) % s.length
        }
        i++
    }
    println(ans.reduce(Long::times))
}
