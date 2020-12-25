fun main() {
    val a = generateSequence(::readLine).toList()
    val n = a.size
    var i = 0
    var ans = 0
    while (i < n) {
        var j = i
        while (j < n && !a[j].isEmpty()) j++
        var s = ""
        for (x in a.subList(i, j)) s += x
        ans += s.toSet().size
        i = j + 1
    }
    println(ans)
}
