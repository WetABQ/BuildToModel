package top.wetabq.buildtomodel.task

import cn.nukkit.math.Vector3
import cn.nukkit.scheduler.AsyncTask
import top.wetabq.buildtomodel.entity.HumanNPC
import kotlin.math.sin

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class EntityModelTask(private val entity : HumanNPC,private val x : Int,private var y : Double,private val z : Int) : AsyncTask() {

    private var t = 0.0

    override fun onRun() {
        while (!entity.closed) {
            if (t >= 360) {
                t = 0.0
            }
            t += 0.1
            y += sin(t)*0.04
            entity.teleport(Vector3(x.toDouble(), y, z.toDouble()))
            Thread.sleep(50)
        }
    }

}