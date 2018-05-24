package com.applemann.arkanoid

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.Batch
import java.awt.Image

val screenWidth = Gdx.graphics.width
val screenHeight = Gdx.graphics.height

enum class ImagePath (val value: String) {
    BALL("core/assets/ball.png"),
    PAD("core/assets/pad.png"),
    BRICK("core/assets/brick.png"),
}

class Ball(texture: Texture?) : Sprite(texture) {
    var speed: Int = 10

    fun update (batch: Batch) {
        this.draw(batch)
    }
}

class Pad(texture: Texture?) : Sprite(texture) {
    var speed: Int = 8

    fun update (batch: Batch) {
        this.draw(batch)

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.x = this.x + speed
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.x = this.x - speed
        }

        if (this.x < 0 ) {
            this.x += speed
        }
        else if (this.x + this.width > screenWidth ) {
            this.x -= speed
        }
    }
}


class GdxArkanoid : ApplicationAdapter() {
    lateinit var batch: Batch
    lateinit var ball : Ball
    lateinit var pad : Pad
    lateinit var brick : Sprite



    override fun create() {
        batch = SpriteBatch()
        pad = Pad( Texture(ImagePath.PAD.value) )
        pad.setPosition(screenWidth / 2 - pad.width / 2, 0f)

        ball = Ball( Texture(ImagePath.BALL.value) )
        ball.setPosition( (screenWidth/2 - ball.width), 50f)
    }

    override fun render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()

        pad.update(batch)
        ball.update(batch)

        batch.end()
    }

    override fun dispose() {
        batch.dispose()

    }
}
