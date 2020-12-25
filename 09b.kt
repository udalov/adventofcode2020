fun main() {
    val a = generateSequence(::readLine).map(String::toLong).toList()
    for (i in 25 until a.size) {
        if (((i-25)..(i-1)).none { x -> ((i-25)..(i-1)).any { y -> x != y && a[x] + a[y] == a[i] } }) {
            for (x in 0 until a.size) for (y in x until a.size) {
                val b = a.subList(x, y + 1)
                if (b.reduce { x, y -> x + y } == a[i]) {
                    println(b.minOf { it } + b.maxOf { it })
                }
            }
        }
    }
}
