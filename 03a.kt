fun main() {
    var ans = 0
    var x = 0
    var first = true
    while (true) {
        val s = readLine() ?: break
        if (!first) {
            if (s[x] == '#') ans++
        }
        x = (x + 3) % s.length
        first = false
    }
    println(ans)
}
