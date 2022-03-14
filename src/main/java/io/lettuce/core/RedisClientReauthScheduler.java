package io.lettuce.core;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import io.lettuce.core.resource.ClientResources;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class RedisClientReauthScheduler implements Runnable {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(RedisClientReauthScheduler.class);
    private final AtomicBoolean clientReauthActivated = new AtomicBoolean(false);
    private final AtomicReference<ScheduledFuture<?>> clientReauthFuture = new AtomicReference<>();

    private final Supplier<ClientOptions> clientOptions;
    private final ClientResources clientResources;
    private final EventExecutorGroup genericWorkerPool;

    private final AbstractRedisClient arc;

    public RedisClientReauthScheduler(
        Supplier<ClientOptions> clientOptions,
        ClientResources clientResources,
        AbstractRedisClient arc)
    {
        this.clientOptions = clientOptions;
        this.clientResources = clientResources;
        this.genericWorkerPool = this.clientResources.eventExecutorGroup();
        this.arc = arc;
    }

    public void activateReauthIfNeeded() {

        ClientOptions options = clientOptions.get();        

        if (false == options.isPeriodicReauthenticate()) {
            return;
        }

        if (clientReauthActivated.compareAndSet(false, true)) {
            ScheduledFuture<?> scheduledFuture = genericWorkerPool.scheduleAtFixedRate(this,
                options.getReauthenticatePeriod().toNanos(), options.getReauthenticatePeriod().toNanos(), TimeUnit.NANOSECONDS);
            clientReauthFuture.set(scheduledFuture);
        }
    }

    /**
     * Disable periodic reauthentication
     */
    public void shutdown() {

        if (clientReauthActivated.compareAndSet(true, false)) {

            ScheduledFuture<?> scheduledFuture = clientReauthFuture.get();

            try {
                scheduledFuture.cancel(false);
                clientReauthFuture.set(null);
            } catch (Exception e) {
                logger.debug("Could not cancel client reauth", e);
            }
        }
    }

    @Override
    public void run() {
        arc.reauthInConnections();
    }    
}
