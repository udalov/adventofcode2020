fun main() {
    var a = mutableListOf<Int>()
    var b = mutableListOf<Int>()
    var g: MutableList<Int> = a
    for (line in generateSequence(::readLine).toList()) {
        if (line.isEmpty()) g = b
        g.add(line.toIntOrNull() ?: continue)
    }

    while (a.isNotEmpty() && b.isNotEmpty()) {
        val x = a.removeAt(0)
        val y = b.removeAt(0)
        if (x > y) {
            a.add(x)
            a.add(y)
        } else {
            b.add(y)
            b.add(x)
        }
    }
    
    var ans = 0
    for (c in listOf(a, b)) {
        for (i in c.indices) ans += (i + 1) * c[c.size - i - 1]
    }
    println(ans)
}
