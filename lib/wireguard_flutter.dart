
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
}
