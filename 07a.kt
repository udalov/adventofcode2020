fun main() {
    val a = generateSequence(::readLine).toList()
    val g = mutableMapOf<String, MutableList<String>>()
    for (line in a) {
        val from = line.substringBefore(" bags contain ")
        val to = line.substringAfter(" bags contain ").substringBefore(".").split(", ")
            .filterNot { it == "no other bags" }
            .map {
                it.substringBefore(" ").toInt() to it.substringAfter(" ").substringBeforeLast(" ")
            }
        for ((_, e) in to) g.getOrPut(from) { mutableListOf() }.add(e)
    }

    val used = mutableMapOf<String, Boolean>()
    fun dfs(v: String): Boolean {
        if (v in used) return used[v]!!
        if (v == "shiny gold") { used[v] = true; return true; }
        for (e in g[v].orEmpty()) {
            if (dfs(e)) { used[v] = true; return true; }
        }
        used[v] = false; return false
    }

    println(g.keys.count { dfs(it) } - 1)
}
