/*
 *   Project: Speedith.Core
 * 
 * File name: RuleApplication.java
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
package speedith.core.reasoning;

import static speedith.core.i18n.Translations.i18n;
import speedith.core.reasoning.args.RuleArg;

/**
 * A pair of two elements:
 *  <ul>
 *      <li>the applied {@link InferenceRule inference rule}, and</li>
 *      <li>{@link RuleArg arguments} given to the former.</li>
 *  </ul>
 * <p>Instances of this class (and its derived classes) are immutable.</p>
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class RuleApplication {
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private InferenceRule<?> inferenceRule;
    private RuleArg ruleArguments;
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Initialises an instance of this class with the given inference rule and
     * arguments to the inference rule.
     * @param inferenceRule the inference rule (that can be or was applied to
     * some set of spider diagrams with the given additional arguments).
     * @param ruleArguments the additional arguments to the inference rule.
     */
    public RuleApplication(InferenceRule<?> inferenceRule, RuleArg ruleArguments) {
        if (inferenceRule == null) {
            throw new IllegalArgumentException(i18n("GERR_NULL_ARGUMENT", "inferenceRule"));
        }
        this.inferenceRule = inferenceRule;
        this.ruleArguments = ruleArguments;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public Properties">
    /**
     * Returns the inference rule stored in this object.
     * <p>If you want to apply this rule with the accompanying {@link
     * RuleApplication#getRuleArguments() arguments} to a list of
     * spider diagrams, you can use the {@link RuleApplication#applyTo(speedith.core.reasoning.Goals)}
     * method.</p>
     * @return the inference rule stored in this object.
     */
    public InferenceRule<?> getInferenceRule() {
        return inferenceRule;
    }
    
    /**
     * Returns the arguments that will be passed to the {@link RuleApplication#getInferenceRule()
     * inference rule} when calling the {@link RuleApplication#applyTo(speedith.core.reasoning.Goals)}
     * method.
     * @return the arguments that will be passed to the {@link RuleApplication#getInferenceRule()
     * inference rule} when calling the {@link RuleApplication#applyTo(speedith.core.reasoning.Goals)}
     * method.
     */
    public RuleArg getRuleArguments() {
        return ruleArguments;
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Rule Application">
    /**
     * Applies the {@link RuleApplication#getInferenceRule() inference
     * rule} with the current {@link RuleApplication#getRuleArguments()
     * arguments} to the given list of spider diagrams (goals).
     * @param goals a list of spider diagrams on which to apply the rule.
     * @return a modified list of spider diagrams.
     * @throws RuleApplicationException thrown if the rule application failed
     * for any reason.
     */
    public RuleApplicationResult applyTo(Goals goals) throws RuleApplicationException {
        return inferenceRule.apply(ruleArguments, goals);
    }
    // </editor-fold>
}
