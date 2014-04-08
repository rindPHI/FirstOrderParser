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

public class Existential implements Formula {
    private Formula subformula = null;
    private Term quantifiedVar = null;

    public Existential(Formula subformula, Term quantifiedVar) {
        this.subformula = subformula;
        this.quantifiedVar = quantifiedVar;
    }

    public Formula getSubformula() {
        return subformula;
    }

    public void setSubformula(Formula subformula) {
        this.subformula = subformula;
    }

    public Term getQuantifiedVar() {
        return quantifiedVar;
    }

    public void setQuantifiedVar(Term quantifiedVar) {
        this.quantifiedVar = quantifiedVar;
    }

    @Override
    public void substitute(Term term, Term forVar) {
        if (!forVar.equals(quantifiedVar)) {
            subformula.substitute(term, forVar);
        }
    }

    @Override
    public Set<Term> freeVars() {
        Set<Term> freeVars = subformula.freeVars();
        freeVars.remove(quantifiedVar);
        return freeVars;
    }
    
    @Override
    public String toString() {
        return "exists " + quantifiedVar.toString() + ". " + subformula.toString();
    }
}
