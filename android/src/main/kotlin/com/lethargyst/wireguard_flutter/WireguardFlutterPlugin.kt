package com.lethargyst.wireguard_flutter

import android.app.Activity
import com.wireguard.android.backend.GoBackend
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


class WireguardFlutterPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
    private lateinit var channel : MethodChannel
    private lateinit var appContext: Activity;

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "wireguard_flutter")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "genKeyPair" -> result.success(WgKeysService.genKeyPair())
            "genPrivateKey" -> result.success(WgKeysService.genPrivateKey())
            "genPublicKey" -> result.success(WgKeysService.genPublicKey())

            "setInterface" -> WgService.setInterface(call.arguments, result)
            "setPeer" -> WgService.setPeer(call.arguments, result)
            "toggleVPN" -> null
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null);
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        appContext = binding.activity;
        handleRequestPermission(appContext);
    }

    override fun onDetachedFromActivityForConfigChanges() {}

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onDetachedFromActivity() {}

    private fun handleRequestPermission(appContext: Activity) {
        val intent = GoBackend.VpnService.prepare(appContext)
        if (intent != null) {
            appContext.startActivityForResult(intent, 0);
        }
    }
}

