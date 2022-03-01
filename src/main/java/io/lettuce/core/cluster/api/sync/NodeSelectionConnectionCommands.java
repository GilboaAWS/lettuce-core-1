/*
 * Copyright 2017-2022 the original author or authors.
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
package io.lettuce.core.cluster.api.sync;

import java.util.List;

import io.lettuce.core.KillArgs;
import io.lettuce.core.TrackingArgs;
import io.lettuce.core.UnblockType;

/**
 * Synchronous executed commands on a node selection for connection commands.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 7.0
 * @generated by io.lettuce.apigenerator.CreateSyncNodeSelectionClusterApi
 */
public interface NodeSelectionConnectionCommands<K, V> {

    /**
     * Control tracking of keys in the context of server-assisted client cache invalidation.
     *
     * @param enabled {@code true} to enable key tracking.
     * @return String simple-string-reply {@code OK}.
     * @since 6.0
     */
    Executions<String> clientCaching(boolean enabled);

    /**
     * Get the current connection name.
     *
     * @return K bulk-string-reply The connection name, or a null bulk reply if no name is set.
     */
    Executions<K> clientGetname();

    /**
     * Returns the client ID we are redirecting our tracking notifications to.
     *
     * @return the ID of the client we are redirecting the notifications to. The command returns -1 if client tracking is not
     *         enabled, or 0 if client tracking is enabled but we are not redirecting the notifications to any client.
     * @since 6.0
     */
    Executions<Long> clientGetredir();

    /**
     * Get the id of the current connection.
     *
     * @return Long The command just returns the ID of the current connection.
     * @since 5.3
     */
    Executions<Long> clientId();

    /**
     * Kill the connection of a client identified by ip:port.
     *
     * @param addr ip:port.
     * @return String simple-string-reply {@code OK} if the connection exists and has been closed.
     */
    Executions<String> clientKill(String addr);

    /**
     * Kill connections of clients which are filtered by {@code killArgs}.
     *
     * @param killArgs args for the kill operation.
     * @return Long integer-reply number of killed connections.
     */
    Executions<Long> clientKill(KillArgs killArgs);

    /**
     * Get the list of client connections.
     *
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     */
    Executions<String> clientList();

    /**
     * Stop processing commands from clients for some time.
     *
     * @param timeout the timeout value in milliseconds.
     * @return String simple-string-reply The command returns OK or an error if the timeout is invalid.
     */
    Executions<String> clientPause(long timeout);

    /**
     * Set the current connection name.
     *
     * @param name the client name.
     * @return simple-string-reply {@code OK} if the connection name was successfully set.
     */
    Executions<String> clientSetname(K name);

    /**
     * Enables the tracking feature of the Redis server, that is used for server assisted client side caching. Tracking messages
     * are either available when using the RESP3 protocol or through Pub/Sub notification when using RESP2.
     *
     * @param args for the CLIENT TRACKING operation.
     * @return String simple-string-reply {@code OK}.
     * @since 6.0
     */
    Executions<String> clientTracking(TrackingArgs args);

    /**
     * Unblock the specified blocked client.
     *
     * @param id the client id.
     * @param type unblock type.
     * @return Long integer-reply number of unblocked connections.
     * @since 5.1
     */
    Executions<Long> clientUnblock(long id, UnblockType type);

    /**
     * Echo the given string.
     *
     * @param msg the message type: value.
     * @return V bulk-string-reply.
     */
    Executions<V> echo(V msg);

    /**
     * Return the role of the instance in the context of replication.
     *
     * @return List&lt;Object&gt; array-reply where the first element is one of master, slave, sentinel and the additional
     *         elements are role-specific.
     */
    Executions<List<Object>> role();

    /**
     * Ping the server.
     *
     * @return String simple-string-reply.
     */
    Executions<String> ping();

    /**
     * Instructs Redis to disconnect the connection. Note that if auto-reconnect is enabled then Lettuce will auto-reconnect if
     * the connection was disconnected. Use {@link io.lettuce.core.api.StatefulConnection#close} to close connections and
     * release resources.
     *
     * @return String simple-string-reply always OK.
     */
    Executions<String> quit();

}
