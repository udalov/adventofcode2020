fun main() {
    val a = readLine()!!.split(",").map(String::toInt)
    val t = mutableMapOf<Int, Int>()
    val tt = mutableMapOf<Int, Int>()
    for (i in a.indices) t[a[i]] = i
    var x = 0
    for (i in a.size until 30000000) {
        val tx = t[x]
        val ttx = tt[x]
        x = if (tx != null && ttx != null) tx - ttx else 0
        t[x]?.let { tt[x] = it }
        t[x] = i
    }
    println(x)
}
