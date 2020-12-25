import Rule.*

sealed class Rule {
    class Term(val s: String) : Rule()
    class Non(val x: List<List<Int>>) : Rule()
}

fun go0(s: String, i: Int, j: Int, n: Int, rules: Map<Int, Rule>, mem: MutableMap<Triple<Int, Int, Int>, Boolean>): Boolean {
    val key = Triple(i, j, n)
    val cached = mem[key]
    if (cached != null) return cached

    val rule = rules[n]
    if (rule is Term) {
        val ans = s.substring(i, j) == rule.s
        mem[key] = ans
        return ans
    }

    for (alt in (rule as Non).x) {
        when (alt.size) {
            1 -> {
                val cur = go0(s, i, j, alt[0], rules, mem)
                if (cur) {
                    mem[key] = true
                    return true
                }
            }
            2 -> {
                for (k in (i + 1)..j) {
                    val cur = go0(s, i, k, alt[0], rules, mem) && go0(s, k, j, alt[1], rules, mem)
                    if (cur) {
                        mem[key] = true
                        return true
                    }
                }
            }
            3 -> {
                for (k1 in (i + 1)..j) for (k2 in (k1 + 1)..j) {
                    val cur = go0(s, i, k1, alt[0], rules, mem) &&
                        go0(s, k1, k2, alt[1], rules, mem) &&
                        go0(s, k2, j, alt[2], rules, mem)
                    if (cur) {
                        mem[key] = true
                        return true
                    }
                }
            }
            else -> error(alt.size)
        }
    }

    mem[key] = false
    return false
}

fun go(s: String, rules: Map<Int, Rule>): Boolean {
    return go0(s, 0, s.length, 0, rules, mutableMapOf())
}

fun main() {
    val rules = mutableMapOf<Int, Rule>()
    while (true) {
        val line = readLine()!!
        if (line.isEmpty()) break
        val i = line.substringBefore(": ").toInt()
        val rest = line.substringAfter(": ")
        if (rest.startsWith("\"")) {
            val s = rest[1] + ""
            rules[i] = Rule.Term(s)
        } else {
            val rs = rest.split(" | ").map { list -> list.split(" ").map(String::toInt) }
            rules[i] = Rule.Non(rs)
        }
    }

    rules[8] = Rule.Non(listOf(listOf(42), listOf(42, 8)))
    rules[11] = Rule.Non(listOf(listOf(42, 31), listOf(42, 11, 31)))

    var ans = 0
    while (true) {
        val line = readLine() ?: break
        if (go(line, rules)) {
            ans++
            println(line)
        }
    }
    println(ans)
}
