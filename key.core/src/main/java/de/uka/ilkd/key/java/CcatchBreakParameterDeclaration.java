package de.uka.ilkd.key.java;

import de.uka.ilkd.key.java.visitor.Visitor;

import org.key_project.util.ExtList;

import java.util.List;

/**
 * A "\Break" parameter declaration of a ccatch clause.
 *
 * @author Dominic Steinhöfel
 */
public class CcatchBreakParameterDeclaration extends CcatchNonstandardParameterDeclaration {

    public CcatchBreakParameterDeclaration(ExtList children) {
    }

    public CcatchBreakParameterDeclaration(PositionInfo pi, List<Comment> c) {
        super();
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public ProgramElement getChildAt(int index) {
        return null;
    }

    @Override
    public void visit(Visitor v) {
        v.performActionOnCcatchBreakParameterDeclaration(this);
    }

}