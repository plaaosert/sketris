package com.skirmish.sketris.mino

class MinoRotation(
    val data: Array<BooleanArray>
) {
    companion object {
        fun parse(data: String): MinoRotation {
            return MinoRotation(
                data.split("\n").reversed().map { line ->
                    line.map { char ->
                        when (char) {
                            '#' -> true
                            else -> false
                        }
                    }.toBooleanArray()
                }.toTypedArray()
            )
        }
    }

    val width: Int = data.filter { it.contains(true) }.maxOf { row -> row.lastIndexOf(true) }
    val height: Int = data.indexOfLast { it.contains(true) } - data.indexOfFirst { it.contains(true) }
    val offset: Int = data.filter { it.contains(true) }.minOf { row -> row.indexOf(true) }
}