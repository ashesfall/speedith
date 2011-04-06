/*
 *   Project: Speedith.Core
 * 
 * File name: NullSpiderDiagram.java
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
package speedith.core.lang;

import static speedith.core.i18n.Translations.i18n;

/**
 * Represents an empty spider diagram (it is a tautology and evaluates to '⊤' or
 * {@code true}).
 * <p>This class is a singleton. To get the only instance of the {@link
 * NullSpiderDiagram} use the {@link NullSpiderDiagram#getInstance()} method.</p>
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class NullSpiderDiagram extends SpiderDiagram {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    /**
     * The identifier of the null spider diagram in the textual representation
     * of spider diagrams.
     * <p>This value is used in the textual representation of spider diagrams
     * (see {@link BinarySpiderDiagram#toString()}).</p>
     */
    public static final String SDTextNullId = "NullSD";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    /**
     * Returns the only instance of the null spider diagram.
     * @return the only instance of the null spider diagram.
     */
    public static NullSpiderDiagram getInstance() {
        return SingletonHolder.Instance;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="The Singleton Holder Class">
    private static class SingletonHolder {
        public static final NullSpiderDiagram Instance = new NullSpiderDiagram();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Constructor">
    private NullSpiderDiagram() {
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Text Conversion Methods">
    @Override
    public void toString(StringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(i18n("GERR_NULL_ARGUMENT", "sb"));
        }
        sb.append(SDTextNullId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }
    // </editor-fold>
}
