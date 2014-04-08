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

package de.dominicscheurer.fol.test;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.dominicscheurer.fol.model.Formula;
import de.dominicscheurer.fol.model.Term;
import de.dominicscheurer.fol.parser.FOLParser;
import de.dominicscheurer.fol.parser.ParseException;

public class FOLParserTest {
    
    private void testFormulaParsing(String form) {
        try {
            Formula formula = FOLParser.parse(form);
            assertEquals(form, formula.toString());
        } catch (ParseException e) {
            fail(e.toString());
        }
    }
    
    private void testTermParsing(String term) {
        try {
            FOLParser parser = new FOLParser(new StringReader(term));
            assertEquals(term, parser.term().toString());
        } catch (ParseException e) {
            fail(e.toString());
        }
    }
    
    @Test
    public void testTermParsing() {
        testTermParsing("c");        
        testTermParsing("f(X,Y)");        
        testTermParsing("f(X,f(c))");
    }
    
    @Test
    public void testAtomicParsing() {
        testFormulaParsing("q");
        testFormulaParsing("p(X,c)");
        testFormulaParsing("p(X,f(g(c,d),Y))");
    }
    
    @Test
    public void testNegatedParsing() {
        testFormulaParsing("!p(X,Y)");
    }
    
    @Test
    public void testConjunctionParsing() {
        testFormulaParsing("(p(X,c) & q(c))");        
        testFormulaParsing("(p(X,c) & q)");        
        testFormulaParsing("((p(X,c) & q) & r(c))");
    }
    
    @Test
    public void testDisjunctionParsing() {
        testFormulaParsing("(p(X,Y) | p(Y,f(X,Y)))");
    }
    
    @Test
    public void testImpParsing() {
        testFormulaParsing("(p(X,Y) -> p(Y,f(X,Y)))");
    }
    
    @Test
    public void testComplexParsing() {
        testFormulaParsing("forall X. (p(X,Y) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))");
    }
    
    @Test
    public void testComplexFreeVars() throws ParseException {
        Formula formula = 
                FOLParser.parse("forall X. (p(X,Y) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))");
        
        HashSet<Term> expected = new HashSet<Term>();
        expected.add(new Term("Y"));
        
        Set<Term> actual = formula.freeVars();
        
        assertEquals("Different number of free variables",
                expected.size(),
                actual.size());
        
        for (Term var : expected){
            assertTrue("Expected variable not contained in actual free vars set",
                    actual.contains(var));
        }
    }
    
    @Test
    public void testComplexSubstitute() throws ParseException {
        Formula formula = 
                FOLParser.parse("forall X. (p(X,Y) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))");        
        formula.substitute(new Term("d"), new Term("Y"));        
        assertEquals("forall X. (p(X,d) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))", formula.toString());
        
        formula = 
                FOLParser.parse("forall X. (p(X,f(g(Y))) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))");        
        formula.substitute(new Term("d"), new Term("Y"));        
        assertEquals("forall X. (p(X,f(g(d))) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))", formula.toString());
    }
}
