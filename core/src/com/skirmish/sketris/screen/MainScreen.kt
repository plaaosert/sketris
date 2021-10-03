package com.skirmish.sketris.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerAdapter
import com.badlogic.gdx.controllers.Controllers
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.skirmish.sketris.actor.SketrisMatrix
import com.skirmish.sketris.controls.*
import com.skirmish.sketris.mino.RotationDirection.CLOCKWISE
import com.skirmish.sketris.mino.RotationDirection.COUNTER_CLOCKWISE
import io.anuke.gif.GifRecorder

class MainScreen(
    private val assetManager: AssetManager
) : ScreenAdapter() {

    private val stage = Stage(FitViewport(2560f, 1440f))

    private val gifRecorder = GifRecorder(stage.batch)

    init {
        val matrix = SketrisMatrix(assetManager)
        stage.addActor(matrix)
        matrix.x = (stage.width - matrix.width) / 2f
        matrix.y = (stage.height - matrix.height) / 2f

        fun getKeyboardBindings(binding: ControlBinding) = when (binding) {
            is KeyBinding -> listOf(binding)
            is AggregateBinding -> binding.bindings.filterIsInstance<KeyBinding>()
            else -> listOf()
        }

        fun KeyBinding.matches(keycode: Int) = key == keycode

        fun List<KeyBinding>.matches(keycode: Int) = any { it.matches(keycode) }

        stage.addListener(object : InputListener() {
            override fun keyDown(event: InputEvent, keycode: Int): Boolean {
                var processed = false
                Controls.playerControls.forEach { (playerId, controls) ->
                    val hardDropBindings = getKeyboardBindings(controls.hardDrop)
                    if (hardDropBindings.matches(keycode)) {
                        matrix.hardDrop()
                        processed = true
                    }
                    val holdBindings = getKeyboardBindings(controls.hold)
                    if (holdBindings.matches(keycode)) {
                        matrix.hold()
                        processed = true
                    }
                    val rotateCounterClockwiseBindings = getKeyboardBindings(controls.rotateCounterClockwise)
                    if (rotateCounterClockwiseBindings.matches(keycode)) {
                        matrix.rotatePiece(COUNTER_CLOCKWISE)
                        processed = true
                    }
                    val rotateClockwiseBindings = getKeyboardBindings(controls.rotateClockwise)
                    if (rotateClockwiseBindings.matches(keycode)) {
                        matrix.rotatePiece(CLOCKWISE)
                        processed = true
                    }
                }
                return processed
            }
        })

        fun getGamepadBindings(binding: ControlBinding) = when (binding) {
            is GamepadButtonBinding -> listOf(binding)
            is AggregateBinding -> binding.bindings.filterIsInstance<GamepadButtonBinding>()
            else -> listOf()
        }

        fun GamepadButtonBinding.matches(controller: Controller, buttonIndex: Int) =
            gamepad == controller.playerIndex && button == buttonIndex

        fun List<GamepadButtonBinding>.matches(controller: Controller, buttonIndex: Int) =
            any { it.matches(controller, buttonIndex) }

        Controllers.addListener(object : ControllerAdapter() {
            override fun buttonDown(controller: Controller, buttonIndex: Int): Boolean {
                var processed = false
                Controls.playerControls.forEach { (playerId, controls) ->
                    val hardDropBindings = getGamepadBindings(controls.hardDrop)
                    if (hardDropBindings.matches(controller, buttonIndex)) {
                        matrix.hardDrop()
                        processed = true
                    }
                    val holdBindings = getGamepadBindings(controls.hold)
                    if (holdBindings.matches(controller, buttonIndex)) {
                        matrix.hold()
                        processed = true
                    }
                    val rotateCounterClockwiseBindings = getGamepadBindings(controls.rotateCounterClockwise)
                    if (rotateCounterClockwiseBindings.matches(controller, buttonIndex)) {
                        matrix.rotatePiece(COUNTER_CLOCKWISE)
                        processed = true
                    }
                    val rotateClockwiseBindings = getGamepadBindings(controls.rotateClockwise)
                    if (rotateClockwiseBindings.matches(controller, buttonIndex)) {
                        matrix.rotatePiece(CLOCKWISE)
                        processed = true
                    }
                }
                return processed
            }
        })
    }

    override fun render(delta: Float) {
        act(delta)
        draw()
        gifRecorder.update()
    }

    fun act(delta: Float) {
        stage.act(delta)
    }

    fun draw() = stage.draw()

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        stage.dispose()
    }

}