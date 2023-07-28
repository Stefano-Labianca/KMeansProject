package com.kmeans.cluster.database;

public class EmptySetException extends Exception {
    public EmptySetException() {
        super();
    }

    public EmptySetException(String msg) {
        super(msg);
    }

}
