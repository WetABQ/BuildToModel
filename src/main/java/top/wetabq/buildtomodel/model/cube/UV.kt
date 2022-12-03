package top.wetabq.buildtomodel.model.cube

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class UV(private val u : Int,private val v : Int) {

    fun getJson() : String {
        return "[$u,$v]"
    }
}