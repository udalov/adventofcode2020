val dx = listOf(1, 1, 0, -1, -1, 0)
val dy = listOf(0, -1, -1, 0, 1, 1)

fun main() {
    val lines = generateSequence(::readLine).toList()
    val a = mutableSetOf<Pair<Int, Int>>()
    for (line in lines) {
        var i = 0
        var x = 0
        var y = 0
        while (i < line.length) {
            when (line[i++]) {
                'e' -> x++
                's' -> when (line[i++]) {
                    'e' -> y++
                    'w' -> { x--; y++ }
                }
                'w' -> x--
                'n' -> when (line[i++]) {
                    'w' -> y--
                    'e' -> { x++; y-- }
                }
            }
        }
        val k = Pair(x, y)
        if (k in a) a.remove(k) else a.add(k)
    }
    val N = 300
    var b = Array(N) { i ->
        BooleanArray(N) { j ->
            Pair(i - N/2, j - N/2) in a
        }
    }
    var t = Array(N) { BooleanArray(N) }
    repeat(100) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                var c = 0
                for (d in 0 until 6) c += if (b.getOrNull(i + dx[d])?.getOrNull(j + dy[d]) == true) 1 else 0
                t[i][j] = when {
                    b[i][j] && (c == 0 || c > 2) -> false
                    !b[i][j] && c == 2 -> true
                    else -> b[i][j]
                }
            }
        }
        for (i in 0 until N) for (j in 0 until N) b[i][j] = t[i][j]
    }
    println(b.sumBy { row -> row.count { it } })
}
