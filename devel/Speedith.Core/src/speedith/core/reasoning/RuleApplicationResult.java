/*
 *   Project: Speedith.Core
 * 
 * File name: RuleApplicationResult.java
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

/**
 * Contains the result data of an application of an inference rule (see {@link
 * InferenceRule#apply(speedith.core.reasoning.args.RuleArg, speedith.core.reasoning.Goals)
 * the apply method}).
 * <p>Instances of this class (and its derived classes) are immutable.</p>
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class RuleApplicationResult {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private Goals goals;
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Initialises an instance of the RuleApplicationResult class.
     * @param goals the goals that remained after the application of an
     * inference rule.
     */
    public RuleApplicationResult(Goals goals) {
        this.goals = goals;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public Properties">
    /**
     * Returns the goals that have been the result of an {@link InferenceRule#apply(speedith.core.reasoning.args.RuleArg, speedith.core.reasoning.Goals) 
     * inference rule application}.
     * <p>May return {@code null}, which indicates that the application of the
     * inference rule yielded no subgoals. The same hold for an empty {@link Goals}
     * instance.</p>
     * @return the goals that have been the result of an {@link InferenceRule#apply(speedith.core.reasoning.args.RuleArg, speedith.core.reasoning.Goals)  
     * inference rule application}.
     * <p>May return {@code null}, which indicates that the application of the
     * inference rule yielded no subgoals. The same hold for an empty {@link Goals}
     * instance.</p>
     */
    public Goals getGoals() {
        return goals;
    }
    //</editor-fold>
}
