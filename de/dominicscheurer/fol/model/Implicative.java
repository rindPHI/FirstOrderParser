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

public class Implicative implements Formula {
    private Formula premise = null, conclusion = null;

    public Implicative(Formula premise, Formula conclusion) {
        this.premise = premise;
        this.conclusion = conclusion;
    }

    public Formula getPremise() {
        return premise;
    }

    public void setPremise(Formula premise) {
        this.premise = premise;
    }

    public Formula getConclusion() {
        return conclusion;
    }

    public void setConclusion(Formula conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public void substitute(Term term, Term forVar) {
        premise.substitute(term, forVar);
        conclusion.substitute(term, forVar);
    }

    @Override
    public Set<Term> freeVars() {
        Set<Term> freeVars = premise.freeVars();
        freeVars.addAll(conclusion.freeVars());
        
        return freeVars;
    }
    
    @Override
    public String toString() {
        return "(" + premise.toString() + " -> " + conclusion.toString() + ")";
    }
}
