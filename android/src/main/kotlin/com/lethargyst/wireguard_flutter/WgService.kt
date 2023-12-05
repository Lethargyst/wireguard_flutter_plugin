package com.lethargyst.wireguard_flutter

import android.app.Activity
import com.beust.klaxon.Klaxon
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.GoBackend
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
        private var wgInterface: Interface? = null;
        private var wgPeer: Peer? = null;
        private var wgConfig: Config? = null;

        fun setInterface(arguments: Any, result: Result) {
            val params = Klaxon().parse<WgInterface>(arguments.toString());

            if (params == null) {
                result.error("Interface", "Interface params is missing", null)
                return
            }

            val interfaceBuilder = Interface.Builder()
            interfaceBuilder.parsePrivateKey(params.interfaceKey)
            interfaceBuilder.addAddress(InetNetwork.parse(params.address))
            interfaceBuilder.parseListenPort(params.listenPort)
            interfaceBuilder.addDnsServer(InetAddress.getByName(params.dns))

            wgInterface = interfaceBuilder.build()

            result.success("Success")
        }

        fun setPeer(arguments: Any, result: Result) {
            val params = Klaxon().parse<WgPeer>(arguments.toString())

            if (params == null) {
                result.error("Peer", "Peer params is missing", null)
                return
            }

            val peerBuilder = Peer.Builder()
            peerBuilder.parsePublicKey(params.peerKey)
            peerBuilder.setEndpoint(InetEndpoint.parse(params.endpoint))
            peerBuilder.addAllowedIp(InetNetwork.parse(params.allowedIp))

            wgPeer = peerBuilder.build()

            result.success("Success")
        }

        fun toggleConnection(context: Activity, result: Result) {
            if (wgInterface == null) {
                result.error("Interface","There are no wireguard interface configured!", null)
                return
            }
            if (wgPeer == null) {
                result.error("Peer", "There are no wireguard peer configured!", null)
                return
            }

            val tunnel: Tunnel = WgTunnel()
            val intent = GoBackend.VpnService.prepare(context)

            if (intent != null) {
                context.startActivityForResult(intent, 0)
            }

            val backend: Backend = GoBackend(context)
            wgConfig = Config.Builder()
                .setInterface(wgInterface!!)
                .addPeer(wgPeer!!)
                .build()

            backend.setState(tunnel, Tunnel.State.TOGGLE, wgConfig)
        }
    }

}