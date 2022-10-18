/*
 * Copyright 2018-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import io.lettuce.core.protocol.ProtocolVersion;

/**
 * Unit tests for {@link ClientOptions}.
 *
 * @author Mark Paluch
 */
class ClientOptionsUnitTests {

    @Test
    void testNew() {
        checkAssertions(ClientOptions.create());
    }

    @Test
    void testBuilder() {
        ClientOptions options = ClientOptions.builder()
            .scriptCharset(StandardCharsets.US_ASCII)
            .enablePeriodicReauthentication(Duration.ofSeconds(10))
            .build();
        checkAssertions(options);
        assertThat(options.getScriptCharset()).isEqualTo(StandardCharsets.US_ASCII);
        assertThat(options.getReauthenticationPeriod()).isEqualTo(Duration.ofSeconds(10));
    }

    @Test
    void testCopy() {

        ClientOptions original = ClientOptions.builder()
            .scriptCharset(StandardCharsets.US_ASCII)
            .enablePeriodicReauthentication(Duration.ofSeconds(10))
            .build();
        ClientOptions copy = ClientOptions.copyOf(original);

        checkAssertions(copy);
        assertThat(copy.getScriptCharset()).isEqualTo(StandardCharsets.US_ASCII);
        assertThat(copy.mutate().build().getScriptCharset()).isEqualTo(StandardCharsets.US_ASCII);
        
        assertThat(copy.getReauthenticationPeriod()).isEqualTo(Duration.ofSeconds(10));
        assertThat(copy.mutate().build().getReauthenticationPeriod()).isEqualTo(Duration.ofSeconds(10));

        assertThat(original.mutate()).isNotSameAs(copy.mutate());
    }

    void checkAssertions(ClientOptions sut) {
        assertThat(sut.isAutoReconnect()).isTrue();
        assertThat(sut.isCancelCommandsOnReconnectFailure()).isFalse();
        assertThat(sut.getProtocolVersion()).isEqualTo(ProtocolVersion.RESP3);
        assertThat(sut.isSuspendReconnectOnProtocolFailure()).isFalse();
        assertThat(sut.getDisconnectedBehavior()).isEqualTo(ClientOptions.DisconnectedBehavior.DEFAULT);
    }
}
