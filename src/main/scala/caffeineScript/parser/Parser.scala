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
			 (verbWord ^^ {case x => Verb(x)})

			lazy val verbWord: PackratParser[String] =
			( "pour" | "add" | "sprinkle" | "scoop")

			lazy val quantity: PackratParser[Quantity] = 
			(amount~typename ^^ {case amt~qtyname => Quantity(qtyname, amt)})

			lazy val typename: PackratParser[String] = 
			("shots" | "oz" | "spoons" | "grams")

			lazy val ingredient: PackratParser[Ingredient] =
			(name ^^ {case x => Ingredient(x)})

			lazy val name: PackratParser[String] = 
			("espresso" | "milk" | "sugar" | "cinnamon")

			lazy val amount: PackratParser[Int] =
			(wholeNumber ^^ {x => x.toInt})
}

// add 2 shots of coffee