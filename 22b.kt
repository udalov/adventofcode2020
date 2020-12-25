val mem = mutableMapOf<Long, Int>()

fun hash(a: List<Int>, b: List<Int>): Long {
    var h = 0L
    for (x in a) h = h * 366239 + x
    for (x in b) h = h * 31 + x
    return h
}

fun go(a: MutableList<Int>, b: MutableList<Int>): Int {
    val key = hash(a, b)
    mem[key]?.let { return it }

    if (a.isEmpty()) return 0.also { mem[key] = it }
    if (b.isEmpty()) return 2.also { mem[key] = it }
    mem[key] = 1

    val x = a.removeAt(0)
    val y = b.removeAt(0)
    val first = if (a.size >= x && b.size >= y) {
        go(a.subList(0, x).toMutableList(), b.subList(0, y).toMutableList()) >= 1
    } else {
        x > y
    }

    if (first) {
        a.add(x)
        a.add(y)
    } else {
        b.add(y)
        b.add(x)
    }

    return go(a, b).also { mem[key] = it }
}

fun main() {
    var a = mutableListOf<Int>()
    var b = mutableListOf<Int>()
    var g: MutableList<Int> = a
    for (line in generateSequence(::readLine).toList()) {
        if (line.isEmpty()) g = b
        g.add(line.toIntOrNull() ?: continue)
    }

    go(a, b)

    var ans = 0
    for (c in listOf(a, b)) {
        for (i in c.indices) ans += (i + 1) * c[c.size - i - 1]
    }
    println(ans)
}
