fun main() {
    val a = generateSequence(::readLine).toList()
    val g = mutableMapOf<String, MutableList<Pair<Int, String>>>()
    for (line in a) {
        val from = line.substringBefore(" bags contain ")
        val to = line.substringAfter(" bags contain ").substringBefore(".").split(", ")
            .filterNot { it == "no other bags" }
            .map {
                it.substringBefore(" ").toInt() to it.substringAfter(" ").substringBeforeLast(" ")
            }
        for (e in to) g.getOrPut(from) { mutableListOf() }.add(e)
    }

    val used = mutableMapOf<String, Int>()
    fun dfs(v: String): Int {
        if (v in used) return used[v]!!
        var ans = 1
        for ((x, e) in g[v].orEmpty()) {
            ans += x * dfs(e)
        }
        used[v] = ans; return ans
    }

    println(dfs("shiny gold") - 1)
}
