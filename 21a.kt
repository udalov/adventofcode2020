fun main() {
    val lines = generateSequence(::readLine).toList()
    val a = mutableListOf<List<String>>()
    val g = mutableListOf<List<String>>()
    for (line in lines) {
        val it = line.substringBefore(" (").split(" ")
        a.add(it)
        g.add(line.substringAfter("(contains ").substringBefore(")").split(", "))
    }

    val m = mutableMapOf<String, MutableSet<String>>()
    for (i in a.indices) {
        for (s in a[i]) m.getOrPut(s) { mutableSetOf() }.addAll(g[i])
    }

    var ans = 0
    for ((s, gs) in m) {
        if (gs.none { z -> a.indices.all { j ->
            z !in g[j] || s in a[j]
        } }) {
            ans += a.sumBy { if (s in it) 1 else 0 }
        }
    }
    println(ans)
}
