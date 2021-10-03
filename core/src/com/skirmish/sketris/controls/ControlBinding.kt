package com.skirmish.sketris.controls

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.controllers.Controllers
import kotlin.math.absoluteValue
import kotlin.math.sign

sealed interface ControlBinding {
    val isPressed: Boolean
}

class KeyBinding(val key: Int) : ControlBinding {
    override val isPressed: Boolean
        get() = Gdx.input.isKeyPressed(key)
}

class GamepadButtonBinding(val gamepad: Int, val button: Int) : ControlBinding {
    override val isPressed: Boolean
        get() = Controllers.getControllers()[gamepad]?.getButton(button) ?: false
}

class GamepadAxisBinding(val gamepad: Int, val axis: Int, val direction: Int) : ControlBinding {
    override val isPressed: Boolean
        get() {
            val axisValue = Controllers.getControllers()[gamepad]?.getAxis(axis) ?: return false
            return axisValue.absoluteValue > DeadZone.value && axisValue.sign.toInt() == direction.sign
        }
}

class AggregateBinding(vararg val bindings: ControlBinding) : ControlBinding {
    override val isPressed: Boolean
        get() = bindings.any(ControlBinding::isPressed)
}