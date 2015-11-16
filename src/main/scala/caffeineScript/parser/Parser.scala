package caffeineScript.parser

import scala.util.parsing.combinator._
import caffeineScript.ir._

/**
 * @author Zoab
 */
object CaffeineScriptParser extends JavaTokenParsers with PackratParsers {

	def apply(s: String): ParseResult[List[Instruction]] = parseAll(program,s)

			def program: Parser[List[Instruction]] = instr*

			lazy val instr: PackratParser[Instruction] = 
			( verb~quantity~ingredient~";" ^^ {case v~q~i~";" => Instruction(i, q, v)})       

			lazy val verb: PackratParser[Verb] = 
			 (ident ^^ {case x => Verb(x)})

			lazy val quantity: PackratParser[Quantity] = 
			(amount~ident ^^ {case amt~qtyname => Quantity(qtyname, amt)})

			lazy val ingredient: PackratParser[Ingredient] =
			(ident ^^ {case x => Ingredient(x)})

			lazy val amount: PackratParser[Double] =
			(floatingPointNumber ^^ {x => x.toDouble})
}

// add 2 shots of coffee