package com.skirmish.sketris.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.skirmish.sketris.actor.SketrisMatrix.Companion.COLUMNS
import com.skirmish.sketris.actor.SketrisMatrix.Companion.ROWS
import com.skirmish.sketris.actor.SketrisMatrix.Companion.TILE_SIZE
import com.skirmish.sketris.mino.Mino

class FloatingBlock(
    private val matrix: SketrisMatrix,
    private val mino: Mino,
    xLoc: Int,
    yLoc: Int
) : Actor() {

    private val xt = xLoc
    private val yt = yLoc

    override fun draw(batch: Batch, parentAlpha: Float) {
        for ((rowNum, row) in mino.rotation.data.withIndex()) {
            for ((colNum, tile) in row.withIndex()) {
                if (tile) {
                    val texture = matrix.minoTextures[mino.type]
                    batch.color = color

                    if (xt + colNum <= COLUMNS && yt + colNum <= ROWS) {
                        batch.draw(texture, (xt + colNum) * TILE_SIZE, (yt + rowNum) * TILE_SIZE)
                    }
                }
            }
        }
    }
}