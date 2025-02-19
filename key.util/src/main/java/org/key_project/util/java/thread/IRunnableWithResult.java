package org.key_project.util.java.thread;

/**
 * <p>
 * A {@link Runnable} that has a result that is accessible via {@link #getResult()}.
 * </p>
 * <p>
 * Concrete implementations should be subclasses of {@link AbstractRunnableWithResult}.
 * </p>
 *
 * @author Martin Hentschel
 * @see AbstractRunnableWithResult
 */
public interface IRunnableWithResult<T> extends IRunnableWithException {
    /**
     * Returns the result.
     *
     * @return The result.
     */
    T getResult();

    /**
     * Returns an occurred exception.
     *
     * @return An occurred exception.
     */
    Exception getException();
}
