import Inst.*

sealed class Inst {
    data class Nop(val x: Int) : Inst()
    data class Acc(val x: Int) : Inst()
    data class Jmp(val x: Int) : Inst()
}

fun run(a: List<Inst>): Int? {
    var i = 0
    var acc = 0
    val v = mutableSetOf<Int>()
    while (true) {
        if (i !in a.indices) return acc
        if (!v.add(i)) return null
        val inst = a[i]
        when (inst) {
            is Nop -> i++
            is Acc -> { acc += inst.x; i++ }
            is Jmp -> { i += inst.x }
        }
    }
}

fun main() {
    val a = generateSequence(::readLine).map { line ->
        val inst = line.substringBefore(" ")
        val num = line.substringAfter(" ").let { if (it.startsWith("+")) it.substring(1) else it }.toInt()
        when (inst) {
            "nop" -> Nop(num)
            "acc" -> Acc(num)
            "jmp" -> Jmp(num)
            else -> error(line)
        }
    }.toMutableList()
    for (i in a.indices) {
        val s = a[i]
        a[i] = (s as? Jmp)?.let { Nop(it.x) }
            ?: (s as? Nop)?.let { Jmp(it.x) }
            ?: s
        val x = run(a)
        if (x != null) { println(x); break }
        a[i] = s
    }
}
