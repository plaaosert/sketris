package com.skirmish.sketris.actor

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Group
import com.skirmish.sketris.controls.Controls
import com.skirmish.sketris.mino.MinoType
import com.skirmish.sketris.mino.MinoType.*
import com.skirmish.sketris.mino.RotationDirection
import com.skirmish.sketris.queue.MinoQueue
import com.skirmish.sketris.queue.randomizer.SevenBagRandomizer
import com.skirmish.sketris.settings.Settings

class SketrisMatrix(
    private val assetManager: AssetManager,
    private val settings: Settings
) : Group() {

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
    public val zMinoTexture = assetManager.get<Texture>("graphic/minos/mino1.png")
    private val lMinoTexture = assetManager.get<Texture>("graphic/minos/mino2.png")
    private val oMinoTexture = assetManager.get<Texture>("graphic/minos/mino3.png")
    private val sMinoTexture = assetManager.get<Texture>("graphic/minos/mino4.png")
    private val iMinoTexture = assetManager.get<Texture>("graphic/minos/mino5.png")
    private val jMinoTexture = assetManager.get<Texture>("graphic/minos/mino6.png")
    private val tMinoTexture = assetManager.get<Texture>("graphic/minos/mino7.png")

    // shirase
    private val shiraseMinoTexture = assetManager.get<Texture>("graphic/minos/mino9.png")

    val minoTextures = mapOf(
        Z to zMinoTexture,
        L to lMinoTexture,
        O to oMinoTexture,
        S to sMinoTexture,
        I to iMinoTexture,
        J to jMinoTexture,
        T to tMinoTexture
    )

    private val tiles: MutableList<MutableList<MinoType?>> = MutableList(ROWS) { MutableList<MinoType?>(COLUMNS) { null } }

    // Make an active block. Need to make the class first.
    // Also need to make a ghost piece.
    // Unsure of what to do (does the active block get created by the matrix when a new piece is spawned?)
    // or do we keep a reference to a single active block (and ghost piece) and edit its info on the fly
    // good night
    private val minoQueue = MinoQueue(randomizer = SevenBagRandomizer())
    var activeMino = minoQueue.popNext()
    private val activeBlock : FloatingBlock = FloatingBlock(
        this,
        5,
        17
    )
    private val ghostBlock : FloatingBlock = FloatingBlock(
        this,
        5,
        0
    )

    private var leftPressed = false
    private var leftDasHold: Float = 0f
    private var timeToNextLeftRepeat = 0f
    private var rightPressed = false
    private var rightDasHold: Float = 0f
    private var timeToNextRightRepeat = 0f

    init {
        width = TILE_SIZE * VISIBLE_COLUMNS
        height = TILE_SIZE * VISIBLE_ROWS

        ghostBlock.color.a = 0.3f

        addActor(activeBlock)
        addActor(ghostBlock)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.setColor(1f, 1f, 1f, color.a * parentAlpha)
        for ((rowNum, row) in tiles.take(VISIBLE_ROWS).withIndex()) {
            for ((colNum, tile) in row.take(VISIBLE_COLUMNS).withIndex()) {
                // ?.                - Safe call - invokes function if value is not null, otherwise null
                // ?:                - Elvis operator (takes value on the right if value on the left is null)
                // let               - Scope function - takes value into scope as lambda parameter
                // tileTextures::get - Method reference - references the instance of map's get method
                val texture = tile?.let(minoTextures::get) ?: emptyMinoTexture
                batch.draw(texture, x + colNum * TILE_SIZE, y + rowNum * TILE_SIZE)
            }
        }

        super.draw(batch, parentAlpha)
    }

    override fun act(delta: Float) {
        super.act(delta)
        val controls = Controls.playerControls[0] ?: Controls.defaultControls
        if (controls.moveLeft.isPressed) {
            if (!leftPressed || leftDasHold > settings.das) {
                timeToNextLeftRepeat -= delta
                while (timeToNextLeftRepeat < 0f) {
                    moveLeft()
                    timeToNextLeftRepeat += settings.arr
                }
            }
            leftPressed = true
            leftDasHold += delta
        } else {
            leftPressed = false
            leftDasHold = 0f
            timeToNextLeftRepeat = 0f
        }
        if (controls.moveRight.isPressed) {
            if (!rightPressed || rightDasHold > settings.das) {
                timeToNextRightRepeat -= delta
                while (timeToNextRightRepeat < 0f) {
                    moveRight()
                    timeToNextRightRepeat += settings.arr
                }
            }
            rightPressed = true
            rightDasHold += delta
        } else {
            rightPressed = false
            rightDasHold = 0f
            timeToNextRightRepeat = 0f
        }
    }

    fun moveLeft() {
        if (--activeBlock.xt < -activeMino.rotation.offset) {
            activeBlock.xt = -activeMino.rotation.offset
        }
        if (--ghostBlock.xt < -activeMino.rotation.offset) {
            ghostBlock.xt = -activeMino.rotation.offset
        }
    }

    fun moveRight() {
        if (++activeBlock.xt + activeMino.rotation.width >= VISIBLE_COLUMNS) {
            activeBlock.xt = VISIBLE_COLUMNS - 1 - activeMino.rotation.width
        }
        if (++ghostBlock.xt + activeMino.rotation.width >= VISIBLE_COLUMNS) {
            ghostBlock.xt = VISIBLE_COLUMNS - 1 - activeMino.rotation.width
        }
    }

    fun rotatePiece(direction: RotationDirection) {
        activeMino.rotate(direction)
    }

    fun hold() {
        TODO("not yet implemented")
    }

    fun hardDrop() {
        activeMino = minoQueue.popNext()
    }

}