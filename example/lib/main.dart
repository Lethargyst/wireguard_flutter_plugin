import 'package:flutter/material.dart';
import 'dart:async';

import 'package:wireguard_flutter/wireguard_flutter.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _publicKey = '';
  String _privateKey = '';
  final _wireguardFlutterPlugin = WireguardFlutter();

  @override
  void initState() {
    super.initState();
    initKeysState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initKeysState() async {
    String public = '';
    String private = '';
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    await _wireguardFlutterPlugin.genKeyPair();
    public = await _wireguardFlutterPlugin.getPublicKey() ?? 'Public key error';
    private =
        await _wireguardFlutterPlugin.getPrivateKey() ?? 'Private key error';

    setState(() {
      _publicKey = public;
      _privateKey = private;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('Private key: $_privateKey'),
              Text('Public key: $_publicKey'),
              ElevatedButton(
                onPressed: () => initKeysState(),
                child: const Text('Сгенерировать ключи')
              )
            ],
          ),
        ),
      ),
    );
  }
}
