import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'wireguard_flutter_platform_interface.dart';

/// An implementation of [WireguardFlutterPlatform] that uses method channels.
class MethodChannelWireguardFlutter extends WireguardFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('wireguard_flutter');

  @override
  Future<void> genKeyPair() async {
    await methodChannel.invokeMethod<String>('genKeyPair');
  }

  @override
  Future<String?> getPrivateKey() async {
    return await methodChannel.invokeMethod<String>('genPrivateKey');
  }

  @override
  Future<String?> getPublicKey() async {
    return await methodChannel.invokeMethod<String>('genPublicKey');
  }
}
