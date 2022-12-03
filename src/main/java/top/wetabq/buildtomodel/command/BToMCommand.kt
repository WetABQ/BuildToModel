package top.wetabq.buildtomodel.command

import cn.nukkit.Player
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.command.data.CommandParameter
import cn.nukkit.utils.TextFormat
import top.wetabq.buildtomodel.BuildToModel
import java.util.HashMap

/**
 * BuildToModel
 *
 * @author WetABQ Copyright (c) 2018.07
 * @version 1.0
 */
class BToMCommand : Command("btom") {

    init {
        this.setDescription("Build To Model")
        this.aliases = arrayOf("bm")
        this.usage = "/btom(bm) <modelId:String> [size:Int] [Whether the box is limited: boolean]"
        this.setCommandParameters(object : HashMap<String, Array<CommandParameter>>() {
            init {
                put("1arg", arrayOf(
                        CommandParameter("ModelId", "string", false),
                        CommandParameter("Size(1)", "int", true),
                        CommandParameter("Whether the box is limited(true)", "bool", true)))
            }
        })

    }

    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (sender.isOp && sender is Player) {
            when (args.size) {
                1 -> {
                }
                2 -> {
                    if (args[1].toInt() != 1 || args[1].toInt() != 5) {
                        BuildToModel.size = args[1].toInt()
                        sender.sendMessage("${TextFormat.RED} The default texture only supports models with size 1, other textures need to be homemade.")
                    }
                }
                3 -> {
                    if (args[1].toInt() != 1) {
                        BuildToModel.size = args[1].toInt()
                        sender.sendMessage("${TextFormat.RED} The default texture only supports models with size 1, other textures need to be homemade.")
                    }
                    if (!args[2].toBoolean()) {
                        BuildToModel.limit = args[2].toBoolean()
                        sender.sendMessage("${TextFormat.RED} The default texture only supports models with size 1, and the block model order only supports concrete. The generated model map needs to be reset. (including uv parameters)")
                    }
                }
                else -> {
                    sender.sendMessage("${TextFormat.RED} Usage: /btom(bm) [size:Int] [Whether the box is limited: boolean]")
                    return true
                }
            }
            BuildToModel.modelId[sender.name] = args[0]
            BuildToModel.switch.add(sender.name)
            sender.sendMessage("${TextFormat.GREEN} Please stand at point 1 and right click on the stick.\n${TextFormat.RED} ps: Reading the location of the player instead of clicking on the square")
        }
        return true
    }

}