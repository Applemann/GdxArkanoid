package com.applemann.arkanoid

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.Batch
import java.lang.*


val screenWidth = Gdx.graphics.width
val screenHeight = Gdx.graphics.height

enum class ImagePath (val value: String) {
    BALL("core/assets/ball.png"),
    PAD("core/assets/pad.png"),
    BRICK("core/assets/brick.png"),
}

class Ball(texture: Texture) : Sprite(texture) {
    var speed: Int = 250
    var direction: Int = 45
    var motionx: Int = 1
    var motiony: Int = 1

    constructor(texture: Texture, x: Float, y: Float) : this(texture) {
        this.setPosition(x, y)
    }

    fun update (batch: Batch) {
        this.draw(batch)

        val delta = Gdx.graphics.getDeltaTime()
        x += motionx * (Math.cos(this.direction * (Math.PI/180)) * this.speed * delta ).toFloat()
        y += motiony * (Math.sin(this.direction * (Math.PI/180)) * this.speed * delta ).toFloat()


        // Right collision
        if (this.x + this.width > screenWidth && direction > 0 ) {
            direction += 90
        }

        if (this.x + this.width > screenWidth && direction < 0 ) {
            direction -= 90
        }

        // Top collision
        if (this.y + this.height > screenHeight && direction > 90 ) {
            direction += 90
        }
        if (this.y + this.height > screenHeight && direction < 90 ) {
            direction -= 90
        }

        // Left collision
        if (this.x < 0 && direction > 180 ) {
            direction += 90
        }
        if (this.x < 0 && direction < 180 ) {
            direction -= 90
        }

        // Bottom collision
        if (this.y < 0 && direction > 270 ) {
            direction += 90
        }
        if (this.y < 0 && direction < 270 ) {
            direction -= 90
        }

        //collide = move_and_collide(motion)

    }
}

class Pad(texture: Texture?) : Sprite(texture) {
    var speed: Int = 8

    constructor(texture: Texture, x: Float, y: Float) : this(texture) {
        setPosition(x, y)
    }

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
