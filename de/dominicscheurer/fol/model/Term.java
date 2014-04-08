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

import java.util.ArrayList;

public class Term {
    private String function = "";
    private ArrayList<Term> innerTerms = null;
    
    public Term(String function) {
        this.function = function;
        this.innerTerms = new ArrayList<Term>();
    }
    
    public Term(String function, ArrayList<Term> innerTerms) {
        this.function = function;
        this.innerTerms = innerTerms;
    }
    
    public String getFunction() {
        return function;
    }
    
    public void setFunction(String function) {
        this.function = function;
    }
    
    public ArrayList<Term> getInnerTerms() {
        return innerTerms;
    }
    
    public void setInnerTerms(ArrayList<Term> innerTerms) {
        this.innerTerms = innerTerms;
    }
    
    public int arity() {
        return innerTerms.size();
    }
    
    public boolean isConstant() {
        return arity() == 0;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(function.toString());
        
        if (!isConstant()) {
            result.append("(");
            
            int i;
            for (i = 0; i < innerTerms.size() - 1; i++) {
                result.append(innerTerms.get(i).toString());
                result.append(",");
            }
            result.append(innerTerms.get(i).toString());
            
            result.append(")");
        }
        
        return result.toString();
    }
}
