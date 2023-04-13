/*
 * SPDX-License-Identifier: MIT
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2023 games647 and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.games647.fastlogin.bukkit.listener.protocollib.packet;

import lombok.Value;
import lombok.experimental.Accessors;

import java.security.PublicKey;
import java.time.Instant;
import java.util.Base64;
import java.util.StringJoiner;

@Accessors(fluent = true)
@Value(staticConstructor = "of")
public class ClientPublicKey {
    Instant expiry;
    PublicKey key;
    byte[] signature;

    public boolean isExpired(Instant verifyTimestamp) {
        return !verifyTimestamp.isBefore(expiry);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientPublicKey.class.getSimpleName() + '[', "]")
                .add("expiry=" + expiry)
                .add("key=" + Base64.getEncoder().encodeToString(key.getEncoded()))
                .add("signature=" + Base64.getEncoder().encodeToString(signature))
                .toString();
    }
}
