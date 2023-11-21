import 'package:flutter_test/flutter_test.dart';
import 'package:wireguard_flutter/wireguard_flutter.dart';
import 'package:wireguard_flutter/wireguard_flutter_platform_interface.dart';
import 'package:wireguard_flutter/wireguard_flutter_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockWireguardFlutterPlatform
    with MockPlatformInterfaceMixin
    implements WireguardFlutterPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final WireguardFlutterPlatform initialPlatform = WireguardFlutterPlatform.instance;

  test('$MethodChannelWireguardFlutter is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelWireguardFlutter>());
  });

  test('getPlatformVersion', () async {
    WireguardFlutter wireguardFlutterPlugin = WireguardFlutter();
    MockWireguardFlutterPlatform fakePlatform = MockWireguardFlutterPlatform();
    WireguardFlutterPlatform.instance = fakePlatform;

    expect(await wireguardFlutterPlugin.getPlatformVersion(), '42');
  });
}
