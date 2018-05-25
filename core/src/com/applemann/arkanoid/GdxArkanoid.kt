package com.applemann.arkanoid

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.Batch


val screenWidth = Gdx.graphics.width
val screenHeight = Gdx.graphics.height

enum class ImagePath (val value: String) {
    BALL("core/assets/ball.png"),
    PAD("core/assets/pad.png"),
    BRICK("core/assets/brick.png"),
}

class BrickGroup(x: Float, y: Float) {

    private val _bricks = mutableListOf<Sprite>()
    private val _space = 3

    fun getBricks() : List<Sprite> {
        return _bricks.toList()
    }

    init {
        //loadBricks(140f, 480f)
        loadBricks(x, y)
    }

    fun loadBricks(pos_x: Float, pos_y: Float) {
        val brickTexture = Texture(ImagePath.BRICK.value)

        for (i in 0..9) {
            val brick = Sprite(brickTexture)
            brick.setPosition(pos_x + (brick.width + _space) * i , pos_y )
            _bricks.add(brick)
        }
    }

    fun update (batch: Batch) {
        for (brick in _bricks) {
            brick.draw(batch)
        }
    }
}

class GdxArkanoid : ApplicationAdapter() {
    lateinit var batch: Batch
    lateinit var ball : Ball
    lateinit var pad : Pad
    lateinit var bricks : BrickGroup


    override fun create() {
        batch = SpriteBatch()
        pad = Pad( Texture(ImagePath.PAD.value) )
        pad.setPosition(screenWidth / 2 - pad.width / 2, 0f)

        ball = Ball( Texture(ImagePath.BALL.value) )
        ball.setPosition( (screenWidth/2 - ball.width), 50f)

        //bricks = BrickGroup(140f, 480f)
        bricks = BrickGroup(140f, 200f)

    }

    override fun render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        pad.update(batch)
        ball.update(batch)
        ball.computeCollisionWith(pad)

        for (brick in bricks.getBricks())
            ball.computeCollisionWith(brick)

        bricks.update(batch)

        batch.end()

    }

    override fun dispose() {
        batch.dispose()

    }
}
