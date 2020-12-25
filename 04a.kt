val needed = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun List<String>.isValid(): Boolean {
    val p = mutableSetOf<String>()
    for (s in this) {
        for (f in s.split(" ")) {
            p.add(f.substringBefore(":"))
        }
    }
    return (needed - p).isEmpty()
}

fun main() {
    val a = arrayListOf<List<String>>()
    val s = arrayListOf<String>()
    while (true) {
        val line = readLine() ?: break
        if (line.isEmpty()) {
            a.add(s.toList())
            s.clear()
        } else {
            s.add(line)
        }
    }
    if (s.isNotEmpty()) a.add(s.toList())
    println(a.count { it.isValid() })
}
