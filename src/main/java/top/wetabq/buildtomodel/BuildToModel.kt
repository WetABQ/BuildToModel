package top.wetabq.buildtomodel

import cn.nukkit.Server
import cn.nukkit.entity.data.Skin
import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.TextFormat
import top.wetabq.buildtomodel.command.BToMCommand
import top.wetabq.buildtomodel.listener.EventListener
import java.util.*
import javax.imageio.ImageIO

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class BuildToModel : PluginBase() {

    init {
        init(this)
    }

    override fun onLoad() {
        logger.notice("BuildToModel Loading...")
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(EventListener(), this)
        val skinStream = this.javaClass.classLoader.getResourceAsStream("creeper.png")
        val dskin = Skin()
        dskin.setSkinData(ImageIO.read(skinStream))
        skin = dskin
        Server.getInstance().commandMap.register("${TextFormat.GOLD}[BTOM]", BToMCommand())
        logger.notice("BulidToModel Enabled ! Made by WetABQ. Created with ${TextFormat.GOLD}Kotlin.")
    }

    override fun onDisable() {
        logger.notice("BulidToModel Disabled!")
    }

    companion object {

        lateinit var skin: Skin
        lateinit var instance: BuildToModel
        var switch = arrayListOf<String>()
        var size = 1
        var limit = true
        var modelId = hashMapOf<String, String>()
        var s1: Long = Random().nextLong()

        var abs: Boolean = false
        var vvv: Boolean = false
        var sda: Boolean = false // License
        var dsa: Boolean = false

        @JvmStatic
        fun a(): Boolean = sda

        @JvmStatic
        fun b(): Boolean = vvv

        @JvmStatic
        fun c(): Boolean = dsa

        fun init(p: BuildToModel) {
            instance = p
        }

    }

}