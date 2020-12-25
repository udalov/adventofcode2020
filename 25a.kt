const val mod = 20201227L

fun main() {
    val cpk = readLine()!!.toLong()
    val dpk = readLine()!!.toLong()

    var cls = 0L
    var k = 1L
    while (k != cpk) {
        k = (k * 7) % mod
        cls++
    }

    k = 1L
    repeat(cls.toInt()) {
        k = (k * dpk) % mod
    }
    println(k)
}
