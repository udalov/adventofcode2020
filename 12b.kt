fun main() {
    val a = generateSequence(::readLine).toList()
    var x = 0
    var y = 0
    var wx = 10
    var wy = 1
    for (line in a) {
        val n = line.substring(1).toInt()
        var d = 0
        when (line[0]) {
            'N' -> wy += n
            'S' -> wy -= n
            'E' -> wx += n
            'W' -> wx -= n
            'L' -> d = (d + n / 90) % 4
            'R' -> d = (d - n / 90 + 4) % 4
            'F' -> {
                x += n * wx
                y += n * wy
            }
        }
        repeat(d) {
            wx = -wy.also { wy = wx }
        }
    }
    println(Math.abs(x) + Math.abs(y))
}
