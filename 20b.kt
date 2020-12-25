import kotlin.math.*

fun List<String>.groups(): List<List<String>> =
    (listOf(-1) + indices.filter { this[it].isEmpty() } + listOf(size)).windowed(2).map { (i, j) -> subList(i + 1, j) }

class Tile(val k: Int, val cells: List<List<String>>) {
    fun side(orientation: Int, sideIndex: Int): String = when (sideIndex) {
        0 -> cells[orientation].first()
        1 -> cells[orientation].fold("") { acc, s -> acc + s.last() }
        2 -> cells[orientation].last().reversed()
        3 -> cells[orientation].fold("") { acc, s -> s.first() + acc }
        else -> error(sideIndex)
    }
}

val tiles = mutableListOf<Tile>()
var corner = -1

fun orientedSide(i: Int, orient: IntArray, sideIndex: Int): String =
    tiles[i].side(orient[i], sideIndex)

fun solve(i: Int, j: Int, a: Array<IntArray>, orient: IntArray): Boolean {
    if (i == a.size) return true
    for (k in tiles.indices) if (orient[k] == -1 && (i != 0 || j != 0 || k == corner)) {
        for (orientation in 0 until 16) {
            if (j > 0 && orientedSide(a[i][j - 1], orient, 1) != tiles[k].side(orientation, 3).reversed() ||
                i > 0 && orientedSide(a[i - 1][j], orient, 2) != tiles[k].side(orientation, 0).reversed()
            ) continue
            a[i][j] = k
            orient[k] = orientation
            if (when {
                j == a[i].size - 1 -> solve(i + 1, 0, a, orient)
                else -> solve(i, j + 1, a, orient)
            }) return true
            a[i][j] = -1
        }
        orient[k] = -1
    }
    return false
}

fun List<String>.rotate(): List<String> =
    first().indices.map { i -> indices.map { j -> this[size - 1 - j][i] }.joinToString("") }

fun main() {
    for (group in generateSequence(::readLine).toList().groups()) {
        val k = group.first().substringAfter("Tile ").substringBefore(":").toInt()
        var tile = group.subList(1, group.size)
        val cells = mutableListOf<List<String>>()
        for (flip in 0 until 4) {
            for (rev in 0 until 4) {
                cells.add(tile)
                tile = tile.rotate()
            }
            if (flip % 2 == 0) tile = tile.map(String::reversed)
            if (flip >= 2) tile = tile.reversed()
        }
        tiles.add(Tile(k, cells))
    }

    corner = tiles.indices.first { i ->
        (0 until 4).sumBy { sideIndex ->
            tiles.count { other ->
                tiles[i].side(0, sideIndex) in (0 until 16).flatMap { orientation ->
                    (0 until 4).map { other.side(orientation, it) }
                }
            }
        } == 6
    }

    val m = sqrt(tiles.size.toDouble()).roundToInt()
    val a = Array(m) { IntArray(m) { -1 } }
    val orient = IntArray(tiles.size) { -1 }
    check(solve(0, 0, a, orient))

    val n = orientedSide(0, orient, 0).length - 2
    val f = List(m * n) { i ->
        CharArray(m * n) { j ->
            val index = a[i / n][j / n]
            tiles[index].cells[orient[index]][i % n + 1][j % n + 1]
        }
    }

    var monster = listOf(
        "                  # ",
        "#    ##    ##    ###",
        " #  #  #  #  #  #   ",
    )
    for (flip in 0 until 4) {
        for (rev in 0 until 4) {
            for (i in f.indices) for (j in f[i].indices) {
                if (i + monster.size <= f.size && j + monster[0].length <= f[i].size &&
                    monster.indices.all { x ->
                        monster[x].indices.all { y ->
                            monster[x][y].let { it == ' ' || it == f[i + x][j + y] }
                        }
                    }
                ) {
                    for (x in monster.indices) for (y in monster[x].indices) {
                        if (monster[x][y] == '#') f[i + x][j + y] = 'O'
                    }
                }
            }

            monster = monster.rotate()
        }
        if (flip % 2 == 0) monster = monster.map(String::reversed)
        if (flip >= 2) monster = monster.reversed()
    }

    println(f.sumBy { it.count('#'::equals) })
}
