// This file is part of KeY - Integrated Deductive Software Design
//
// Copyright (C) 2001-2011 Universitaet Karlsruhe (TH), Germany
//                         Universitaet Koblenz-Landau, Germany
//                         Chalmers University of Technology, Sweden
// Copyright (C) 2011-2017 Karlsruhe Institute of Technology, Germany
//                         Technical University Darmstadt, Germany
//                         Chalmers University of Technology, Sweden
//
// The KeY system is protected by the GNU General
// Public License. See LICENSE.TXT for details.
//

package de.uka.ilkd.key.java;

import java.io.IOException;
import java.util.List;

import org.key_project.util.ExtList;

import de.uka.ilkd.key.java.visitor.Visitor;

/**
 * A "\Break label" parameter declaration of a ccatch clause.
 *
 * @author Dominic Steinhöfel
 */
public class CcatchBreakLabelParameterDeclaration
        extends CcatchNonstandardParameterDeclaration {

    private final Label label;

    public CcatchBreakLabelParameterDeclaration(PositionInfo pi, List<Comment> comments, Label label) {
        super(pi, comments);
        this.label = label;
    }

    public CcatchBreakLabelParameterDeclaration(ExtList children) {
        super(null, null);
        label = children.get(Label.class);
    }

    @Override
    public int getChildCount() {
        return (label != null) ? 1 : 0;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public ProgramElement getChildAt(int index) {
        if (label != null) {
            if (index == 0)
                return label;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void visit(Visitor v) {
        v.performActionOnCcatchBreakLabelParameterDeclaration(this);
    }

    @Override
    public void prettyPrint(PrettyPrinter w) throws IOException {
        w.printCcatchBreakLabelParameterDeclaration(this);
    }

}