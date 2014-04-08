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

public class Variable extends Term {

    public Variable(String identifier) {
        super(identifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) {
            return false;
        }
        
        return getFunction().equals(((Variable) obj).getFunction());
    }
    
    @Override
    public String toString() {
        return getFunction();
    }
    
    @Override
    public int hashCode() {
        return getFunction().hashCode();
    }
}
