// This file is part of KeY - Integrated Deductive Software Design
//
// Copyright (C) 2001-2011 Universitaet Karlsruhe (TH), Germany
//                         Universitaet Koblenz-Landau, Germany
//                         Chalmers University of Technology, Sweden
// Copyright (C) 2011-2014 Karlsruhe Institute of Technology, Germany
//                         Technical University Darmstadt, Germany
//                         Chalmers University of Technology, Sweden
//
// The KeY system is protected by the GNU General
// Public License. See LICENSE.TXT for details.
//

package de.uka.ilkd.key.java.expression.literal;

import de.uka.ilkd.key.java.*;
import de.uka.ilkd.key.java.abstraction.KeYJavaType;
import de.uka.ilkd.key.java.abstraction.PrimitiveType;
import de.uka.ilkd.key.java.expression.Literal;
import de.uka.ilkd.key.java.visitor.Visitor;
import de.uka.ilkd.key.ldt.DoubleLDT;
import de.uka.ilkd.key.logic.Name;
import org.key_project.util.ExtList;

import javax.annotation.Nullable;
import java.util.List;

public final class DoubleLiteral extends Literal {
    /**
     * Textual representation of the value.
     */
    private final String value;

    public DoubleLiteral(@Nullable PositionInfo pi, @Nullable List<Comment> comments, String value) {
        super(pi, comments);
        this.value = value;
    }

    /**
     * Double literal.
     *
     * @param value a double value.
     */
    public DoubleLiteral(double value) {
        this(null, null, "" + value);
    }

    /**
     * Double literal.
     *
     * @param children list with all children(here:comments)
     *                 May contain: Comments
     * @param value    a string.
     */

    public DoubleLiteral(ExtList children, String value) {
        super(children);
        this.value = value;
    }

    /**
     * Double literal.
     *
     * @param value a string.
     */

    public DoubleLiteral(String value) {
        this(null, null, value);
    }

    /**
     * tests if equals
     */
    public boolean equalsModRenaming(SourceElement o,
                                     NameAbstractionTable nat) {
        if (!(o instanceof DoubleLiteral)) {
            return false;
        }
        return ((DoubleLiteral) o).getValue().equals(getValue());
    }

    @Override
    protected int computeHashCode() {
        return 37 * super.computeHashCode() + getValue().hashCode();
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Get value.
     *
     * @return the string.
     */

    public String getValue() {
        return value;
    }

    /**
     * calls the corresponding method of a visitor in order to
     * perform some action/transformation on this element
     *
     * @param v the Visitor
     */
    public void visit(Visitor v) {
        v.performActionOnDoubleLiteral(this);
    }

    public void prettyPrint(PrettyPrinter p) throws java.io.IOException {
        p.printDoubleLiteral(this);
    }

    public KeYJavaType getKeYJavaType(Services javaServ) {
        return javaServ.getJavaInfo().getKeYJavaType(PrimitiveType.JAVA_DOUBLE);
    }

    @Override
    public Name getLDTName() {
        return DoubleLDT.NAME;
    }

}