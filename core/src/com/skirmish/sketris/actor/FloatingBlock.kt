package com.skirmish.sketris.actor

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor

class FloatingBlock(
        private val assetManager: AssetManager, private val matrix: SketrisMatrix, x_loc: Int, y_loc: Int
) : Actor() {
    public val xt = x_loc
    public val yt = y_loc

    private val temp_grid : Array<Array<Boolean>> = Array(4) {
        Array<Boolean>(4) {
            false
        }
    }

    init {
        temp_grid[2][1] = true
        temp_grid[2][2] = true
        temp_grid[1][2] = true
        temp_grid[1][3] = true
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        for ((rowNum, row) in temp_grid.withIndex()) {
            for ((colNum, tile) in row.withIndex()) {
                if (tile) {
                    val texture = matrix.zMinoTexture
                    batch.color = color

                    if (xt + colNum <= SketrisMatrix.COLUMNS && yt + colNum <= SketrisMatrix.ROWS) {
                        batch.draw(texture, (xt + colNum) * SketrisMatrix.TILE_SIZE, (yt + rowNum) * SketrisMatrix.TILE_SIZE)
                    }
                }
            }
        }
    }
}