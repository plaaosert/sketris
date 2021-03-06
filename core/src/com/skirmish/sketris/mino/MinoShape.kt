package com.skirmish.sketris.mino

import com.skirmish.sketris.mino.MinoType.*

class MinoShape(
    vararg val rotations: MinoRotation
) {

    constructor(vararg rotations: String) : this(*rotations.map(MinoRotation.Companion::parse).toTypedArray())

    companion object {
        private val z = MinoShape(
            """
                ##.
                .##
                ...
            """.trimIndent(),
            """
                ..#
                .##
                .#.
            """.trimIndent(),
            """
                ...
                ##.
                .##
            """.trimIndent(),
            """
                .#.
                ##.
                #..
            """.trimIndent()
        )

        private val l = MinoShape(
            """
                ..#
                ###
                ...
            """.trimIndent(),
            """
                .#.
                .#.
                .##
            """.trimIndent(),
            """
                ...
                ###
                #..
            """.trimIndent(),
            """
                ##.
                .#.
                .#.
            """.trimIndent()
        )

        private val o = MinoShape(
            """
                .##.
                .##.
                ....
            """.trimIndent()
        )

        private val s = MinoShape(
            """
                .##
                ##.
                ...
            """.trimIndent(),
            """
                .#.
                .##
                ..#
            """.trimIndent(),
            """
                ...
                .##
                ##.
            """.trimIndent(),
            """
                #..
                ##.
                .#.
            """.trimIndent()
        )

        private val i = MinoShape(
            """
                ....
                ####
                ....
                ....
            """.trimIndent(),
            """
                ..#.
                ..#.
                ..#.
                ..#.
            """.trimIndent(),
            """
                ....
                ....
                ####
                ....
            """.trimIndent(),
            """
                .#..
                .#..
                .#..
                .#..
            """.trimIndent()
        )

        private val j = MinoShape(
            """
                #..
                ###
                ...
            """.trimIndent(),
            """
                .##
                .#.
                .#.
            """.trimIndent(),
            """
                ...
                ###
                ..#
            """.trimIndent(),
            """
                .#.
                .#.
                ##.
            """.trimIndent()
        )

        private val t = MinoShape(
            """
                .#.
                ###
                ...
            """.trimIndent(),
            """
                .#.
                .##
                .#.
            """.trimIndent(),
            """
                ...
                ###
                .#.
            """.trimIndent(),
            """
                .#.
                ##.
                .#.
            """.trimIndent()
        )

        fun of(type: MinoType): MinoShape = when (type) {
            Z -> z
            L -> l
            O -> o
            S -> s
            I -> i
            J -> j
            T -> t
        }
    }
}