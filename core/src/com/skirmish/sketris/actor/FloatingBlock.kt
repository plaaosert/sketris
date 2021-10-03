package com.skirmish.sketris.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.skirmish.sketris.actor.SketrisMatrix.Companion.COLUMNS
import com.skirmish.sketris.actor.SketrisMatrix.Companion.ROWS
import com.skirmish.sketris.actor.SketrisMatrix.Companion.TILE_SIZE

class FloatingBlock(
    private val matrix: SketrisMatrix,
    var xt: Int,
    var yt: Int
) : Actor() {

    override fun draw(batch: Batch, parentAlpha: Float) {
        val mino = matrix.activeMino
        for ((rowNum, row) in mino.rotation.data.withIndex()) {
            for ((colNum, tile) in row.withIndex()) {
                if (tile) {
                    val texture = matrix.minoTextures[mino.type]
                    batch.setColor(1f, 1f, 1f, color.a * parentAlpha)

                    if (xt + colNum <= COLUMNS && yt + colNum <= ROWS) {
                        batch.draw(texture, (xt + colNum) * TILE_SIZE, (yt + rowNum) * TILE_SIZE)
                    }
                }
            }
        }
    }
}