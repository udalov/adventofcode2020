class V(val z: Int) {
    lateinit var next: V
}

fun main() {
    var s = readLine()!!.map { it - '0' }.toMutableList()
    while (s.size < 1000000) s.add(s.size + 1)
    val n = s.size
    val a = s.map(::V)
    for (i in a.indices) a[i].next = a[(i + 1) % n]
    val b = Array<V>(n + 1) { V(0) }
    for (v in a) b[v.z] = v
    var cur = a[0]
    repeat(10000000) {
        var x1 = cur.next
        var x2 = x1.next
        var x3 = x2.next
        var next = (cur.z - 2 + n) % n + 1
        while (next == x1.z || next == x2.z || next == x3.z) next = (next - 2 + n) % n + 1
        cur.next = x3.next
        val dest = b[next]
        val u = dest.next
        dest.next = x1
        x3.next = u
        cur = cur.next
    }
    while (cur.z != 1) cur = cur.next
    println(cur.next.z.toLong() * cur.next.next.z)
}
