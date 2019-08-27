package com.game.quillyjumper.ecs.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor
import kotlin.math.roundToInt

class TransformComponent(var position: Vector2 = Vector2(0f, 0f),
                         var z: Int = 0,
                         var prevPosition: Vector2 = Vector2(0f, 0f),
                         var interpolatedPosition: Vector2 = Vector2(0f, 0f),
                         var size: Vector2 = Vector2(1f, 1f)) : Component, Comparable<TransformComponent> {
    // entities are sorted first by their z index (background/foreground) and then by their
    // y-coordinate of their position on the current layer (=z index)
    override fun compareTo(other: TransformComponent): Int {
        val zDiff = z - other.z
        return if (zDiff == 0) (position.y - other.position.y).roundToInt() else zDiff
    }

    companion object {
        val mapper = mapperFor<TransformComponent>()
    }
}
