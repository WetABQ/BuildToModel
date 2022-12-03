package top.wetabq.buildtomodel.model.cube

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class Cube(private val origin : Origin,private val size : Int,private val uv : UV,private val ySize: Double) {
    fun getJson() : String {
        return "{\"origin\": ${origin.getJson()},\"size\": [$size,$ySize,$size],\"uv\": ${uv.getJson()}}"
    }
}