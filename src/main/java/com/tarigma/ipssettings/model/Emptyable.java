package com.tarigma.ipssettings.model;

/**
 * When a component or object usually is empty or can possibly be empty, it implements/extends this
 */
public abstract class Emptyable {

    /**
     * Is empty
     */
    private final boolean isEmpty;

    /**
     * Construct emptyable
     *
     * @param isEmpty is empty
     */
    public Emptyable(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * Returns is empty
     *
     * @return is empty
     */
    public boolean isEmpty() {
        return isEmpty;
    }
}
