package de.uka.ilkd.key.java.expression.operator;

import de.uka.ilkd.key.java.Comment;
import de.uka.ilkd.key.java.Expression;
import de.uka.ilkd.key.java.PositionInfo;
import de.uka.ilkd.key.java.visitor.Visitor;
import org.key_project.util.ExtList;

import java.util.List;

/**
 * Binary or.
 *
 * @author <TT>AutoDoc</TT>
 */

public class BinaryOr extends BinaryOperator {


    /**
     * Binary or.
     *
     * @param children an ExtList with all children of this node the first children in list will be
     *                 the one on the left side, the second the one on the right side.
     */

    public BinaryOr(ExtList children) {
        super(children);
    }

    public BinaryOr(PositionInfo pi, List<Comment> c, Expression lhs, Expression rhs) {
        super(pi, c, lhs, rhs);
    }


    /**
     * Get precedence.
     *
     * @return the int value.
     */

    public int getPrecedence() {
        return 9;
    }

    /**
     * Get notation.
     *
     * @return the int value.
     */

    public int getNotation() {
        return INFIX;
    }

    /**
     * calls the corresponding method of a visitor in order to perform some action/transformation on
     * this element
     *
     * @param v the Visitor
     */
    public void visit(Visitor v) {
        v.performActionOnBinaryOr(this);
    }

}