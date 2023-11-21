package com.lethargyst.wireguard_flutter

import com.wireguard.crypto.KeyPair

class WgKeysService {
    companion object WgKeys {
        private var keyPair: KeyPair? = null;

        fun genKeyPair() {
            keyPair = KeyPair();
        }

        fun genPrivateKey() : String {
            if (keyPair == null) {
                keyPair = KeyPair();
            }

            return keyPair!!.privateKey.toBase64();
        }

        fun genPublicKey() : String {
            if (keyPair == null) {
                keyPair = KeyPair();
            }
            return keyPair!!.publicKey.toBase64();
        }
    }
}