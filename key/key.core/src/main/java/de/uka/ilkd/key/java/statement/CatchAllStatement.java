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

package de.uka.ilkd.key.java.statement;

import de.uka.ilkd.key.java.*;
import de.uka.ilkd.key.java.visitor.Visitor;
import de.uka.ilkd.key.logic.op.LocationVariable;
import org.key_project.util.ExtList;

import java.util.List;


public class CatchAllStatement extends JavaNonTerminalProgramElement
        implements Statement, NonTerminalProgramElement, StatementContainer {

    private final StatementBlock body;
    private final LocationVariable param;

    public CatchAllStatement(PositionInfo pi, List<Comment> comments, StatementBlock body, LocationVariable param) {
        super(pi, comments);
        this.body = body;
        this.param = param;
    }

    public CatchAllStatement(StatementBlock body, LocationVariable param) {
        this(null, null, body, param);
    }

    public CatchAllStatement(ExtList children) {
        super(children); // for comments
        this.body = children.get(StatementBlock.class);
        this.param = children.get(LocationVariable.class);
    }


    public Statement getBody() {
        return body;
    }


    public LocationVariable getParam() {
        return param;
    }


    /**
     * Returns the number of children of this node.
     *
     * @return an int giving the number of children of this node
     */
    @Override
    public int getChildCount() {
        int i = 0;
        if (body != null) i++;
        if (param != null) i++;
        return i;
    }


    @Override
    public Statement getStatementAt(int i) {
        return body;
    }


    @Override
    public int getStatementCount() {
        return 1;
    }


    /**
     * Returns the child at the specified index in this node's "virtual"
     * child array.
     *
     * @param index an index into this node's "virtual" child array
     * @return the program element at the given position
     * @throws ArrayIndexOutOfBoundsException if <tt>index</tt> is out
     *                                        of bounds
     */
    @Override
    public ProgramElement getChildAt(int index) {
        if (index == 0) {
            return param;
        }
        if (index == 1) {
            return body;
        }
        throw new ArrayIndexOutOfBoundsException();
    }


    /**
     * calls the corresponding method of a visitor in order to
     * perform some action/transformation on this element
     *
     * @param v the Visitor
     */
    @Override
    public void visit(Visitor v) {
        v.performActionOnCatchAllStatement(this);
    }


    @Override
    public void prettyPrint(PrettyPrinter p) throws java.io.IOException {
        p.printCatchAllStatement(this);
    }


//    public Object desugar() {	
//        IProgramVariable pv = getParameterDeclaration()
//        .getVariableSpecification().getProgramVariable();
//        LocalVariableDeclaration lvd = new LocalVariableDeclaration
//        (new TypeRef(pv.getKeYJavaType()),
//                new VariableSpecification(pv, 0, NullLiteral.NULL, 
//                        pv.getKeYJavaType()));
//        ProgramVariable paramExc = new LocationVariable
//        (new ProgramElementName("e"),
//                pv.getKeYJavaType());
//        CopyAssignment ass = new CopyAssignment((Expression)pv, paramExc);
//        ParameterDeclaration parDecl 
//        = new ParameterDeclaration(new Modifier[0],
//                new TypeRef(pv.getKeYJavaType()),
//                new VariableSpecification(paramExc),
//                false);
//        Catch catchBranch = new Catch(parDecl, new StatementBlock(ass));
//        Try tryBlock = new Try(body, new Branch[]{catchBranch});
//        return new StatementBlock(new Statement[]{lvd, tryBlock});
//    }
}