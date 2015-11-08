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
			( liquidVerb~" "~quantityLiquid~" "~liquidIngredient ^^ {case lv~" "~lq~" "~li => Instruction(li, lq, lv)} |
          solidVerb~" "~quantitySolid~" "~solidIngredient ^^ {case sv~" "~sq~" "~si => Instruction(si,sq,sv)})       

			lazy val liquidVerb: PackratParser[Verb] = 
			 (liquidVerbWord ^^ {case x => Verb(x)})

			lazy val liquidVerbWord: PackratParser[String] =
			( "pour" | "add" )

			lazy val quantityLiquid: PackratParser[Quantity] = 
			(amount~" "~liquidTypename ^^ {case amt~" "~liqtypename => Quantity(liqtypename, amt)})

			lazy val liquidTypename: PackratParser[String] = 
			("shots" | "oz" )

			lazy val liquidIngredient: PackratParser[Ingredient] =
			(liquidName ^^ {case liqname => Ingredient(liqname)})

			lazy val liquidName: PackratParser[String] = 
			("espresso" | "milk" )

			lazy val amount: PackratParser[Int] =
			(wholeNumber ^^ {x => x.toInt})

			lazy val solidVerb: PackratParser[Verb] = 
			(solidVerbWord ^^ {case x => Verb(x)})

			lazy val solidVerbWord: PackratParser[String] =
			( "add" | "sprinkle" | "scoop")

			lazy val quantitySolid: PackratParser[Quantity] = 
			(amount~" "~solidTypename ^^ {case amt~" "~soltypename => Quantity(soltypename, amt)})

			lazy val solidTypename: PackratParser[String] = 
			("spoons" | "grams")

			lazy val solidIngredient: PackratParser[Ingredient] =
			(solidName ^^ {case solname => Ingredient(solname)})

			lazy val solidName: PackratParser[String] = 
			("sugar" | "cinnamon")
}

// add 2 shots of coffee