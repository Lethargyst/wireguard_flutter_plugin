import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'wireguard_flutter_method_channel.dart';

abstract class WireguardFlutterPlatform extends PlatformInterface {
  /// Constructs a WireguardFlutterPlatform.
  WireguardFlutterPlatform() : super(token: _token);

  static final Object _token = Object();

  static WireguardFlutterPlatform _instance = MethodChannelWireguardFlutter();

  /// The default instance of [WireguardFlutterPlatform] to use.
  ///
  /// Defaults to [MethodChannelWireguardFlutter].
  static WireguardFlutterPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [WireguardFlutterPlatform] when
  /// they register themselves.
  static set instance(WireguardFlutterPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<void> genKeyPair() {
    throw UnimplementedError('getPrivateKey() has not been implemented.');
  }

  Future<String?> getPrivateKey() {
    return WireguardFlutterPlatform.instance.getPrivateKey();
  }

  Future<String?> getPublicKey() {
    throw UnimplementedError('getPublicKey() has not been implemented.');
  }
}
