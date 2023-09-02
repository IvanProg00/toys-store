package org.toys.exceptions;

public class EmptyStoreException extends Exception {
    public EmptyStoreException() {
        super("Store doesn't have any toy");
    }
}
