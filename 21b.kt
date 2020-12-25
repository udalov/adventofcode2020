fun main() {
    val lines = generateSequence(::readLine).toList()
    val a = mutableListOf<MutableList<String>>()
    val g = mutableListOf<List<String>>()
    for (line in lines) {
        val it = line.substringBefore(" (").split(" ").toMutableList()
        a.add(it)
        g.add(line.substringAfter("(contains ").substringBefore(")").split(", "))
    }

    val m = mutableMapOf<String, MutableSet<String>>()
    for (i in a.indices) {
        for (s in a[i]) m.getOrPut(s) { mutableSetOf() }.addAll(g[i])
    }

    for ((s, gs) in m) {
        if (gs.none { z -> a.indices.all { j ->
            z !in g[j] || s in a[j]
        } }) {
            for (x in a) x.remove(s)
        }
    }

    val left = a.flatten().toSet().toList()
    val right = g.flatten().sorted().toSet().toList()
    val n = left.size
    check(n == right.size)
    val p = (0 until n).toList().toTypedArray().toIntArray()
    // for (i in a.indices) println("${a[i]} | ${g[i]}")
    // println(left)
    // println(right)
    while (true) {
        if ((0 until n).all { i ->
            a.indices.all { j ->
                left[p[i]] in a[j] || right[i] !in g[j]
            }
        }) {
            println((0 until n).joinToString(",") { left[p[it]] })
        }
        var i = n - 1
        while (i > 0 && p[i - 1] > p[i]) i--
        if (i == 0) break
        var k = n - 1
        while (p[k] < p[i - 1]) k--
        p[i - 1] = p[k].also { p[k] = p[i - 1] }
        for (j in 0 until (n - i) / 2) p[i + j] = p[n - 1 - j].also { p[n - 1 - j] = p[i + j] }
    }
}
