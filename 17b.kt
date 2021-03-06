const val N = 30

fun main() {
    val lines = generateSequence(::readLine).toList()
    val a = Array(N) { Array(N) { Array(N) { BooleanArray(N) } } }
    val b = Array(N) { Array(N) { Array(N) { BooleanArray(N) } } }
    for (i in lines.indices) {
        for (j in lines[i].indices) {
            a[N/2][N/2][i+N/2][j+N/2] = lines[i][j] == '#'
        }
    }
    repeat(6) {
        for (x in 0 until N) for (y in 0 until N) for (z in 0 until N) for (w in 0 until N) {
            var t = 0
            for (dx in -1..1) for (dy in -1..1) for (dz in -1..1) for (dw in -1..1) if (dx != 0 || dy != 0 || dz != 0 || dw != 0) {
                if (a.getOrNull(x + dx)?.getOrNull(y + dy)?.getOrNull(z + dz)?.getOrNull(w + dw) == true) t++
            }
            if (a[x][y][z][w]) b[x][y][z][w] = t in 2..3
            else b[x][y][z][w] = t == 3
        }
        for (x in 0 until N) for (y in 0 until N) for (z in 0 until N) for (w in 0 until N) a[x][y][z][w] = b[x][y][z][w]
    }
    var ans = 0
    for (x in 0 until N) for (y in 0 until N) for (z in 0 until N) for (w in 0 until N) if (a[x][y][z][w]) ans++
    println(ans)
}
