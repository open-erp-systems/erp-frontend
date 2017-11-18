package com.jukusoft.erp.network.utils;

public interface NetworkResult<T> {

    /**
     * The result of the operation. This will be null if the operation failed.
     *
     * @return the result or null if the operation failed.
     */
    T result();

    /**
     * A Throwable describing failure. This will be null if the operation succeeded.
     *
     * @return the cause or null if the operation succeeded.
     */
    Throwable cause();

    /**
     * Did it succeed?
     *
     * @return true if it succeded or false otherwise
     */
    boolean succeeded();

    /**
     * Did it fail?
     *
     * @return true if it failed or false otherwise
     */
    boolean failed();

    public static <T> WritableNetworkResult<T> create () {
        return new WritableNetworkResult<>();
    }

    public static <T> WritableNetworkResult<T> complete (T result) {
        WritableNetworkResult<T> res = new WritableNetworkResult<>();

        res.complete(result);

        return res;
    }

    public static <T> WritableNetworkResult<T> fail (Throwable cause, Class<T> cls) {
        WritableNetworkResult<T> res = new WritableNetworkResult<>();

        res.fail(cause);

        return res;
    }

    public static <T> WritableNetworkResult<T> fail (Throwable cause) {
        WritableNetworkResult<T> res = new WritableNetworkResult<>();

        res.fail(cause);

        return res;
    }

}
