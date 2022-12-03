package top.wetabq.buildtomodel.model.cube

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class Origin(private val x1: Int,private val y1: Double,private val z1 :Int) {

    fun getJson() : String {
        return "[$x1,$y1,$z1]"
    }

}