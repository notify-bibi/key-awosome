package de.uka.ilkd.key.strategy.termProjection;

import java.math.BigInteger;

import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.logic.PosInOccurrence;
import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.logic.op.AbstractTermTransformer;
import de.uka.ilkd.key.logic.op.Function;
import de.uka.ilkd.key.proof.Goal;
import de.uka.ilkd.key.rule.RuleApp;
import de.uka.ilkd.key.rule.metaconstruct.arith.Monomial;

public abstract class AbstractDividePolynomialsProjection implements ProjectionToTerm {

    private final ProjectionToTerm leftCoefficient, polynomial;

    protected AbstractDividePolynomialsProjection(ProjectionToTerm leftCoefficient,
            ProjectionToTerm polynomial) {
        this.leftCoefficient = leftCoefficient;
        this.polynomial = polynomial;
    }

    public Term toTerm(RuleApp app, PosInOccurrence pos, Goal goal) {
        final Term coeffT = leftCoefficient.toTerm(app, pos, goal);
        final Term polyT = polynomial.toTerm(app, pos, goal);

        final Services services = goal.proof().getServices();
        final BigInteger coeff =
            new BigInteger(AbstractTermTransformer.convertToDecimalString(coeffT, services));

        return quotient(coeff, polyT, services);
    }

    protected abstract Term divide(Monomial numerator, BigInteger denominator, Services services);

    private Term quotient(BigInteger monoCoeff, Term rightPoly, Services services) {
        final Function add = services.getTypeConverter().getIntegerLDT().getAdd();
        if (rightPoly.op() == add) {
            final Term left = quotient(monoCoeff, rightPoly.sub(0), services);
            final Term right = quotient(monoCoeff, rightPoly.sub(1), services);
            return services.getTermBuilder().func(add, left, right);
        }

        final Monomial rightMono = Monomial.create(rightPoly, services);
        return divide(rightMono, monoCoeff, services);
    }

}
