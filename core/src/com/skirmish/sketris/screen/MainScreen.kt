package com.skirmish.sketris.screen

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.skirmish.sketris.actor.SketrisMatrix

class MainScreen(
    private val assetManager: AssetManager
) : ScreenAdapter() {

    private val stage = Stage(FitViewport(2560f, 1440f))

    init {
        val matrix = SketrisMatrix(assetManager)
        stage.addActor(matrix)
        matrix.x = (stage.width - matrix.width) / 2f
        matrix.y = (stage.height - matrix.height) / 2f
    }

    override fun render(delta: Float) {
        act(delta)
        draw()
    }

    fun act(delta: Float) {
        stage.act(delta)
    }

    fun draw() = stage.draw()

}