package top.wetabq.buildtomodel.model

import cn.nukkit.Server
import cn.nukkit.block.Block
import cn.nukkit.block.BlockSlab
import cn.nukkit.level.Level
import cn.nukkit.math.Vector3
import top.wetabq.buildtomodel.BuildToModel
import top.wetabq.buildtomodel.image.DrawImage
import top.wetabq.buildtomodel.model.cube.Cube
import top.wetabq.buildtomodel.model.cube.Origin
import top.wetabq.buildtomodel.model.cube.UV
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import java.io.FileOutputStream



/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
object BToM {
    @JvmStatic
    fun BtoM (x1 : Int,y1 : Int,z1 : Int,x2 : Int,y2 : Int,z2 : Int,modelSize : Int,level : Level,limit : Boolean,modelId : String) : String? {

        var cubes = arrayListOf<Cube>()
        val startx = Math.min(x1, x2)
        val stopx = Math.max(x1, x2)
        val startz = Math.min(z1, z2)
        val stopz = Math.max(z1, z2)
        val starty = Math.min(y1, y2)
        val stopy = Math.max(y1, y2)
        val uvMap = hashMapOf<String,HashMap<String,Int>>()
        val drawImage = DrawImage()
        for (x in startx..stopx) {
            for (z in startz..stopz) {
                for (y in starty..stopy) {
                    val block = level.getBlock(Vector3(x.toDouble(),y.toDouble(),z.toDouble()))
                    if (limit) {
                        if (block.id == Block.CONCRETE) {
                            var u = (block.damage shl 2)
                            cubes.add(Cube(Origin((x - startx) * modelSize, (y - starty) * modelSize * 1.0, (z - startz) * modelSize), modelSize, UV(u, 0),modelSize.toDouble()))
                        }
                    } else {
                        if (block.id != 0) {
                            val blockString = "${block.id}:${block.damage}"
                            if (!uvMap.containsKey(blockString)) {
                                uvMap[blockString] = drawImage.draw(block)
                            }
                            var slab = 1.0
                            if (block is BlockSlab) {
                                slab = 0.5
                            }
                            cubes.add(Cube(Origin((x - startx) * modelSize, (y - starty) * modelSize * slab, (z - startz) * modelSize), modelSize, UV(uvMap[blockString]!!["u"]!!, uvMap[blockString]!!["v"]!!),slab))
                        }
                    }
                }
            }
        }
        if (drawImage.drawCount != 0) {
            drawImage.save(modelId)
        }

        val model = Model(modelId, arrayListOf(
                Bone("head", cubes)
        ))

        val json = model.getJson()
        val cubesCount = cubes.size
        BuildToModel.instance.logger.warning("Successfully converted $cubesCount blocks")
        if (cubesCount >= 50) {
            val modelFolder = File("${Server.getInstance().dataPath}/model/")
            if (!modelFolder.exists()) {
                modelFolder.mkdir()
            }
            val jsonFile = File("${Server.getInstance().dataPath}/model/$modelId.json")
            if (!jsonFile.exists()) {
                jsonFile.createNewFile()
            }
            val bytes = json.toByteArray()
            val b = bytes.size
            val fos = FileOutputStream(jsonFile)
            fos.write(bytes, 0, b)
            fos.close()
            BuildToModel.instance.logger.notice("Since the model is larger than 50 blocks, we automatically convert it to a file and store it in /model/$modelId.json")
        } else if (cubesCount == 0) {
            return null
        }
        return json
    }

}