package de.uka.ilkd.key.rule.tacletbuilder;

import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.rule.BoundUniquenessChecker;
import de.uka.ilkd.key.rule.FindTaclet;

/**
 * Superclass of TacletBuilder objects that have a non-empty find clause. This should be all of them
 * except NoFindTacletBuilder.
 */

public abstract class FindTacletBuilder<T extends FindTaclet> extends TacletBuilder<T> {

    protected Term find = null;

    /**
     * checks that a SchemaVariable that is used to match pure variables (this means bound
     * variables) occurs at most once in a quantifier of the assumes and finds and throws an
     * exception otherwise
     */
    protected void checkBoundInIfAndFind() {
        final BoundUniquenessChecker ch = new BoundUniquenessChecker(getFind(), ifSequent());
        if (!ch.correct()) {
            throw new TacletBuilderException(this,
                "A bound SchemaVariable variables occurs both " + "in assumes and find clauses.");
        }
    }

    /*
     * Get the `find' term. This could be a term or a formula for a RewriteTaclet, but only a
     * formula for an Antec/Succ Taclet.
     */
    public Term getFind() {
        return find;
    }

}
