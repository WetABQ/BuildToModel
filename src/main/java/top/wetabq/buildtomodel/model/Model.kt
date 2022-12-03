package top.wetabq.buildtomodel.model

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class Model(private val geometryId : String,private val bones : ArrayList<Bone>) {

    //val texturewidth = 64*modelSize
    //val textureheight = 2*modelSize

    fun getJson() : String {
        var boneJson = "["
        for(bone : Bone in bones) {
            boneJson += "${bone.getJson()},"
        }
        boneJson = "${boneJson.substring(0,boneJson.length-1)}]"
        return "{\"geometry.$geometryId\" : {\"bones\": $boneJson}}"
    }

}