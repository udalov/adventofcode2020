fun row(s: String): Int =
    if (s.isEmpty()) 0
    else row(s.substring(1)) + (if (s[0] == 'B') (1 shl (s.length - 1)) else 0)

fun column(s: String): Int =
    if (s.isEmpty()) 0
    else column(s.substring(1)) + (if (s[0] == 'R') (1 shl (s.length - 1)) else 0)

fun String.id(): Int =
    row(substring(0, 7)) * 8 + column(substring(7))

fun main() {
    val a = arrayListOf<String>()
    while (true) {
        val line = readLine() ?: break
        a.add(line)
    }
    val b = a.map(String::id)
    println((1..b.maxOrNull()!!).first { i ->
        i in b && (i + 1) !in b && (i + 2) in b
    } + 1)
}
