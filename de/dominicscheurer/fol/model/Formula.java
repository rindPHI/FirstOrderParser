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

/**
 * Basic interface for formulas, comprising methods for
 * substitution and computation of free variables.
 * 
 * @author Dominic Scheurer
 */
public interface Formula {
    /**
     * Substitutes the given term for the given variable
     * in the formula. Only free occurrences of the variable
     * are considered.
     * 
     * @param term Term to substitute forVar with.
     * @param forVar Variable to substitute with term.
     */
    public void substitute(Term term, Variable forVar);
    
    /**
     * @return The free variables of the formula.
     */
    public Set<Variable> freeVars();
}
