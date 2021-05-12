package com.varuns2002.disable_flag_secure

import android.view.SurfaceView
import android.view.Window
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class DisableFlagSecure : IXposedHookLoadPackage {

    private val debug: Boolean = false

    private val mRemoveSecureFlagHook: XC_MethodHook = object : XC_MethodHook() {
        @Throws(Throwable::class)
        override fun beforeHookedMethod(param: MethodHookParam) {
            var flags: Int = param.args[0] as Int
            flags = flags and LayoutParams.FLAG_SECURE.inv()
            param.args[0] = flags
        }
    }

    private val mRemoveSetSecureHook: XC_MethodHook = object : XC_MethodHook() {
        @Throws(Throwable::class)
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.args[0] = false
        }
    }

    private val mRemoveSecureParamHook: XC_MethodHook = object : XC_MethodHook() {
        @Throws(Throwable::class)
        override fun beforeHookedMethod(param: MethodHookParam) {
            val params = param.args[1] as LayoutParams
            params.flags = params.flags and LayoutParams.FLAG_SECURE.inv()
        }
    }

    /**
     * This method is called when an app is loaded. It's called very early, even before
     * Application.onCreate is called.
     * Modules can set up their app-specific hooks here.
     *
     * @param loadPackageParam Information about the app.
     * @throws Throwable Everything the callback throws is caught and logged.
     */
    override fun handleLoadPackage(loadPackageParam: LoadPackageParam?) {
        // Log Package Name
        XposedBridge.log("Disabled FLAG_SECURE for: " + (loadPackageParam?.packageName ?: "null"))

        XposedHelpers.findAndHookMethod(
            Window::class.java, "setFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
            mRemoveSecureFlagHook
        )

        XposedHelpers.findAndHookMethod(
            SurfaceView::class.java, "setSecure", Boolean::class.javaPrimitiveType,
            mRemoveSetSecureHook
        )

        try {
            val windowsState =
                XposedHelpers.findClass("com.android.server.wm.WindowState", loadPackageParam?.classLoader)
            XposedHelpers.findAndHookMethod(windowsState, "isSecureLocked", XC_MethodReplacement.returnConstant(false))
        } catch (error: Throwable) {
            if (debug) XposedBridge.log("Disable-FLAG_SECURE: $error")
        }

        try {
            val windowsState =
                XposedHelpers.findClass("com.android.server.wm.WindowState", loadPackageParam?.classLoader)
            XposedHelpers.findAndHookMethod(
                "com.android.server.wm.WindowManagerService",
                loadPackageParam?.classLoader,
                "isSecureLocked",
                windowsState,
                XC_MethodReplacement.returnConstant(false)
            )
        } catch (error: Throwable) {
            if (debug) XposedBridge.log("Disable-FLAG_SECURE: $error")
        }

        try {
            XposedHelpers.findAndHookMethod(
                "android.view.WindowManagerGlobal", loadPackageParam!!.classLoader, "addView",
                View::class.java,
                ViewGroup.LayoutParams::class.java,
                Display::class.java,
                Window::class.java, mRemoveSecureParamHook
            )
        } catch (error: Throwable) {
            if (debug) XposedBridge.log("Disable-FLAG_SECURE: $error")
        }

        try {
            XposedHelpers.findAndHookMethod(
                "android.view.WindowManagerGlobal", loadPackageParam!!.classLoader, "addView",
                View::class.java,
                ViewGroup.LayoutParams::class.java,
                Display::class.java,
                Window::class.java,
                Int::class.javaPrimitiveType, mRemoveSecureParamHook
            )
        } catch (error: Throwable) {
            if (debug) XposedBridge.log("Disable-FLAG_SECURE: $error")
        }

        try {
            XposedHelpers.findAndHookMethod(
                "android.view.WindowManagerGlobal", loadPackageParam!!.classLoader, "updateViewLayout",
                View::class.java,
                ViewGroup.LayoutParams::class.java, mRemoveSecureParamHook
            )
        } catch (error: Throwable) {
            if (debug) XposedBridge.log("Disable-FLAG_SECURE: $error")
        }
    }
}
