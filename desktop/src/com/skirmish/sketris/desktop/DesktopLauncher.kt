package com.skirmish.sketris.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.skirmish.sketris.Sketris

fun main() {
    val config = LwjglApplicationConfiguration()
    config.width = 1280
    config.height = 720
    LwjglApplication(Sketris(), config)
}