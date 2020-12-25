val r = "mem\\[(\\d+)] = (\\d+)".toRegex()

fun main() {
    val a = generateSequence(::readLine).toList()
    var mask = ""
    val mem = LongArray(100000)
    for (line in a) {
        when {
            line.startsWith("mask = ") -> mask = line.substringAfter("mask = ").reversed()
            else -> {
                val (index, value) = r.matchEntire(line)!!.destructured
                var x = 0L
                for (i in 0 until 36) {
                    x += when (mask[i]) {
                        '0' -> 0
                        '1' -> 1L shl i
                        'X' -> ((value.toLong() shr i) and 1) shl i
                        else -> error(mask[i])
                    }
                }
                mem[index.toInt()] = x
            }
        }
    }
    println(mem.reduce { a, b -> a + b })
}
