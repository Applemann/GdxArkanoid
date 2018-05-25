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
        loadBricks(x, y)
    }

    fun loadBricks(pos_x: Float, pos_y: Float) {
        val brickTexture = Texture(ImagePath.BRICK.value)

        for (i in 0..9) {
            for (j in 0..5) {
                val brick = Sprite(brickTexture)
                brick.setPosition(pos_x + (brick.width + _space) * i, pos_y - (brick.height + _space) * j)
                _bricks.add(brick)
            }
        }
    }

    fun remove (index: Int) {
        _bricks.removeAt(index)
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
    lateinit var brickGroup : BrickGroup


    override fun create() {
        batch = SpriteBatch()
        pad = Pad( Texture(ImagePath.PAD.value) )
        pad.setPosition(screenWidth / 2 - pad.width / 2, 0f)

        ball = Ball( Texture(ImagePath.BALL.value) )
        ball.setPosition( (screenWidth/2 - ball.width), 50f)

        brickGroup = BrickGroup(140f, 480f)
        //brickGroup = BrickGroup(140f, 200f)

    }

    override fun render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        pad.update(batch)
        ball.update(batch)
        ball.computeCollisionWith(pad)

        //val bricksList = brickGroup.getBrickGroup()
        for ((index, brick) in brickGroup.getBricks().withIndex()) {
            if (ball.computeCollisionWith(brick)) {
                //brickGroup.remove(index)
            }
        }


        brickGroup.update(batch)

        batch.end()

    }

    override fun dispose() {
        batch.dispose()

    }
}
