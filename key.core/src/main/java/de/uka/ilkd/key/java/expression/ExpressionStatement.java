package de.uka.ilkd.key.java.expression;


import de.uka.ilkd.key.java.Expression;
import de.uka.ilkd.key.java.LoopInitializer;

/**
 * An ExpressionStatement is a statement that may appear as an expression. There are three
 * subclasses: MethodReference, Assignment, and New. Strictly speaking, Java would allow any
 * expression as a statement; however, this does not make much sense (except backward compatibility
 * to awkward C code) and is even forbidden in dialects (such as Generic Java).
 */

public interface ExpressionStatement extends Expression, LoopInitializer {
}
