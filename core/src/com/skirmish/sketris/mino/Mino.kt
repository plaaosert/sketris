package com.skirmish.sketris.mino

import com.skirmish.sketris.mino.RotationDirection.CLOCKWISE
import com.skirmish.sketris.mino.RotationDirection.COUNTER_CLOCKWISE

class Mino(
    val type: MinoType,
    private var rotationIndex: Int = 0
) {
    val shape = MinoShape.of(type)
    val rotation: MinoRotation
        get() = shape.rotations[rotationIndex]

    fun rotate(direction: RotationDirection) {
        when (direction) {
            COUNTER_CLOCKWISE -> {
                if (--rotationIndex < 0) {
                    rotationIndex = shape.rotations.size - 1
                }
            }
            CLOCKWISE -> {
                if (++rotationIndex >= shape.rotations.size) {
                    rotationIndex = 0
                }
            }
        }
    }
}