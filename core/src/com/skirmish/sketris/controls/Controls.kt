package com.skirmish.sketris.controls

import com.badlogic.gdx.Input
import com.skirmish.sketris.controls.gamepad.XboxMapping

/*
 * As this doesn't change frequently like most game state, we can maintain immutability.
 * This helps prevent bugs and makes behaviour more predictable, and also means each instance represents
 * a "state" from the time it was captured.
 * To make changes to the controls, you can use the "copy" method provided by data classes.
 * The companion object _is_ mutable, which means you can then update the playerControls map with the new controls
 * instance, and let the old one be garbage collected when it's no longer needed.
 * Controls updates happen outside of gameplay, which means that frequent GC spikes are not something we have to worry
 * about either.
 */
data class Controls(
    val moveLeft: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.LEFT),
        GamepadAxisBinding(0, XboxMapping.L_STICK_HORIZONTAL_AXIS, -1),
        GamepadButtonBinding(0, XboxMapping.DPAD_LEFT)
    ),
    val moveRight: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.RIGHT),
        GamepadAxisBinding(0, XboxMapping.L_STICK_HORIZONTAL_AXIS, 1),
        GamepadButtonBinding(0, XboxMapping.DPAD_RIGHT)
    ),
    val rotateCounterClockwise: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.Z),
        GamepadButtonBinding(0, XboxMapping.A)
    ),
    val rotateClockwise: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.X),
        GamepadButtonBinding(0, XboxMapping.B)
    ),
    val hardDrop: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.SPACE),
        GamepadAxisBinding(0, XboxMapping.L_STICK_VERTICAL_AXIS, -1),
        GamepadButtonBinding(0, XboxMapping.DPAD_UP)
    ),
    val softDrop: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.DOWN),
        GamepadAxisBinding(0, XboxMapping.L_STICK_VERTICAL_AXIS, 1),
        GamepadButtonBinding(0, XboxMapping.DPAD_DOWN)
    ),
    val hold: ControlBinding = AggregateBinding(
        KeyBinding(Input.Keys.C),
        GamepadButtonBinding(0, XboxMapping.L_BUMPER),
        GamepadButtonBinding(0, XboxMapping.R_BUMPER)
    )
) {
    companion object {
        val defaultControls = Controls()
        val playerControls: MutableMap<Int, Controls> = mutableMapOf(0 to defaultControls)
    }
}