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

package de.uka.ilkd.key.rule.join.procedures;

import org.key_project.util.collection.DefaultImmutableSet;
import org.key_project.util.collection.ImmutableSet;

import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.logic.Name;
import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.logic.TermBuilder;
import de.uka.ilkd.key.logic.op.Function;
import de.uka.ilkd.key.logic.op.LocationVariable;
import de.uka.ilkd.key.rule.join.JoinProcedure;
import de.uka.ilkd.key.rule.join.JoinRule;
import de.uka.ilkd.key.util.Quadruple;
import de.uka.ilkd.key.util.Triple;
import de.uka.ilkd.key.util.joinrule.JoinRuleUtils;
import de.uka.ilkd.key.util.joinrule.SymbolicExecutionState;

/**
 * Rule that joins two sequents based on the if-then-else
 * construction: If two locations are assigned different
 * values in the states, the value in the joined state is
 * chosen based on the path condition. This rule retains
 * total precision. In contrast to the {@link JoinIfThenElse}
 * rule, the distinction is not realized in the update /
 * symbolic state, but in the antecedent / path condition.
 * This can be much more efficient.
 * 
 * @author Dominic Scheurer
 * @see JoinIfThenElse
 * @see JoinRule
 */
public class JoinIfThenElseAntecedent extends JoinProcedure {
   
   private static JoinIfThenElseAntecedent INSTANCE = null;
   
   public static JoinIfThenElseAntecedent instance() {
       if (INSTANCE == null) {
           INSTANCE = new JoinIfThenElseAntecedent();
       }
       return INSTANCE;
   }
   
   private static final String DISPLAY_NAME = "JoinByIfThenElseAntecedent";

   @Override
   public Triple<ImmutableSet<Term>, Term, ImmutableSet<Name>> joinValuesInStates(
         LocationVariable v,
         SymbolicExecutionState state1,
         Term valueInState1,
         SymbolicExecutionState state2,
         Term valueInState2,
         Services services) {
      
      final TermBuilder tb = services.getTermBuilder();
      
      Function newSkolemConst = JoinRuleUtils.getNewSkolemConstantForPrefix(
            v.name().toString(), v.sort(), services);
      ImmutableSet<Name> newNames = DefaultImmutableSet.nil();
      newNames = newNames.add(newSkolemConst.name());
      
      ImmutableSet<Term> newConstraints = DefaultImmutableSet.nil();
      newConstraints = newConstraints.union(getIfThenElseConstraints(
            tb.func(newSkolemConst),
            valueInState1,
            valueInState2,
            state1,
            state2,
            services
      ));
      
      return new Triple<ImmutableSet<Term>, Term, ImmutableSet<Name>>(newConstraints, tb.func(newSkolemConst), newNames);
      
   }
   
    @Override
    public boolean requiresDistinguishablePathConditions() {
        return true;
    }
   
   /**
    * Returns a list of if-then-else constraints for the given constrained
    * term, states and if/else terms.
    * 
    * @param constrained The constrained term.
    * @param ifTerm The value for the if case.
    * @param elseTerm The value for the else case.
    * @param state1 First SE state ("if").
    * @param state2 Second SE state ("else").
    * @param services The services object.
    * @return A list of if-then-else constraints for the given constrained
    *    term, states and if/else terms.
    */
   private static ImmutableSet<Term> getIfThenElseConstraints(
         Term constrained,
         Term ifTerm,
         Term elseTerm,
         SymbolicExecutionState state1,
         SymbolicExecutionState state2,
         Services services) {
      
      TermBuilder tb = services.getTermBuilder();
      ImmutableSet<Term> result = DefaultImmutableSet.nil();
      
      Quadruple<Term, Term, Term, Boolean> distFormAndRightSidesForITEUpd =
            JoinIfThenElse.createDistFormAndRightSidesForITEUpd(state1, state2, ifTerm, elseTerm, services);
      
      Term cond         = distFormAndRightSidesForITEUpd.first;
      Term ifForm       = distFormAndRightSidesForITEUpd.second;
      Term elseForm     = distFormAndRightSidesForITEUpd.third;
      boolean isSwapped = distFormAndRightSidesForITEUpd.fourth;
      
      Term varEqualsIfForm   = tb.equals(constrained, ifForm);
      Term varEqualsElseForm = tb.equals(constrained, elseForm);
      
      if (!(ifTerm.equals(constrained) && !isSwapped ||
            elseTerm.equals(constrained) && isSwapped)) {
          result = result.add(tb.imp(cond, varEqualsIfForm));
      }
      
      if (!(elseTerm.equals(constrained) && !isSwapped ||
            ifTerm.equals(constrained) && isSwapped)) {
         result = result.add(tb.or (cond, varEqualsElseForm));
      }
      
      return result;
      
   }
   
   @Override
   public String toString() {
      return DISPLAY_NAME;
   }
}