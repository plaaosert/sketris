package com.skirmish.sketris.controls

sealed interface DeadZone {
    object Default : DeadZone {
        override var value: Float = 0.3f
    }

    sealed class DelegatingDeadZone(var delegate: DeadZone) : DeadZone {
        override var value: Float by delegate::value
    }

    companion object : DelegatingDeadZone(Default)

    var value: Float
}