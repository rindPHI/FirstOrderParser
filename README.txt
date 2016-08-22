	Copyright 2014 Dominic Scheurer

	This file is part of FirstOrderParser.

	FirstOrderParser is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	FirstOrderParser is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with FirstOrderParser.  If not, see <http://www.gnu.org/licenses/>.

######################################
#          FirstOrderParser          #
# Copyright 2014 by Dominic Scheurer #
######################################

FirstOrderParser is a parser for first-order (predicate) logic written
in JavaCC. The shipped model classes feature the basic operations of
substitution and free variables computation.

--------------------------------------

For the JavaCC grammar file, see de/dominicscheurer/fol/parser/FOLGrammar.jj

--------------------------------------

Allowed syntax:

term  ::= var | fun | fun terms
terms ::= "(" term ")" | "(" term "," terms ")"
form  ::=   pred
          | pred terms
          | "!" form
          | "(" form "&" form ")"
          | "(" form "|" form ")"
          | "(" form "->" form ")"
          | "exists " var "." form
          | "forall " var "." form

where variables (var) start with upper case letters, and predicates (pred) and
functions (fun) start with lower case letters. Digits and underscores are allowed,
superfluous whitespace is ignored.

--------------------------------------

The main class is de.dominicscheurer.fol.parser.FOLParser. Example usage:

Formula formula = FOLParser.parse(
	"forall X. (p(X,Y) & exists Y. (p(Y,f(X,Y)) -> (q(c) | !r)))"
);
formula.substitute(new Term("d"), new Term("Y"));

--------------------------------------

JUnit test cases are contained in the class de.dominicscheurer.fol.test.FOLParserTest
