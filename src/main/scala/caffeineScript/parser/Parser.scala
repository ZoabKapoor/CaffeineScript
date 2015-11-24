package caffeineScript.parser

import scala.util.parsing.combinator._
import caffeineScript.ir._

/**
 * @author Zoab
 */
object CaffeineScriptParser extends JavaTokenParsers with PackratParsers {
  
  val LINE_TERMINATOR = ";"

	def apply(s: String): ParseResult[Program] = parseAll(program,s)

	lazy val program: Parser[Program] =
	("{"~recipes~"}"~instructions ^^ {case "{"~r~"}"~i => Program(r,i)} |
     instructions ^^ {case i => Program(Nil,i)})

  lazy val recipes: Parser[List[Recipe]] = recipe*
  
	lazy val recipe: Parser[Recipe] = 
	(recipeName~"{"~instructions~"}" ^^ {case name~"{"~body~"}" => Recipe(name,body)})   

	lazy val recipeName: Parser[String] =
	( """([A-Z])+""".r ^^ {case x => x})

  lazy val instructions: Parser[List[Instruction]] = instr*
  
	lazy val instr: PackratParser[Instruction] = 
    (instrBody~LINE_TERMINATOR ^^ {case body~LINE_TERMINATOR => body})
  
  lazy val instrBody: PackratParser[Instruction] =  
	( "make"~recipeName ^^ {case "make"~name => MakeInstruction(name)} |
		"remove"~ingredient ^^ {case "remove"~ingredient => RemoveInstruction(ingredient)} |
    "swap"~ingredient~","~ingredient ^^ {case "swap"~x~","~y => SwapInstruction(x,y)} |
		verb~quantity~"@"~ingredient ^^ {case v~q~"@"~i => RegularInstruction(i, q, v)})       

	lazy val verb: PackratParser[Verb] = 
  // verbs cannot contain multiple words
	("""([a-z])+""".r ^^ {case x => Verb(x)})

	lazy val quantity: PackratParser[Quantity] = 
	(amount~"""\w+""".r ^^ {case amt~qtyname => Quantity(qtyname, amt)})

	lazy val ingredient: PackratParser[Ingredient] =
	// We allow ingredients to contain multiple words
	("""([a-z]| )+""".r ^^ {case x => Ingredient(x)})

	lazy val amount: PackratParser[Double] =
	(floatingPointNumber ^^ {x => x.toDouble})
}