package com.lethargyst.wireguard_flutter

import com.beust.klaxon.Klaxon
import com.wireguard.android.backend.Tunnel
import com.wireguard.config.Config
import com.wireguard.config.InetEndpoint
import com.wireguard.config.InetNetwork
import com.wireguard.config.Interface
import com.wireguard.config.Peer
import io.flutter.plugin.common.MethodChannel.Result
import java.net.InetAddress

class WgService {
    companion object Wg {
        private var wgInterface: Interface.Builder = Interface.Builder();
        private var wgPeer: Peer.Builder = Peer.Builder();
        private var wgConfig: Config.Builder = Config.Builder();

        fun setInterface(arguments: Any, result: Result) {
            val params = Klaxon().parse<WgInterface>(arguments.toString());

            if (params == null) {
                result.error("Interface", "Interface params is missing", null)
                return
            }

            wgInterface.parsePrivateKey(params.interfaceKey)
            wgInterface.addAddress(InetNetwork.parse(params.address))
            wgInterface.parseListenPort(params.listenPort)
            wgInterface.addDnsServer(InetAddress.getByName(params.dns))

            result.success("Success")
        }

        fun setPeer(arguments: Any, result: Result) {
            val params = Klaxon().parse<WgPeer>(arguments.toString());

            if (params == null) {
                result.error("Peer", "Peer params is missing", null)
                return
            }

            wgPeer.parsePublicKey(params.peerKey)
            wgPeer.setEndpoint(InetEndpoint.parse(params.endpoint))
            wgPeer.addAllowedIp(InetNetwork.parse(params.allowedIp))

            result.success("Success")
        }

        fun connect(result: Result) {
            val tunnel: Tunnel = WgTunnel()
//            val intentPrepare = GoBackend.VpnService.prepare(requireActivity())
        }
    }

}