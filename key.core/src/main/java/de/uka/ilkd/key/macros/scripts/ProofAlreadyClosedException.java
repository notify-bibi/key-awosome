package de.uka.ilkd.key.macros.scripts;

/**
 * Thrown if during the execution of a command, the proof is already closed. May or may not lead to
 * exceptional termination of the whole script based on the <code>@failonclosed</code> setting.
 *
 * @author Dominic Steinhoefel
 */
public class ProofAlreadyClosedException extends ScriptException {
    private static final long serialVersionUID = 1L;

    public ProofAlreadyClosedException(String message) {
        super(message);
    }
}
