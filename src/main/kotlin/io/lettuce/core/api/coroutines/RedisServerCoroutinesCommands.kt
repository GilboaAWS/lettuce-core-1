/*
 * Copyright 2020-2022 the original author or authors.
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

package io.lettuce.core.api.coroutines

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.FlushMode
import io.lettuce.core.ShutdownArgs
import io.lettuce.core.protocol.CommandType
import java.util.*

/**
 * Coroutine executed commands for Server Control.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mikhael Sokolov
 * @since 6.0
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisServerCoroutinesCommands<K : Any, V : Any> {

    /**
     * Asynchronously rewrite the append-only file.
     *
     * @return String simple-string-reply always `OK`.
     */
    suspend fun bgrewriteaof(): String?

    /**
     * Asynchronously save the dataset to disk.
     *
     * @return String simple-string-reply.
     */
    suspend fun bgsave(): String?

    /**
     * Returns an array reply of details about all Redis commands.
     *
     * @return List<Any> array-reply.
     */
    suspend fun command(): List<Any>

    /**
     * Get total number of Redis commands.
     *
     * @return Long integer-reply of number of total commands in this Redis server.
     */
    suspend fun commandCount(): Long?

    /**
     * Returns an array reply of details about the requested commands.
     *
     * @param commands the commands to query for.
     * @return List<Any> array-reply.
     */
    suspend fun commandInfo(vararg commands: String): List<Any>

    /**
     * Returns an array reply of details about the requested commands.
     *
     * @param commands the commands to query for.
     * @return List<Any> array-reply.
     */
    suspend fun commandInfo(vararg commands: CommandType): List<Any>

    /**
     * Get the value of a configuration parameter.
     *
     * @param parameter name of the parameter.
     * @return Map<String, String> bulk-string-reply.
     */
    suspend fun configGet(parameter: String): Map<String, String>?

    /**
     * Get the value of multiple pattern parameters.
     *
     * @param parameters patterns names of Redis server's configuration.
     * @return Map<String, String> bulk-string-reply.
     * @since 6.2
     */
    suspend fun configGet(vararg parameters: String): Map<String, String>?

    /**
     * Reset the stats returned by INFO.
     *
     * @return String simple-string-reply always `OK`.
     */
    suspend fun configResetstat(): String?

    /**
     * Rewrite the configuration file with the in memory configuration.
     *
     * @return String simple-string-reply `OK` when the configuration was rewritten properly. Otherwise an error is
     *         returned.
     */
    suspend fun configRewrite(): String?

    /**
     * Set a configuration parameter to the given value.
     *
     * @param parameter the parameter name.
     * @param value the parameter value.
     * @return String simple-string-reply: `OK` when the configuration was set properly. Otherwise an error is returned.
     */
    suspend fun configSet(parameter: String, value: String): String?

    /**
     * Set multiple parameters to the given value.
     *
     * @param kvs the parameter name and value.
     * @return String simple-string-reply: `OK` when the configuration was set properly. Otherwise an error is returned.
     * @since 6.2
     */
    suspend fun configSet(kvs: Map<String, String>): String?

    /**
     * Return the number of keys in the selected database.
     *
     * @return Long integer-reply.
     */
    suspend fun dbsize(): Long?

    /**
     * Crash and recover.
     *
     * @param delay optional delay in milliseconds.
     * @return String simple-string-reply.
     */
    suspend fun debugCrashAndRecover(delay: Long): String?

    /**
     * Get debugging information about the internal hash-table state.
     *
     * @param db the database number.
     * @return String simple-string-reply.
     */
    suspend fun debugHtstats(db: Int): String?

    /**
     * Get debugging information about a key.
     *
     * @param key the key.
     * @return String simple-string-reply.
     */
    suspend fun debugObject(key: K): String?

    /**
     * Make the server crash: Out of memory.
     *
     * @return nothing, because the server crashes before returning.
     */
    suspend fun debugOom()

    /**
     * Save RDB, clear the database and reload RDB.
     *
     * @return String simple-string-reply The commands returns OK on success.
     */
    suspend fun debugReload(): String?

    /**
     * Restart the server gracefully.
     *
     * @param delay optional delay in milliseconds.
     * @return String simple-string-reply.
     */
    suspend fun debugRestart(delay: Long): String?

    /**
     * Get debugging information about the internal SDS length.
     *
     * @param key the key.
     * @return String simple-string-reply.
     */
    suspend fun debugSdslen(key: K): String?

    /**
     * Make the server crash: Invalid pointer access.
     *
     * @return nothing, because the server crashes before returning.
     */
    suspend fun debugSegfault()

    /**
     * Remove all keys from all databases.
     *
     * @return String simple-string-reply.
     */
    suspend fun flushall(): String?

    /**
     * Remove all keys from all databases using the specified [FlushMode].
     *
     * @param flushMode the flush mode (sync/async).
     * @return String simple-string-reply.
     * @since 6.1
     */
    suspend fun flushall(flushMode: FlushMode): String?

    /**
     * Remove all keys asynchronously from all databases.
     *
     * @return String simple-string-reply.
     * @deprecated since 6.1, use [flushall(FlushMode)] instead.
     */
    @Deprecated("Use [flushall(FlushMode.ASYNC)] instead.", ReplaceWith("flushall(FlushMode.ASYNC)"))
    suspend fun flushallAsync(): String?

    /**
     * Remove all keys from the current database.
     *
     * @return String simple-string-reply.
     */
    suspend fun flushdb(): String?

    /**
     * Remove all keys from the current database using the specified [FlushMode].
     *
     * @param flushMode the flush mode (sync/async).
     * @return String simple-string-reply.
     * @since 6.1
     */
    suspend fun flushdb(flushMode: FlushMode): String?

    /**
     * Remove all keys asynchronously from the current database.
     *
     * @return String simple-string-reply.
     * @deprecated since 6.1, use [flushdb(FlushMode)] instead.
     */
    @Deprecated("Use [flushdb(FlushMode.ASYNC)] instead.", ReplaceWith("flushdb(FlushMode.ASYNC)"))
    suspend fun flushdbAsync(): String?

    /**
     * Get information and statistics about the server.
     *
     * @return String bulk-string-reply as a collection of text lines.
     */
    suspend fun info(): String?

    /**
     * Get information and statistics about the server.
     *
     * @param section the section type: string.
     * @return String bulk-string-reply as a collection of text lines.
     */
    suspend fun info(section: String): String?

    /**
     * Get the UNIX time stamp of the last successful save to disk.
     *
     * @return Date integer-reply an UNIX time stamp.
     */
	suspend fun lastsave(): Date?

	/**
	 * Reports the number of bytes that a key and its value require to be stored in RAM.
	 *
	 * @return memory usage in bytes.
	 * @since 5.2
	 */
	suspend fun memoryUsage(key: K): Long?

	/**
	 * Make the server a replica of another instance.
	 *
	 * @param host the host type: string.
	 * @param port the port type: string.
	 * @return String simple-string-reply.
	 * @since 6.1.7
	 */
	suspend fun replicaof(host: String, port: Int): String?

	/**
	 * Promote server as master.
	 *
	 * @return String simple-string-reply.
	 * @since 6.1.7
	 */
	suspend fun replicaofNoOne(): String?

	/**
	 * Synchronously save the dataset to disk.
	 *
	 * @return String simple-string-reply The commands returns OK on success.
	 */
	suspend fun save(): String?

	/**
	 * Synchronously save the dataset to disk and then shut down the server.
	 *
	 * @param save @code true} force save operation.
	 */
	suspend fun shutdown(save: Boolean)

	/**
	 * Synchronously save the dataset to disk and then shutdown the server.
	 *
	 * @param args
	 * @since 6.2
	 */
	suspend fun shutdown(args: ShutdownArgs)

    /**
	 * Make the server a replica of another instance.
	 *
	 * @param host the host type: string.
	 * @param port the port type: string.
	 * @return String simple-string-reply.
	 * @deprecated since 6.1.7, use [replicaof(String, Integer)] instead.
     */
	@Deprecated(message = "since 6.1.7", replaceWith = ReplaceWith("replicaof(host, port)"))
    suspend fun slaveof(host: String, port: Int): String?

    /**
	 * Promote server as master.
	 *
	 * @return String simple-string-reply.
	 * @deprecated since 6.1.7, use [replicaofNoOne] instead.
     */
	@Deprecated(message = "since 6.1.7", replaceWith = ReplaceWith("replicaofNoOne()"))
    suspend fun slaveofNoOne(): String?

    /**
     * Read the slow log.
     *
     * @return List<Any> deeply nested multi bulk replies.
     */
    suspend fun slowlogGet(): List<Any>

	/**
	 * Read the slow log.
	 *
	 * @param count the count.
	 * @return List<Any> deeply nested multi bulk replies.
	 */
	suspend fun slowlogGet(count: Int): List<Any>

	/**
     * Obtaining the current length of the slow log.
     *
     * @return Long length of the slow log.
     */
    suspend fun slowlogLen(): Long?

    /**
     * Resetting the slow log.
     *
     * @return String simple-string-reply The commands returns OK on success.
     */
    suspend fun slowlogReset(): String?

    /**
     * Return the current server time.
     *
     * @return List<V> array-reply specifically:
     *
     *         A multi bulk reply containing two elements:
     *
     *         unix time in seconds. microseconds.
     */
    suspend fun time(): List<V>

}

