package com.lethargyst.wireguard_flutter

import com.wireguard.android.backend.Tunnel

class WgTunnel : Tunnel {
    override fun getName(): String {
        return "default_tunnel"
    }

    override fun onStateChange(newState: Tunnel.State) {}
}