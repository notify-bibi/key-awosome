/**
 * This class encapsulates a guard for a loop
 */

package de.uka.ilkd.key.java.statement;

import de.uka.ilkd.key.java.*;
import de.uka.ilkd.key.java.visitor.Visitor;
import org.key_project.util.ExtList;

import java.util.List;

public class Guard extends JavaNonTerminalProgramElement implements IGuard {

    final Expression expr;

    public Guard(Expression expression) {
        expr = expression;
    }

    public Guard(ExtList children) {
        expr = children.get(Expression.class);
    }

    public Guard(PositionInfo pi, List<Comment> o1, Expression accepto) {
        super(pi, o1);
        this.expr = accepto;
    }

    public Expression getExpression() {
        return expr;
    }

    public void visit(Visitor v) {
        v.performActionOnGuard(this);
    }

    public int getChildCount() {
        return (expr != null) ? 1 : 0;
    }

    public ProgramElement getChildAt(int index) {
        if (index == 0) {
            return expr;
        }
        return null;
    }

    public String toString() {
        return expr.toString();
    }
}