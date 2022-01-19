package io.micronaut.transaction.interceptor;

import io.micronaut.aop.kotlin.KotlinInterceptedMethod;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Internal;
import io.micronaut.transaction.support.TransactionSynchronizationManager;
import jakarta.inject.Singleton;
import kotlin.coroutines.CoroutineContext;

/**
 * Helper to setup Kotlin coroutine context.
 *
 * @author Denis Stepanov
 * @since 3.3
 */
@Internal
@Singleton
@Requires(classes = kotlin.coroutines.CoroutineContext.class)
public final class CoroutineTxHelper {

    public void setupCoroutineContext(KotlinInterceptedMethod kotlinInterceptedMethod) {
        CoroutineContext existingContext = kotlinInterceptedMethod.getCoroutineContext();
        kotlinInterceptedMethod.updateCoroutineContext(existingContext.plus(new TxContextElement(TransactionSynchronizationManager.copyState())));
    }

}
