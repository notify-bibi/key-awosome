package de.uka.ilkd.key.java.statement;

import de.uka.ilkd.key.java.Comment;
import de.uka.ilkd.key.java.Label;
import de.uka.ilkd.key.java.PositionInfo;
import de.uka.ilkd.key.java.visitor.Visitor;

import org.key_project.util.ExtList;

import java.util.List;

/**
 * Continue.
 *
 */
public class Continue extends LabelJumpStatement {

    /**
     * Continue.
     */
    public Continue() {
        super();
    }

    /**
     * Continue.
     *
     * @param label an identifier.
     */
    public Continue(Label label) {
        super(label);
    }

    /**
     * Constructor for the transformation of COMPOST ASTs to KeY.
     *
     * @param children the children of this AST element as KeY classes. May contain: Comments, a
     *        ProgramElementName (as label of the label jump statement)
     */
    public Continue(ExtList children) {
        super(children);
    }

    public Continue(PositionInfo pi, List<Comment> c, Label name) {
    }

    /**
     * calls the corresponding method of a visitor in order to perform some action/transformation on
     * this element
     *
     * @param v the Visitor
     */
    public void visit(Visitor v) {
        v.performActionOnContinue(this);
    }
}