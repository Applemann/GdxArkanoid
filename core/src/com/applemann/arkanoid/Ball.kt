package com.applemann.arkanoid

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import jdk.nashorn.internal.runtime.linker.Bootstrap


class Rect(sprite: Sprite) : Rectangle() {
    init {
        this.x = sprite.x
        this.y = sprite.y
        this.width = sprite.width
        this.height = sprite.height
    }
    val topSide: Float get() = this.y + this.height
    val bottomSide: Float get() = this.y
    val rightSide: Float get() = this.x + this.width
    val leftSide: Float get() = this.x


}


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

    fun computeCollisionWith(sprite: Sprite) : Boolean {
        val ballRect = Rect(this)
        val spriteRect = Rect(sprite)


        // Bottom collision
        if (ballRect.bottomSide < spriteRect.topSide &&
            ballRect.topSide > spriteRect.topSide &&
            ballRect.leftSide > spriteRect.leftSide &&
            ballRect.leftSide < spriteRect.rightSide && direction > 270 ) { direction += 90; return true }
        else
        if (ballRect.bottomSide < spriteRect.topSide &&
            ballRect.topSide > spriteRect.topSide &&
            ballRect.leftSide > spriteRect.leftSide &&
            ballRect.leftSide < spriteRect.rightSide && direction < 270 ) { direction -= 90; return true }

        else
        // Top collision
        if (ballRect.topSide > spriteRect.bottomSide &&
            ballRect.bottomSide < spriteRect.bottomSide &&
            ballRect.leftSide > spriteRect.leftSide &&
            ballRect.leftSide < spriteRect.rightSide && direction > 270 ) { direction += 90; return true }
        else
        if (ballRect.topSide > spriteRect.bottomSide &&
            ballRect.bottomSide < spriteRect.bottomSide &&
            ballRect.leftSide > spriteRect.leftSide &&
            ballRect.leftSide < spriteRect.rightSide && direction < 270 ) { direction -= 90; return true }

        else
            return false

        //else
        //// Left collision
        //    if (ballRect.topSide > spriteRect.bottomSide &&
        //        ballRect.bottomSide < spriteRect.bottomSide &&
        //        ballRect.leftSide > spriteRect.leftSide &&
        //        ballRect.leftSide < spriteRect.rightSide && direction > 270 ) direction += 90
        //    else
        //    if (ballRect.topSide > spriteRect.bottomSide &&
        //        ballRect.bottomSide < spriteRect.bottomSide &&
        //        ballRect.leftSide > spriteRect.leftSide &&
        //        ballRect.leftSide < spriteRect.rightSide && direction < 270 ) direction -= 90


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