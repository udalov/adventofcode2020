fun main() {
    val a = generateSequence(::readLine).toList()
    var i = 0
    var acc = 0
    val v = mutableSetOf<Int>()
    while (true) {
        if (!v.add(i)) {
            println(acc)
            break
        }
        val inst = a[i].substringBefore(" ")
        val num = a[i].substringAfter(" ").let { if (it.startsWith("+")) it.substring(1) else it }.toInt()
        when (inst) {
            "nop" -> i++
            "acc" -> { acc += num; i++ }
            "jmp" -> { i += num }
        }
    }
}
