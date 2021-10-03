package com.skirmish.sketris.mino

class Mino(
    val type: MinoType,
    val rotationIndex: Int = 0
) {
    val shape = MinoShape.of(type)
    val rotation: MinoRotation
        get() = shape.rotations[rotationIndex]
}