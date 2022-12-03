package top.wetabq.buildtomodel.image

import cn.nukkit.Server
import cn.nukkit.block.Block
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class DrawImage {

    var drawCount = 0
    private var imageBuffer = BufferedImage(64, 64, BufferedImage.TYPE_4BYTE_ABGR)
    private var v = 0

    fun draw(block : Block) : HashMap<String,Int> {
        val graphics = imageBuffer.graphics
        var map = hashMapOf("u" to (drawCount shl 2),"v" to (v shl 1))
        graphics.color = Color(block.color.red,block.color.green,block.color.blue)
        graphics.drawLine(1 + (drawCount shl 2),v shl 1,2 + (drawCount shl 2), v shl 1)
        graphics.drawLine(drawCount shl 2,1 + (v shl 1),3 + (drawCount shl 2),1 + (v shl 1))
        // drawCount << 2 | v << 1
        //graphics.drawLine(2 + drawCount * 4,1 + v * 2,3 + drawCount * 4,1 + v * 2)
        //graphics.drawLine(1 + drawCount * 4,2 + v * 2,4 + drawCount * 4,2 + v * 2)
        graphics.dispose()
        if (drawCount == 16) {
            v++
        }
        drawCount++
        return map
    }

    fun save(modelId : String) {
        val d = File("${Server.getInstance().dataPath}/model")
        if(!d.exists()) {
            d.mkdirs()
        }
        ImageIO.write(imageBuffer, "png", File("${Server.getInstance().dataPath}/model/$modelId.png"))
    }
}