package de.uka.ilkd.key.java.expression.operator;

import de.uka.ilkd.key.java.*;
import de.uka.ilkd.key.java.abstraction.KeYJavaType;
import de.uka.ilkd.key.java.abstraction.PrimitiveType;
import de.uka.ilkd.key.java.reference.ExecutionContext;
import de.uka.ilkd.key.java.reference.TypeReference;
import de.uka.ilkd.key.java.visitor.Visitor;

import org.key_project.util.ExtList;

/**
 * Instanceof.
 *
 * @author <TT>AutoDoc</TT>
 */

public class Instanceof extends TypeOperator {


    /**
     * Instanceof.
     *
     * @param children an ExtList with all children of this node the first children in list will be
     *        the expression on the left side, the second the one on the right side a type
     *        reference.
     */

    public Instanceof(ExtList children) {
        super(children);
        assert getChildCount() == 2 : "not 2 children but " + getChildCount();
    }


    public Instanceof(Expression unaryChild, TypeReference typeref) {
        super(unaryChild, typeref);
        assert getChildCount() == 2 : "not 2 children but " + getChildCount();
    }

    /**
     * Returns the number of children of this node.
     *
     * @return an int giving the number of children of this node
     */

    public int getChildCount() {
        int result = 0;
        if (children != null) {
            result += children.size();
        }
        if (typeReference != null) {
            result++;
        }
        return result;
    }

    public SourceElement getLastElement() {
        return typeReference;
    }

    /**
     * Returns the child at the specified index in this node's "virtual" child array
     *
     * @param index an index into this node's "virtual" child array
     * @return the program element at the given position
     * @exception ArrayIndexOutOfBoundsException if <tt>index</tt> is out of bounds
     */

    public ProgramElement getChildAt(int index) {
        int len;
        if (children != null) {
            len = children.size();
            if (len > index) {
                return children.get(index);
            }
            index -= len;
        }
        if (typeReference != null) {
            if (index == 0) {
                return typeReference;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * Get arity.
     *
     * @return the int value.
     */

    public int getArity() {
        return 1;
    }

    /**
     * Get precedence.
     *
     * @return the int value.
     */

    public int getPrecedence() {
        return 5;
    }

    /**
     * Get notation.
     *
     * @return the int value.
     */

    public int getNotation() {
        return POSTFIX;
    }

    /**
     * calls the corresponding method of a visitor in order to perform some action/transformation on
     * this element
     *
     * @param v the Visitor
     */
    public void visit(Visitor v) {
        v.performActionOnInstanceof(this);
    }

    public KeYJavaType getKeYJavaType(Services javaServ) {
        return javaServ.getJavaInfo().getKeYJavaType(PrimitiveType.JAVA_BOOLEAN);
    }

    public KeYJavaType getKeYJavaType(Services javaServ, ExecutionContext ec) {
        return getKeYJavaType(javaServ);
    }

}
