fun main() {
    val lines = generateSequence(::readLine).toList()
    val a = mutableSetOf<Pair<Int, Int>>()
    for (line in lines) {
        var i = 0
        var x = 0
        var y = 0
        while (i < line.length) {
            when (line[i++]) {
                'e' -> x++
                's' -> when (line[i++]) {
                    'e' -> y++
                    'w' -> { x--; y++ }
                }
                'w' -> x--
                'n' -> when (line[i++]) {
                    'w' -> y--
                    'e' -> { x++; y-- }
                }
            }
        }
        val k = Pair(x, y)
        if (k in a) a.remove(k) else a.add(k)
    }
    println(a.size)
}
