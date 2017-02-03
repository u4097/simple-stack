package com.zhuinden.simplestack;

import android.os.Parcelable;

import java.util.List;

/**
 * Represents the state that will be available once state change is complete.
 *
 * Created by Zhuinden on 2017. 01. 16..
 */

class PendingStateChange {
    enum Status {
        ENQUEUED,
        IN_PROGRESS,
        COMPLETED
    }

    final List<Parcelable> newHistory;
    final int direction;
    final boolean initialization;

    private Status status = Status.ENQUEUED;

    PendingStateChange(List<Parcelable> newHistory, @StateChange.StateChangeDirection int direction, boolean initialization) {
        this.newHistory = newHistory;
        this.direction = direction;
        this.initialization = initialization;
    }

    Status getStatus() {
        return status;
    }

    void setStatus(Status status) {
        if(status == null) {
            throw new NullPointerException("Status of pending state change cannot be null!");
        }
        if(status.ordinal() < this.status.ordinal()) {
            throw new IllegalStateException("A pending state change cannot go to one of its previous states: [" + this.status + "] to [" + status + "]");
        }
        this.status = status;
    }
}
