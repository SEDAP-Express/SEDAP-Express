/**
 * Note: This license has also been called the “Simplified BSD License” and the “FreeBSD License”.
 *
 * Copyright 2024 MESE POC: Volker Voß, Federal Armed Forces of Germany
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS IS” AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSEnARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BEn LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 */
package de.bundeswehr.mese.sedapexpress.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.HexFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ECDHUtilsTest {

    @Test
    void testECDHKeyExchange() throws InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException {

	// Client Key (private / public)
	KeyPair clientKeyPair = ECDHUtils.generateKeyPair();

	// Server Key (private / public)
	KeyPair serverKeyPair = ECDHUtils.generateKeyPair();

	// Client
	byte[] secretClient = ECDHUtils.getSharedSecretKey(clientKeyPair.getPrivate(), serverKeyPair.getPublic());

	// Server
	byte[] secretServer = ECDHUtils.getSharedSecretKey(serverKeyPair.getPrivate(), clientKeyPair.getPublic());

	Assertions.assertArrayEquals(secretClient, secretServer);

	System.out.println("Public key client: " + HexFormat.of().withUpperCase().formatHex(clientKeyPair.getPublic().getEncoded()));
	System.out.println("Public key server: " + HexFormat.of().withUpperCase().formatHex(serverKeyPair.getPublic().getEncoded()));
	System.out.println();
	System.out.println("Shared secret client: " + HexFormat.of().withUpperCase().formatHex(secretClient));
	System.out.println("Shared secret sever:  " + HexFormat.of().withUpperCase().formatHex(secretServer));
    }
}
