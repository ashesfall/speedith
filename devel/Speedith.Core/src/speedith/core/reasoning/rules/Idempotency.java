/*
 *   Project: Speedith.Core
 * 
 * File name: Idempotency.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2011 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package speedith.core.reasoning.rules;

import java.util.ArrayList;
import java.util.Locale;
import static speedith.core.i18n.Translations.*;
import speedith.core.lang.CompoundSpiderDiagram;
import speedith.core.lang.IdTransformer;
import speedith.core.lang.NullSpiderDiagram;
import speedith.core.lang.Operator;
import speedith.core.lang.SpiderDiagram;
import speedith.core.lang.SpiderDiagrams;
import speedith.core.lang.TransformationException;
import speedith.core.reasoning.*;
import speedith.core.reasoning.args.RuleArg;
import speedith.core.reasoning.args.SubDiagramIndexArg;
import speedith.core.reasoning.rules.instructions.SelectSingleOperatorInstruction;

/**
 * The implementation of the idempotency inference rule.
 * <p>This inference rule checks whether a {@link CompoundSpiderDiagram binary
 * compound spider diagram} can be reduced to either one of its operands (in case
 * of the {@link Operator#Conjunction and} or {@link Operator#Disjunction or}
 * operators), or to the {@link NullSpiderDiagram null diagram} (in case of the
 * {@link Operator#Implication implies} or {@link Operator#Equivalence
 * equivalence} operators).</p>
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class Idempotency extends SimpleInferenceRule<SubDiagramIndexArg> implements BasicInferenceRule<SubDiagramIndexArg>, ForwardRule<SubDiagramIndexArg>, BackwardRule<SubDiagramIndexArg> {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The name of this inference rule.
     * <p>This value is returned by the {@link Idempotency#getInferenceRuleName()}
     * method.</p>
     */
    public static final String InferenceRuleName = "idempotency";
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="InferenceRule Implementation">
    public RuleApplicationResult apply(final RuleArg args, Goals goals) throws RuleApplicationException {
        SubDiagramIndexArg arg = getTypedRuleArgs(args);
        SpiderDiagram[] newSubgoals = goals.getGoals().toArray(new SpiderDiagram[goals.getGoalsCount()]);
        newSubgoals[arg.getSubgoalIndex()] = getSubgoal(arg, goals).transform(new IdempotencyTransformer(arg), false);
        return new RuleApplicationResult(Goals.createGoalsFrom(newSubgoals));
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="InferenceRuleProvider Implementation">
    public Idempotency getInferenceRule() {
        return this;
    }

    public String getInferenceRuleName() {
        return InferenceRuleName;
    }

    public String getDescription(Locale locale) {
        return i18n(locale, "IDEMPOTENCY_DESCRIPTION");
    }

    public String getPrettyName(Locale locale) {
        return i18n(locale, "IDEMPOTENCY_PRETTY_NAME");
    }

    public Class<SubDiagramIndexArg> getArgumentType() {
        return SubDiagramIndexArg.class;
    }

    public RuleApplicationInstruction<SubDiagramIndexArg> getInstructions() {
        // This rule needs a subdiagram. In fact, it needs an OR or AND operator
        return SingletonContainer.Instruction;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Forward Rule">
    @Override
    public RuleApplicationResult applyForwards(RuleArg args, Goals goals) throws RuleApplicationException {
        return apply(args, goals);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Backward Rule">
    @Override
    public RuleApplicationResult applyBackwards(RuleArg args, Goals goals) throws RuleApplicationException {
        return apply(args, goals);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Helper Classes">
    private static final class SingletonContainer {
        private static final SelectSingleOperatorInstruction Instruction = new SelectSingleOperatorInstruction(Operator.Conjunction, Operator.Disjunction);
    }
    
    private class IdempotencyTransformer extends IdTransformer {

        private final SubDiagramIndexArg arg;

        public IdempotencyTransformer(SubDiagramIndexArg arg) {
            this.arg = arg;
        }

        @Override
        public SpiderDiagram transform(CompoundSpiderDiagram csd, int diagramIndex, ArrayList<CompoundSpiderDiagram> parents, ArrayList<Integer> childIndices) {
            // Transform only the target diagram
            if (diagramIndex == arg.getSubDiagramIndex()) {
                // Is the compound diagram a conjunction or a disjunction?
                // Is it an implication or an equivalence?
                if (Operator.Conjunction.equals(csd.getOperator()) || Operator.Disjunction.equals(csd.getOperator())) {
                    if (csd.getOperand(0).isSEquivalentTo(csd.getOperand(1))) {
                        return csd.getOperand(1);
                    }
                } else if (Operator.Equivalence.equals(csd.getOperator()) || Operator.Implication.equals(csd.getOperator())) {
                    if (csd.getOperand(0).isSEquivalentTo(csd.getOperand(1))) {
                        return SpiderDiagrams.createNullSD();
                    }
                }
                throw new TransformationException(i18n("RULE_IDEMPOTENCY_NOT_APPLICABLE"));
            }
            return null;
        }
    }
    //</editor-fold>
}
