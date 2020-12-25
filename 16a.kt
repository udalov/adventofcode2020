val r = "(.+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".toRegex()

fun main() {
    val a = generateSequence(::readLine).toList()
    val sep = a.indexOf("")
    val cs = a.subList(0, sep)
    val my = a[sep + 2].split(",").map(String::toInt)
    val nearby = a.subList(sep + 5, a.size).map { it.split(",").map(String::toInt) }
    val b = BooleanArray(1000)
    for (t in cs) {
        val (_, a1, b1, a2, b2) = r.matchEntire(t)!!.destructured
        for (i in a1.toInt()..b1.toInt()) b[i] = true
        for (i in a2.toInt()..b2.toInt()) b[i] = true
    }
    var ans = 0
    for (t in nearby) {
        for (x in t) if (!b[x]) ans += x
    }
    println(ans)
}
