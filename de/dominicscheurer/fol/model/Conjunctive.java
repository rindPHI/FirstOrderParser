/* Copyright 2014 Dominic Scheurer
 *
 * This file is part of FirstOrderParser.
 * 
 * FirstOrderParser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FirstOrderParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with FirstOrderParser.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.dominicscheurer.fol.model;

import java.util.Set;

public class Conjunctive implements Formula {
    private Formula subformulaA = null, subformulaB = null;

    public Conjunctive(Formula subformulaA, Formula subformulaB) {
        this.subformulaA = subformulaA;
        this.subformulaB = subformulaB;
    }

    public Formula getSubformulaA() {
        return subformulaA;
    }

    public void setSubformulaA(Formula subformulaA) {
        this.subformulaA = subformulaA;
    }

    public Formula getSubformulaB() {
        return subformulaB;
    }

    public void setSubformulaB(Formula subformulaB) {
        this.subformulaB = subformulaB;
    }

    @Override
    public void substitute(Term term, Term forVar) {
        subformulaA.substitute(term, forVar);
        subformulaB.substitute(term, forVar);
    }

    @Override
    public Set<Term> freeVars() {
        Set<Term> freeVars = subformulaA.freeVars();
        freeVars.addAll(subformulaB.freeVars());
        
        return freeVars;
    }
    
    @Override
    public String toString() {
        return "(" + subformulaA.toString() + " & " + subformulaB.toString() + ")";
    }
}
