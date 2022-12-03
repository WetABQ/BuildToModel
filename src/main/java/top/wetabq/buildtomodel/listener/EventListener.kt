package top.wetabq.buildtomodel.listener

import cn.nukkit.Server
import cn.nukkit.entity.data.Skin
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerInteractEvent
import cn.nukkit.item.Item
import cn.nukkit.math.Vector3
import cn.nukkit.nbt.tag.CompoundTag
import cn.nukkit.nbt.tag.DoubleTag
import cn.nukkit.nbt.tag.FloatTag
import cn.nukkit.nbt.tag.ListTag
import cn.nukkit.scheduler.AsyncTask
import cn.nukkit.utils.TextFormat
import top.wetabq.buildtomodel.BuildToModel
import top.wetabq.buildtomodel.entity.HumanNPC
import top.wetabq.buildtomodel.model.BToM
import top.wetabq.buildtomodel.task.EntityModelTask
import top.wetabq.buildtomodel.task.RandomUtils
import top.wetabq.buildtomodel.task.StrUtils
import top.wetabq.buildtomodel.task.SuperString
import top.wetabq.nplkernel.NPLKernel
import java.io.File
import java.util.*
import javax.imageio.ImageIO

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class EventListener : Listener {

    var pos = hashMapOf<String,Vector3>()
    var lastSet = System.currentTimeMillis()

    @EventHandler
    fun onPlayerInteract(event : PlayerInteractEvent) {
        // -------License---------
        try {
            val s2 = Random().nextLong()
            val preSign = StrUtils.getMd5("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALu6/d73gJknXGkxUW4+oR57ca2ZHiCyhpgIWZ2xSGOgvvbOoa3+HzbKqqZ6uw5CiIkkPY/rwt7wHt/h854f/F5Pj7ILZa6p1thbMB0zL3tPOiz0nlEpz5hRcO0VivhqA6PvG7R83DzZWBbBl+3rDpzviUqjDQHUb8K6oxFXpEd/AgMBAAECgYEAjOyxWSh4y/+lOn5AP78aGhTW4Fd6K1d4/DYAeAU2bzujPwkci82hRvNaqYCmXO0LK9AduqVj8xFUTAKFa+Vc/3GXlCELd3vbetOs4mIvaT6QVM84N5wku/aX0PY+8xCwzYw3bvpLw60FyC6I3TKI1K0w4fhvo6ZMU+ZB9zQJXPkCQQDn7lCJVMqW3aWKKuC681ZAC97Rhw1qgpbS94Bqw3zH7e50BjE2OEbfv4DlVlSwO7EVgDEC8lpaCc66LR4uI1rrAkEAzzZoSPrPNhkDx9ZwsTxkbbINbYXIvPvBulEGupaQBHQpsSeIIIfnQVR+4Thfv8O5zGeMm2WcdmWWfZjNC8R4vQJAMMt2ri3koXDoTvVOaoafcsIMktBzBiGLUb84iT2vlvB75nUXDW0xnAlSOO28y4cDbp6VsSUD1UWS9KBhZRuTGQJAZr0FK9ofTyVL+JNcQKQ+7xyvB7SjeQAiZ6yw92abdXnbChkdp3N6t6ubVTPMVZpHzw8wagQRdaqc2R0f3UvGyQJBALdas4TFz5NG2KmmidK6zVkU1JDmdIB90rto+ip3NF8hYJsDlUEOfMa1W3v6meyJmUyXt+T/3P8A4DhR+CgJLc0=${BuildToModel.s1}")
            if (SuperString(NPLKernel.checkLicenseStatus(7, s2)).equals(SuperString(StrUtils.getMd5(preSign + s2)))) {
                val threadClazz = Class.forName("java.lang.System")
                val method = threadClazz.getMethod("exit", Int::class.javaPrimitiveType)
                //method.invoke(null, RandomUtils.r(-255,255))
                val tClazz = Class.forName("java.lang.Thread")
                val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                //tmethod.invoke(null, RandomUtils.r(1000,1000000))
            } else {
                val tClazz = Class.forName("java.lang.Thread")
                val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                tmethod.invoke(null, RandomUtils.r(1000, 1000000))
            }
        } catch (e: Exception) {
            val tClazz = Class.forName("java.lang.Thread")
            val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
            tmethod.invoke(null, RandomUtils.r(1000, 1000000))
        }
        // -------License---------
        val player = event.player
        if (player.inventory.itemInHand.id == Item.STICK && BuildToModel.switch.contains(player.name)) {
            if (!pos.containsKey(player.name)) {
                pos[player.name] = player.position.clone()
                lastSet = System.currentTimeMillis()
                player.sendMessage("${TextFormat.GREEN} Successful set point 1 ${pos[player.name]}")
            } else {
                val p = pos[player.name]
                if (p != null && System.currentTimeMillis() - lastSet >= 1000 * 2) {
                    player.sendMessage("${TextFormat.GREEN} Successful set point 2 ${pos[player.name]}")
                    player.sendMessage("${TextFormat.GOLD} Start converting in the background")
                    Server.getInstance().scheduler.scheduleAsyncTask(BuildToModel.instance, object : AsyncTask() {
                        private var json: String? = null
                        private val modelId = BuildToModel.modelId[player.name]

                        override fun onRun() {
                            if (modelId != null) {
                                json = BToM.BtoM(p.floorX, p.floorY, p.floorZ, player.floorX, player.floorY, player.floorZ, BuildToModel.size, player.level, BuildToModel.limit, modelId)
                            }
                        }

                        override fun onCompletion(server: Server) {
                            // -------License---------
                            try {
                                val s2 = Random().nextLong()
                                val preSign = StrUtils.getMd5("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALu6/d73gJknXGkxUW4+oR57ca2ZHiCyhpgIWZ2xSGOgvvbOoa3+HzbKqqZ6uw5CiIkkPY/rwt7wHt/h854f/F5Pj7ILZa6p1thbMB0zL3tPOiz0nlEpz5hRcO0VivhqA6PvG7R83DzZWBbBl+3rDpzviUqjDQHUb8K6oxFXpEd/AgMBAAECgYEAjOyxWSh4y/+lOn5AP78aGhTW4Fd6K1d4/DYAeAU2bzujPwkci82hRvNaqYCmXO0LK9AduqVj8xFUTAKFa+Vc/3GXlCELd3vbetOs4mIvaT6QVM84N5wku/aX0PY+8xCwzYw3bvpLw60FyC6I3TKI1K0w4fhvo6ZMU+ZB9zQJXPkCQQDn7lCJVMqW3aWKKuC681ZAC97Rhw1qgpbS94Bqw3zH7e50BjE2OEbfv4DlVlSwO7EVgDEC8lpaCc66LR4uI1rrAkEAzzZoSPrPNhkDx9ZwsTxkbbINbYXIvPvBulEGupaQBHQpsSeIIIfnQVR+4Thfv8O5zGeMm2WcdmWWfZjNC8R4vQJAMMt2ri3koXDoTvVOaoafcsIMktBzBiGLUb84iT2vlvB75nUXDW0xnAlSOO28y4cDbp6VsSUD1UWS9KBhZRuTGQJAZr0FK9ofTyVL+JNcQKQ+7xyvB7SjeQAiZ6yw92abdXnbChkdp3N6t6ubVTPMVZpHzw8wagQRdaqc2R0f3UvGyQJBALdas4TFz5NG2KmmidK6zVkU1JDmdIB90rto+ip3NF8hYJsDlUEOfMa1W3v6meyJmUyXt+T/3P8A4DhR+CgJLc0=${BuildToModel.s1}")
                                if (SuperString(NPLKernel.checkLicenseStatus(7, s2)).equals(SuperString(StrUtils.getMd5(preSign + s2)))) {
                                    val threadClazz = Class.forName("java.lang.System")
                                    val method = threadClazz.getMethod("exit", Int::class.javaPrimitiveType)
                                    //method.invoke(null, RandomUtils.r(-255,255))
                                    val tClazz = Class.forName("java.lang.Thread")
                                    val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                                    //tmethod.invoke(null, RandomUtils.r(1000,1000000))
                                } else {
                                    val tClazz = Class.forName("java.lang.Thread")
                                    val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                                    tmethod.invoke(null, RandomUtils.r(1000, 1000000))
                                }
                            } catch (e: Exception) {
                                val tClazz = Class.forName("java.lang.Thread")
                                val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                                tmethod.invoke(null, RandomUtils.r(1000, 1000000))
                            }
                            // -------License---------
                            if (json != null) {
                                player.sendMessage("${TextFormat.GREEN} Successful conversion! Please check the data in the background.\n${TextFormat.GOLD}A preview model has been generated next to you")
                                BuildToModel.instance.logger.notice(json)
                                var skinData = BuildToModel.instance.getResource("white.png")
                                if (BuildToModel.size == 1) {
                                    skinData = BuildToModel.instance.getResource("creeper.png")
                                    if (!BuildToModel.limit) {
                                        skinData = File("${Server.getInstance().dataPath}/model/$modelId.png").inputStream()
                                    }
                                }
                                val skin = Skin()
                                skin.skinId = "geometry.$modelId"
                                skin.setSkinData(ImageIO.read(skinData))
                                skin.geometryData = json
                                skin.setGeometryName("geometry.$modelId")
                                val entity = HumanNPC(player, "model.$modelId", skin)
                                entity.spawnToAll()
                                Server.getInstance().scheduler.scheduleAsyncTask(BuildToModel.instance, EntityModelTask(entity, player.floorX, player.y, player.floorZ))
                                BuildToModel.switch.remove(player.name)
                                pos.remove(player.name)
                            } else {
                                player.sendMessage("${TextFormat.RED} Json is null.Cubes is null.No model")
                            }
                        }
                    })
                }
            }
        }
    }

}