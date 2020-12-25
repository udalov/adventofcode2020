fun main() {
    val a = generateSequence(::readLine).map(String::toLong).toList()
    for (i in 25 until a.size) {
        if (((i-25)..(i-1)).none { x -> ((i-25)..(i-1)).any { y -> x != y && a[x] + a[y] == a[i] } }) println("$i ${a[i]}")
    }
}
