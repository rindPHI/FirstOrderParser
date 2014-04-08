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

public class Universal implements Formula {
    private Formula subformula = null;
    private Variable quantifiedVar = null;

    public Universal(Formula subformula, Variable quantifiedVar) {
        this.subformula = subformula;
        this.quantifiedVar = quantifiedVar;
    }

    public Formula getSubformula() {
        return subformula;
    }

    public void setSubformula(Formula subformula) {
        this.subformula = subformula;
    }

    public Variable getQuantifiedVar() {
        return quantifiedVar;
    }

    public void setQuantifiedVar(Variable quantifiedVar) {
        this.quantifiedVar = quantifiedVar;
    }

    @Override
    public void substitute(Term term, Variable forVar) {
        if (!forVar.equals(quantifiedVar)) {
            subformula.substitute(term, forVar);
        }
    }

    @Override
    public Set<Variable> freeVars() {
        Set<Variable> freeVars = subformula.freeVars();
        freeVars.remove(quantifiedVar);
        return freeVars;
    }
    
    @Override
    public String toString() {
        return "forall " + quantifiedVar.toString() + ". " + subformula.toString();
    }
}