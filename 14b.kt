val r = "mem\\[(\\d+)] = (\\d+)".toRegex()

fun main() {
    val a = generateSequence(::readLine).toList()
    var mask = ""
    val mem = mutableMapOf<Long, Long>()
    val float = mutableListOf<Int>()
    for (line in a) {
        when {
            line.startsWith("mask = ") -> mask = line.substringAfter("mask = ").reversed()
            else -> {
                val (index, value) = r.matchEntire(line)!!.destructured
                var x = 0L
                float.clear()
                for (i in 0 until 36) {
                    x += when (mask[i]) {
                        '0' -> ((index.toLong() shr i) and 1) shl i
                        '1' -> 1L shl i
                        'X' -> 0L.also { float.add(i) }
                        else -> error(mask[i])
                    }
                }

                fun go(i: Long, j: Int) {
                    if (j == float.size) {
                        mem[i] = value.toLong()
                        return
                    }
                    go(i, j + 1)
                    go(i + (1L shl float[j]), j + 1)
                }

                go(x, 0)
            }
        }
    }
    println(mem.values.reduce { s, t -> s + t })
}
