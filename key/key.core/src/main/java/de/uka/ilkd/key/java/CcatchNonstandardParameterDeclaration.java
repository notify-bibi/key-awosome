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

import java.util.List;

/**
 * A "non-standard" parameter declaration of a Ccatch clause, e.g., "\Return".
 *
 * @author Dominic Steinhöfel
 */
public abstract class CcatchNonstandardParameterDeclaration
        extends JavaNonTerminalProgramElement {

    public CcatchNonstandardParameterDeclaration(PositionInfo pi, List<Comment> comments) {
        super(pi, comments);
    }
}