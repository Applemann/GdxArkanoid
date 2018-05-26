package com.applemann.arkanoid

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite

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