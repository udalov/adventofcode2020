fun solve(s: String): Long {
    if (s.length == 1) return s.toLong()
    var i = s.length - 1
    val (x, y) = if (s[i].isDigit()) {
        solve(s.substring(0, i - 1)) to (s[i] + "").toLong()
    } else {
        check(s[i] == ')')
        var b = 0
        do {
            if (s[i] == ')') b++ else if (s[i] == '(') b--
            i--
        } while (b != 0)
        i++
        if (i == 0) return solve(s.substring(1, s.length - 1))
        solve(s.substring(0, i - 1)) to solve(s.substring(i + 1, s.length - 1))
    }
    return when (s[i - 1]) {
        '+' -> x + y
        '*' -> x * y
        else -> error(s[i - 1])
    }
}

fun main() {
    var ans = 0L
    for (line in generateSequence(::readLine).toList()) {
        ans += solve(line.filterNot { it == ' ' })
    }
    println(ans)
}
