package com.skirmish.sketris.actor

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.skirmish.sketris.actor.SketrisMatrix.Tile.*

class SketrisMatrix(
    private val assetManager: AssetManager
) : Actor() {

    companion object {
        const val TILE_SIZE = 64f
        const val ROWS = 22
        const val COLUMNS = 10
        const val VISIBLE_ROWS = 20
        const val VISIBLE_COLUMNS = 10
    }

    // disabled, locked, RED, empty
    private val greyMinoTexture = assetManager.get<Texture>("graphic/minos/mino0.png")
    private val blackMinoTexture = assetManager.get<Texture>("graphic/minos/mino8.png")
    // private val blockedMinoTexture = assetManager.get<Texture>("graphic/minos/blocked.png")
    private val emptyMinoTexture = assetManager.get<Texture>("graphic/minos/empty.png")

    // rainbow
    private val zMinoTexture = assetManager.get<Texture>("graphic/minos/mino1.png")
    private val lMinoTexture = assetManager.get<Texture>("graphic/minos/mino2.png")
    private val oMinoTexture = assetManager.get<Texture>("graphic/minos/mino3.png")
    private val sMinoTexture = assetManager.get<Texture>("graphic/minos/mino4.png")
    private val iMinoTexture = assetManager.get<Texture>("graphic/minos/mino5.png")
    private val jMinoTexture = assetManager.get<Texture>("graphic/minos/mino6.png")
    private val tMinoTexture = assetManager.get<Texture>("graphic/minos/mino7.png")

    // shirase
    private val shiraseMinoTexture = assetManager.get<Texture>("graphic/minos/mino9.png")

    enum class Tile {
        Z, L, O, S, I, J, T
    }

    private val tileTextures = mapOf(
        Z to zMinoTexture,
        L to lMinoTexture,
        O to oMinoTexture,
        S to sMinoTexture,
        I to iMinoTexture,
        J to jMinoTexture,
        T to tMinoTexture
    )

    private val tiles: MutableList<MutableList<Tile?>> = MutableList(ROWS) { MutableList<Tile?>(COLUMNS) { null } }

    init {
        width = TILE_SIZE * VISIBLE_COLUMNS
        height = TILE_SIZE * VISIBLE_ROWS

        tiles[0][0] = Z
        tiles[0][1] = Z
        tiles[0][2] = L
        tiles[1][1] = J

        tiles[0][0] = L
        tiles[VISIBLE_ROWS - 1][0] = L
        tiles[0][VISIBLE_COLUMNS - 1] = L
        tiles[VISIBLE_ROWS - 1][VISIBLE_COLUMNS - 1] = L
    }

    // Make an active block. Need to make the class first.
    // Also need to make a ghost piece.
    // Unsure of what to do (does the active block get created by the matrix when a new piece is spawned?)
    // or do we keep a reference to a single active block (and ghost piece) and edit its info on the fly
    // good night
    private val activeBlock = ActiveBlock(ass)

    override fun draw(batch: Batch, parentAlpha: Float) {
        for ((rowNum, row) in tiles.take(VISIBLE_ROWS).withIndex()) {
            for ((colNum, tile) in row.take(VISIBLE_COLUMNS).withIndex()) {
                // ?.                - Safe call - invokes function if value is not null, otherwise null
                // ?:                - Elvis operator (takes value on the right if value on the left is null)
                // let               - Scope function - takes value into scope as lambda parameter
                // tileTextures::get - Method reference - references the instance of map's get method
                val texture = tile?.let(tileTextures::get) ?: emptyMinoTexture
                batch.draw(texture, x + colNum * TILE_SIZE, y + rowNum * TILE_SIZE)
            }
        }
    }

}