package com.skirmish.sketris

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.skirmish.sketris.screen.MainScreen
import com.skirmish.sketris.settings.Settings

class Sketris : Game() {

    lateinit var assetManager: AssetManager
    lateinit var settings: Settings

    override fun create() {
        settings = Settings()

        assetManager = AssetManager()
        assetManager.load("graphic/minos/mino0.png", Texture::class.java)
        assetManager.load("graphic/minos/mino1.png", Texture::class.java)
        assetManager.load("graphic/minos/mino2.png", Texture::class.java)
        assetManager.load("graphic/minos/mino3.png", Texture::class.java)
        assetManager.load("graphic/minos/mino4.png", Texture::class.java)
        assetManager.load("graphic/minos/mino5.png", Texture::class.java)
        assetManager.load("graphic/minos/mino6.png", Texture::class.java)
        assetManager.load("graphic/minos/mino7.png", Texture::class.java)
        assetManager.load("graphic/minos/mino8.png", Texture::class.java)
        assetManager.load("graphic/minos/mino9.png", Texture::class.java)
        assetManager.load("graphic/minos/empty.png", Texture::class.java)
        assetManager.finishLoading()

        setScreen(MainScreen(assetManager, settings))
    }

    override fun dispose() {
        screen.dispose()
    }
}