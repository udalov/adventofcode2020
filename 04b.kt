val needed = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun List<String>.isValid(): Boolean {
    val p = mutableSetOf<String>()
    for (s in this) {
        for (f in s.split(" ")) {
            p.add(f.substringBefore(":"))
        }
    }
    if ((needed - p).isNotEmpty()) return false
    for (s in this) {
        for (f in s.split(" ")) {
            val key = f.substringBefore(":")
            val value = f.substringAfter(":")
            if (!when (key) {
                "byr" -> checkDigits(value, 4, 1920, 2002)
                "iyr" -> checkDigits(value, 4, 2010, 2020)
                "eyr" -> checkDigits(value, 4, 2020, 2030)
                "hgt" -> when (value.substring(value.length - 2, value.length)) {
                    "cm" -> checkDigits(value.substring(0, value.length - 2), 3, 150, 193)
                    "in" -> checkDigits(value.substring(0, value.length - 2), 2, 59, 76)
                    else -> false
                }
                "hcl" -> value.length == 7 && value[0] == '#' && value.substring(1).all { it in "0123456789abcdef" }
                "ecl" -> value in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                "pid" -> value.length == 9 && value.all { it.isDigit() }
                else -> true
            }) return false
        }
    }
    return true
}

fun checkDigits(s: String, size: Int, lo: Int, hi: Int): Boolean =
    s.length == size && s.toInt() in lo..hi

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
