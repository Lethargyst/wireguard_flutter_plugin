package com.lethargyst.wireguard_flutter

class WgInterface(
    val interfaceKey: String,
    val address: String,
    val listenPort: String,
    val dns: String
)

class WgPeer(
    val peerKey: String,
    val endpoint: String,
    val allowedIp: String
)