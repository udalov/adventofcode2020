val r = "(.+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".toRegex()

class C(val name: String, val a1: Int, val b1: Int, val a2: Int, val b2: Int)

fun main() {
    val a = generateSequence(::readLine).toList()
    val sep = a.indexOf("")
    val cs = a.subList(0, sep)
    val my = a[sep + 2].split(",").map(String::toInt)
    val nearby = a.subList(sep + 5, a.size).map { it.split(",").map(String::toInt) }
    val b = BooleanArray(1000)
    val z = mutableListOf<C>()
    for (t in cs) {
        val (name, a1, b1, a2, b2) = r.matchEntire(t)!!.destructured
        for (i in a1.toInt()..b1.toInt()) b[i] = true
        for (i in a2.toInt()..b2.toInt()) b[i] = true
        z += C(name, a1.toInt(), b1.toInt(), a2.toInt(), b2.toInt())
    }
    val tickets = listOf(my) + nearby.filter { t -> t.none { !b[it] } }

    val n = z.size
    val m = Array<BooleanArray>(n) { i ->
        BooleanArray(n) { j ->
            tickets.all { ticket ->
                ticket[j] in z[i].a1..z[i].b1 || ticket[j] in z[i].a2..z[i].b2
            }
        }
    }
    fun go(i: Int, mask: Int): Long? {
        if (i == n) return 1
        for (j in 0 until n) if (mask and (1 shl j) == 0 && m[i][j]) {
            val r = go(i + 1, mask or (1 shl j))
            if (r != null) return r * (if (z[i].name.startsWith("departure")) my[j] else 1)
        }
        return null
    }
    println(go(0, 0))
}
