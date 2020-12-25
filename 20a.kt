fun List<String>.groups(): List<List<String>> =
    (listOf(-1) + indices.filter { this[it].isEmpty() } + listOf(size)).windowed(2).map { (i, j) -> subList(i + 1, j) }

class Tile(val k: Int, val sides: List<List<String>>)

fun List<String>.rotate(): List<String> =
    first().indices.map { i -> indices.map { j -> this[size - 1 - j][i] }.joinToString("") }

fun main() {
    for (group in generateSequence(::readLine).toList().groups()) {
        val k = group.first().substringAfter("Tile ").substringBefore(":").toInt()
        var tile = group.subList(1, group.size)
        val sides = mutableListOf<List<String>>()
        for (flip in 0 until 4) {
            for (rev in 0 until 4) {
                sides.add(listOf(
                    tile.first(),
                    tile.fold("") { acc, s -> acc + s.last() },
                    tile.last().reversed(),
                    tile.fold("") { acc, s -> s.first() + acc },
                ))

                tile = tile.rotate()
            }
            if (flip % 2 == 0) tile = tile.map(String::reversed)
            if (flip >= 2) tile = tile.reversed()
        }
        tiles.add(Tile(k, sides))
    }
    var ans = 1L
    for (tile in tiles) {
        if (tile.sides.first().sumBy { s -> tiles.count { s in it.sides.flatten() } } == 6)
            ans *= tile.k
    }
    println(ans)
}
