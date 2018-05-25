package com.applemann.arkanoid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite

class Pad(texture: Texture) : Sprite(texture) {
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
        else if (this.x + this.width > screenWidth) {
            this.x -= speed
        }
    }
}