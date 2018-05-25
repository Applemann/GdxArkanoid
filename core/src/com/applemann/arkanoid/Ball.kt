package com.applemann.arkanoid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite

class Ball(texture: Texture) : Sprite(texture) {
    var speed: Int = 250
    var direction: Int = 45
    var motionx: Int = 1
    var motiony: Int = 1

    constructor(texture: Texture, x: Float, y: Float) : this(texture) {
        this.setPosition(x, y)
    }

    fun computeWallCollision() {
        // Right collision
        if (this.x + this.width > screenWidth && direction > 0 ) {
            direction += 90
        }
        else
        if (this.x + this.width > screenWidth && direction < 360 ) {
            direction -= 90
        }
        else

        // Top collision
        if (this.y + this.height > screenHeight && direction > 90 ) {
            direction += 90
        }
        else
        if (this.y + this.height > screenHeight && direction < 90 ) {
            direction -= 90
        }
        else

        // Left collision
        if (this.x < 0 && direction > 180 ) {
            direction += 90
        }
        else
        if (this.x < 0 && direction < 180 ) {
            direction -= 90
        }
        else

        // Bottom collision
        if (this.y < 0 && direction > 270 ) {
            direction += 90
        }
        else
        if (this.y < 0 && direction < 270 ) {
            direction -= 90
        }
    }

    fun computeCollisionWith(sprite: Sprite) {
        if (this.y < sprite.y + sprite.height && this.x > sprite.x && this.x < sprite.x + sprite.width && direction > 270 ) {
            direction += 90
        }
        else
        if (this.y < sprite.y + sprite.height && this.x > sprite.x && this.x < sprite.x + sprite.width && direction < 270 ) {
            direction -= 90
        }
    }

    fun update(batch: Batch) {
        this.draw(batch)

        if (direction < 0) {
            direction += 360
        }
        else if (direction > 360) {
            direction -= 360
        }

        val delta = Gdx.graphics.getDeltaTime()
        x += motionx * (Math.cos(this.direction * (Math.PI / 180)) * this.speed * delta ).toFloat()
        y += motiony * (Math.sin(this.direction * (Math.PI / 180)) * this.speed * delta ).toFloat()

        computeWallCollision()

    }
}