package de.uka.ilkd.key.informationflow.po.snippet;

import java.util.Set;

import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.logic.op.QuantifiableVariable;
import de.uka.ilkd.key.proof.init.ProofObligationVars;


/**
 * @author christoph
 */
class SelfcomposedExecutionSnippet extends ReplaceAndRegisterMethod
        implements InfFlowFactoryMethod {

    @Override
    public Term produce(BasicSnippetData d, ProofObligationVars poVars1,
            ProofObligationVars poVars2) {
        BasicPOSnippetFactory f1 = POSnippetFactory.getBasicFactory(d, poVars1);
        BasicPOSnippetFactory f2 = POSnippetFactory.getBasicFactory(d, poVars2);

        final Term exec1 = f1.create(BasicPOSnippetFactory.Snippet.SYMBOLIC_EXEC_WITH_PRE);
        final Set<QuantifiableVariable> qvsToReplace = collectQuantifiableVariables(exec1);
        final Term updatedExec1 =
            d.tb.apply(d.tb.elementary(d.tb.getBaseHeap(), poVars1.pre.heap), exec1);
        final Term exec2 = replaceQuantifiableVariables(
            f2.create(BasicPOSnippetFactory.Snippet.SYMBOLIC_EXEC_WITH_PRE), qvsToReplace,
            d.services);
        final Term updatedExec2 =
            d.tb.apply(d.tb.elementary(d.tb.getBaseHeap(), poVars2.pre.heap), exec2);

        return d.tb.and(updatedExec1, updatedExec2);
    }
}
