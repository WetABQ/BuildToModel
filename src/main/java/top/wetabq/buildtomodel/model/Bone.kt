package top.wetabq.buildtomodel.model

import top.wetabq.buildtomodel.model.cube.Cube

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class Bone(private val name : String,private val cubes: List<Cube>) {

    fun getJson() : String {
        var cubeJson = "["
        for(cube : Cube in cubes) {
            cubeJson += "${cube.getJson()},"
        }
        cubeJson = "${cubeJson.substring(0,cubeJson.length-1)}]"
        return "{\"name\": \"$name\",\"pivot\": [0.0,0.0,0.0],\"cubes\": $cubeJson}"
    }

}