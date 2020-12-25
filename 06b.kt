fun solve(a: List<String>): Int {
    var ans = 0
    for (c in 'a'..'z') {
        if (a.all { c in it }) ans++
    }
    return ans
}

fun main() {
    val a = generateSequence(::readLine).toList()
    val n = a.size
    var i = 0
    var ans = 0
    while (i < n) {
        var j = i
        while (j < n && !a[j].isEmpty()) j++
        ans += solve(a.subList(i, j))
        i = j + 1
    }
    println(ans)
}
