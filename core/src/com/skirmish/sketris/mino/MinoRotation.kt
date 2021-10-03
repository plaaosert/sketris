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
}