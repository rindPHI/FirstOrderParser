/* Generated By:JavaCC: Do not edit this line. FOLParser.java */
package de.dominicscheurer.fol.parser;

import java.io.StringReader;
import java.util.ArrayList;

import de.dominicscheurer.fol.model.*;

/**
 * <p>
 * Parser for formulas of the language of first-order (predicate)
 * logic. The suggested method to start with is {@link #parse(String)}.
 * </p>
 * 
 * <p>
 * Syntax: Variables start with upper letters, functions and predicates
 * with lower letters. Digits and underscores in identifiers are allowed.
 * The binary operators "&", "|", "->" must be put in parentheses; parentheses
 * in other places are not allowed. The negation operator is "!", the
 * universal quantifier is "forall", the existential quantifier is "exists".
 * Whitespace is ignored.
 * </p>
 * 
 * <p>
 * <pre>
 * term  ::= var | fun | fun terms
 * terms ::= "(" term ")" | "(" term "," terms ")"
 * form  ::=   pred
 *           | pred terms
 *           | "!" form
 *           | "(" form "&" form ")"
 *           | "(" form "|" form ")"
 *           | "(" form "->" form ")"
 *           | "exists " var "." form
 *           | "forall " var "." form
 *            
 * </pre>
 * </p>
 * 
 * @author Dominic Scheurer
 */
public class FOLParser implements FOLParserConstants {
    /**
     * Parses a given formula.
     * 
     * @param formula Formula to parse.
     * @return Parsed formula object.
     * @throws ParseException if an error occurs during parsing.
     */
    public static Formula parse(String formula) throws ParseException
    {
        FOLParser parser =
                        new FOLParser(new StringReader(formula));
        return parser.parse();
    }

  final public Formula parse() throws ParseException {
    Formula form;
    form = formula();
    jj_consume_token(0);
        {if (true) return form;}
    throw new Error("Missing return statement in function");
  }

  final public Formula formula() throws ParseException {
    Formula form;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      form = atomic();
      break;
    case NEG:
      form = negated();
      break;
    case EXIST:
      form = existential();
      break;
    case ALL:
      form = universal();
      break;
    case OPEN:
      form = binary();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return form;}
    throw new Error("Missing return statement in function");
  }

  final public Existential existential() throws ParseException {
    Formula inner;
    Variable qfdVar;
    jj_consume_token(EXIST);
    jj_consume_token(VARIABLE);
            qfdVar = new Variable(token.image);
    jj_consume_token(DOT);
    inner = formula();
            {if (true) return new Existential(inner, qfdVar);}
    throw new Error("Missing return statement in function");
  }

  final public Universal universal() throws ParseException {
    Formula inner;
    Variable qfdVar;
    jj_consume_token(ALL);
    jj_consume_token(VARIABLE);
            qfdVar = new Variable(token.image);
    jj_consume_token(DOT);
    inner = formula();
            {if (true) return new Universal(inner, qfdVar);}
    throw new Error("Missing return statement in function");
  }

  final public Negated negated() throws ParseException {
    Formula inner;
    jj_consume_token(NEG);
    inner = formula();
            {if (true) return new Negated(inner);}
    throw new Error("Missing return statement in function");
  }

  final public Formula binary() throws ParseException {
    Formula innerA, innerB;
    jj_consume_token(OPEN);
    innerA = formula();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IMP:
      jj_consume_token(IMP);
      innerB = formula();
      jj_consume_token(CLOSE);
                    {if (true) return new Implicative(innerA, innerB);}
      break;
    case DISJ:
      jj_consume_token(DISJ);
      innerB = formula();
      jj_consume_token(CLOSE);
                    {if (true) return new Disjunctive(innerA, innerB);}
      break;
    case CONJ:
      jj_consume_token(CONJ);
      innerB = formula();
      jj_consume_token(CLOSE);
                    {if (true) return new Conjunctive(innerA, innerB);}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Atomic atomic() throws ParseException {
    Predicate p;
    ArrayList<Term> arguments;
    jj_consume_token(ID);
            p = new Predicate(token.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPEN:
      arguments = tuple();
                    {if (true) return new Atomic(p, arguments);}
      break;
    default:
      jj_la1[2] = jj_gen;
                    {if (true) return new Atomic(p);}
    }
    throw new Error("Missing return statement in function");
  }

  final public Term term() throws ParseException {
    String function;
    ArrayList<Term> arguments = new ArrayList<Term>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VARIABLE:
      jj_consume_token(VARIABLE);
        {if (true) return new Variable(token.image);}
      break;
    case ID:
      jj_consume_token(ID);
        function = token.image;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPEN:
        arguments = tuple();
        break;
      default:
        jj_la1[3] = jj_gen;
        ;
      }
        {if (true) return new Term(function, arguments);}
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ArrayList<Term> tuple() throws ParseException {
    ArrayList<Term> tuple = new ArrayList<Term>();
    Term t;
    jj_consume_token(OPEN);
    t = term();
        tuple.add(t);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_1;
      }
      jj_consume_token(COMMA);
      t = term();
            tuple.add(t);
    }
    jj_consume_token(CLOSE);
                {if (true) return tuple;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public FOLParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[6];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2c440,0x3800,0x40,0x40,0x30000,0x100,};
   }

  /** Constructor with InputStream. */
  public FOLParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public FOLParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new FOLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public FOLParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new FOLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public FOLParser(FOLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(FOLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 6; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
