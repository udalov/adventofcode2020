fun main() {
    val a = generateSequence(::readLine).map(String::toInt).toSet()
    var cur = 0
    var p1 = 0
    var p3 = 0
    while (true) {
        if ((cur + 1) in a) {
            cur++
            p1++
        } else if ((cur + 2) in a) {
            cur += 2
        } else if ((cur + 3) in a) {
            cur += 3
            p3++
        } else break
    }
    println(p1 * (p3 + 1))
}
