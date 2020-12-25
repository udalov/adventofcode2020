val dx = listOf(1, 0, -1, 0)
val dy = listOf(0, 1, 0, -1)

fun main() {
    val a = generateSequence(::readLine).toList()
    var x = 0
    var y = 0
    var d = 0
    for (line in a) {
        val n = line.substring(1).toInt()
        when (line[0]) {
            'N' -> y += n
            'S' -> y -= n
            'E' -> x += n
            'W' -> x -= n
            'L' -> d = (d + n / 90) % 4
            'R' -> d = (d - n / 90 + 4) % 4
            'F' -> {
                x += n * dx[d]
                y += n * dy[d]
            }
        }
    }
    println(Math.abs(x) + Math.abs(y))
}
