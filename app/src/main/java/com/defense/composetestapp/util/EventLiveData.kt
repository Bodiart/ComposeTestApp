package com.defense.composetestapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events
 * like navigation and Snackbar messages.
 *
 * @param T The type of data hold by this instance
 */
class EventLiveData<T> : MutableLiveData<T>() {
    private val isPending = AtomicBoolean(false)
    private val pendingQueue: Queue<T> = LinkedBlockingQueue()

    override fun observe(owner: LifecycleOwner, observer: androidx.lifecycle.Observer<in T>) {
        super.observe(owner) { value ->
            if (isPending.compareAndSet(true, false)) {
                observer.onChanged(value)
                pendingQueue.poll()?.let { setValue(it) }
            }
        }
    }

    override fun setValue(value: T) {
        if (isPending.getAndSet(true)) {
            pendingQueue.offer(value)
        } else {
            super.setValue(value)
        }
    }
}