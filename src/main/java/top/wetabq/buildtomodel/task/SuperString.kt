package top.wetabq.buildtomodel.task

import cn.nukkit.utils.TextFormat
import java.util.*

/**
 * WAntiCheatPro
 *
 * @author WetABQ Copyright (c) 2019.09
 * @version 1.0
 */
fun String.color(): String {
    return TextFormat.colorize(this)
}
class SuperString(str: String) {

    private var chars = LinkedList<Int>()

    init {
        for (c in str.toCharArray()) {
            chars.add(charOffset(c) - '0')
        }
    }

    fun setChars(str: String) {
        chars.clear()
        chars = LinkedList()
        for (c in str.toCharArray()) {
            chars.add(charOffset(c) - '0')
        }
    }

    fun equals(superStr: SuperString): Boolean {
        if (superStr.chars.size != chars.size) {
            return false
        }
        for (i in chars.indices) {
            try {
                if (chars[i] != superStr.chars[i]) {
                    return false
                }
            } catch (e: ArrayIndexOutOfBoundsException) {
                return false
            }

        }
        return true
    }

    /**
     * 字节偏移方法，如果要加强防护措施，请自行更改偏移值
     */
    private fun charOffset(ch: Char): Char {
        val offset = 5
        var c = ch - '0'
        c += offset
        return (c + '0'.toInt()).toChar()
    }
}