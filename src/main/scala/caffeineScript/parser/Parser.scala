package caffeineScript.parser

import scala.util.parsing.combinator._
import caffeineScript.ir._

/**
 * @author Zoab
 */
object CaffeineScriptParser extends JavaTokenParsers with PackratParsers {
  
  val LINE_TERMINATOR = ";"

	def apply(s: String): ParseResult[List[Instruction]] = parseAll(program,s)

			def program: Parser[List[Instruction]] = instr*

			lazy val instr: PackratParser[Instruction] = 
			( verb~quantity~"@"~ingredient~LINE_TERMINATOR ^^ {case v~q~"@"~i~LINE_TERMINATOR => Instruction(i, q, v)})       

			lazy val verb: PackratParser[Verb] = 
			 ("""([a-z])+""".r ^^ {case x => Verb(x)})

			lazy val quantity: PackratParser[Quantity] = 
			(amount~"""\w+""".r ^^ {case amt~qtyname => Quantity(qtyname, amt)})

			lazy val ingredient: PackratParser[Ingredient] =
      // We allow ingredients to contain multiple words, but not verbs
			("""([a-z]| )+""".r ^^ {case x => Ingredient(x)})

			lazy val amount: PackratParser[Double] =
			(floatingPointNumber ^^ {x => x.toDouble})
}