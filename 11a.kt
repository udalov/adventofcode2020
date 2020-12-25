val dx = listOf(1, 1, 0, -1, -1, -1, 0, 1)
val dy = listOf(0, 1, 1, 1, 0, -1, -1, -1)

fun List<CharArray>.hash(): Long {
    var l = 0L
    for (x in this) l = l * 366239 + x.contentHashCode()
    return l
}

fun main() {
    var a = generateSequence(::readLine).map(String::toCharArray).toList()
    val n = a.size
    val m = a[0].size
    val seen = mutableMapOf<Long, Int>()
    seen[a.hash()] = 0
    while (true) {
        val b = mutableListOf<CharArray>()
        for (i in 0 until n) b.add(a[i].copyOf())

        for (i in 0 until n) for (j in 0 until m) {
            var nn = 0
            for (d in 0 until 8) nn += if (a.getOrNull(i + dx[d])?.getOrNull(j + dy[d]) == '#') 1 else 0
            when {
                a[i][j] == 'L' && nn == 0 -> b[i][j] = '#'
                a[i][j] == '#' && nn >= 4 -> b[i][j] = 'L'
            }
        }

        a = b
        val h = a.hash()
        if (h in seen) {
            println(a.sumBy { row -> row.count { it == '#' } })
            break
        }
        seen[h] = 1
    }
}
