fun solve(s: String): Long {
    val out = mutableListOf<Char>()
    val op = mutableListOf<Char>()
    for (x in s) {
        if (x.isDigit()) out.add(x)
        else if (x == '(') op.add(x)
        else if (x == ')') {
            while (op.last() != '(') {
                out.add(op.last())
                op.removeAt(op.lastIndex)
            }
            op.removeAt(op.lastIndex)
        } else {
            while (op.isNotEmpty() && op.last() == '+') {
                out.add(op.last())
                op.removeAt(op.lastIndex)
            }
            op.add(x)
        }
    }
    while (op.isNotEmpty()) {
        out.add(op.last())
        op.removeAt(op.lastIndex)
    }

    val a = mutableListOf<Long>()
    for (c in out) {
        if (c.isDigit()) a.add((c + "").toLong())
        else {
            val x = a.removeAt(a.lastIndex)
            val y = a.removeAt(a.lastIndex)
            a.add(if (c == '+') x + y else x * y)
        }
    }

    return a.single()
}

fun main() {
    var ans = 0L
    for (line in generateSequence(::readLine).toList()) {
        ans += solve(line.filterNot { it == ' ' })
    }
    println(ans)
}
