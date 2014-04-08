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
import java.util.HashSet;
import java.util.Set;

public class Atomic implements Formula {
    private Predicate predicate = null;
    private ArrayList<Term> terms = null;
    
    public Atomic(Predicate predicate) {
        this.predicate = predicate;
        this.terms = new ArrayList<Term>();
    }
    
    public Atomic(Predicate predicate, ArrayList<Term> terms) {
        this.predicate = predicate;
        this.terms = terms;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }
    
    public int arity() {
        return terms.size();
    }
    
    public boolean isPropositional() {
        return arity() == 0;
    }

    @Override
    public void substitute(Term term, Term forVar) {
        for (int i = 0; i < terms.size(); i++) {
            terms.get(i).substitute(term, forVar);
        }
    }

    @Override
    public Set<Term> freeVars() {
        HashSet<Term> freeVars = new HashSet<Term>();
        for (Term term : terms) {
            if (term.isVariable()) {
                freeVars.add(term);
            }
        }
        return freeVars;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(predicate.toString());
        
        if (!isPropositional()) {
            result.append("(");
            
            int i;
            for (i = 0; i < terms.size() - 1; i++) {
                result.append(terms.get(i).toString());
                result.append(",");
            }
            result.append(terms.get(i).toString());
            
            result.append(")");
        }
        
        return result.toString();
    }
}
