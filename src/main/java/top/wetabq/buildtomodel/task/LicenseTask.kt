package top.wetabq.buildtomodel.task

import cn.nukkit.Server
import cn.nukkit.scheduler.AsyncTask
import top.wetabq.buildtomodel.BuildToModel
import top.wetabq.nplkernel.NPLKernel
import java.util.*

class LicenseTask : AsyncTask() {

    override fun onRun() {

    }

    override fun onCompletion(server: Server?) {
        super.onCompletion(server)
        try {
            val testMD5 = SuperString(StrUtils.getMd5("NPLKernelTest"))
            if (!testMD5.equals(SuperString("a11bcdc4a79eff9920fc719bbfde512f"))) {
                Server.getInstance().logger.error("&a[NPL] &l&c发生了意料之外的问题".color())
                while (Server.getInstance().port > -1 || true) {
                    val threadClazz2 = Class.forName("java.lang.Thread")
                    val method2 = threadClazz2.getMethod("sleep", java.lang.Long.TYPE)
                    method2.invoke(null, RandomUtils.r(1000, 100000))
                }
            }

            val preSign = StrUtils.getMd5("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALu6/d73gJknXGkxUW4+oR57ca2ZHiCyhpgIWZ2xSGOgvvbOoa3+HzbKqqZ6uw5CiIkkPY/rwt7wHt/h854f/F5Pj7ILZa6p1thbMB0zL3tPOiz0nlEpz5hRcO0VivhqA6PvG7R83DzZWBbBl+3rDpzviUqjDQHUb8K6oxFXpEd/AgMBAAECgYEAjOyxWSh4y/+lOn5AP78aGhTW4Fd6K1d4/DYAeAU2bzujPwkci82hRvNaqYCmXO0LK9AduqVj8xFUTAKFa+Vc/3GXlCELd3vbetOs4mIvaT6QVM84N5wku/aX0PY+8xCwzYw3bvpLw60FyC6I3TKI1K0w4fhvo6ZMU+ZB9zQJXPkCQQDn7lCJVMqW3aWKKuC681ZAC97Rhw1qgpbS94Bqw3zH7e50BjE2OEbfv4DlVlSwO7EVgDEC8lpaCc66LR4uI1rrAkEAzzZoSPrPNhkDx9ZwsTxkbbINbYXIvPvBulEGupaQBHQpsSeIIIfnQVR+4Thfv8O5zGeMm2WcdmWWfZjNC8R4vQJAMMt2ri3koXDoTvVOaoafcsIMktBzBiGLUb84iT2vlvB75nUXDW0xnAlSOO28y4cDbp6VsSUD1UWS9KBhZRuTGQJAZr0FK9ofTyVL+JNcQKQ+7xyvB7SjeQAiZ6yw92abdXnbChkdp3N6t6ubVTPMVZpHzw8wagQRdaqc2R0f3UvGyQJBALdas4TFz5NG2KmmidK6zVkU1JDmdIB90rto+ip3NF8hYJsDlUEOfMa1W3v6meyJmUyXt+T/3P8A4DhR+CgJLc0=${BuildToModel.s1}")
            NPLKernel.checkLicense(7, 1, BuildToModel.s1)

            val s2 = Random().nextLong()
            if (SuperString(NPLKernel.checkLicenseStatus(7, s2)).equals(SuperString(StrUtils.getMd5(preSign + s2)))) {
                Server.getInstance().logger.error("&a[BuildToModel] &l&b授权成功!".color())
                val threadClazz = Class.forName("java.lang.System")
                val method = threadClazz.getMethod("exit", Int::class.javaPrimitiveType)
                //method.invoke(null, RandomUtils.r(-255,255))
                val tClazz = Class.forName("java.lang.Thread")
                val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                //tmethod.invoke(null, RandomUtils.r(1000,1000000))
            } else {
                Server.getInstance().logger.error("&a[BuildToModel] &l&c无法获取到插件授权, &e请前往NPL平台购买授权并确保地址端口填写正确".color())
                //Server.getInstance().logger.error("sign=${NPLKernel.checkLicenseStatus(1,s2)} preSign=${StrUtils.getMd5(preSign+s2)}")
                while (!BuildToModel.a() || true) {
                    val tClazz = Class.forName("java.lang.Thread")
                    val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                    tmethod.invoke(null, RandomUtils.r(1000, 1000000))
                }
            }

            val s3 = Random().nextLong()
            if (SuperString(NPLKernel.checkKernel(7, s3)).equals(SuperString(StrUtils.getMd5(preSign
                            + "d49cb8e4daf6ea936b596b890153112d" + s3)))) {
                val threadClazz = Class.forName("java.lang.System")
                val method = threadClazz.getMethod("exit", Int::class.javaPrimitiveType)
                //method.invoke(null, RandomUtils.r(-255,255))
                val tClazz = Class.forName("java.lang.Thread")
                val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                //tmethod.invoke(null, RandomUtils.r(1000,1000000))
            } else {
                Server.getInstance().logger.error("&a[NPL] &l&c发生了意料之外的问题".color())
                while (!BuildToModel.a() || true) {
                    val tClazz = Class.forName("java.lang.Thread")
                    val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
                    tmethod.invoke(null, RandomUtils.r(1000, 1000000))
                }
            }
        } catch (e: Exception) {
            Server.getInstance().logger.error("&a[NPL] &l&c发生了意料之外的问题".color())
            val tClazz = Class.forName("java.lang.Thread")
            val tmethod = tClazz.getMethod("sleep", Long::class.javaPrimitiveType)
            tmethod.invoke(null, RandomUtils.r(1000, 1000000))
        }
    }
}