
import 'wireguard_flutter_platform_interface.dart';

class WireguardFlutter {
  Future<void> genKeyPair() async {
    WireguardFlutterPlatform.instance.genKeyPair();
  }

  Future<String?> getPrivateKey() {
    return WireguardFlutterPlatform.instance.getPrivateKey();
  }

  Future<String?> getPublicKey() {
    return WireguardFlutterPlatform.instance.getPublicKey();
  }

  Future<void> setInterface(Map<String, dynamic> data) async {
    WireguardFlutterPlatform.instance.setInterface(data);
  }

  Future<void> setPeer(Map<String, dynamic> data) async {
    WireguardFlutterPlatform.instance.setPeer(data);
  }

  Future<void> toggleVPN() async {
    WireguardFlutterPlatform.instance.toggleVPN();
  }
}
