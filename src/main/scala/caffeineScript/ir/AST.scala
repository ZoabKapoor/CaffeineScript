package caffeineScript.ir

/**
 * @author Zoab
 */


sealed abstract class AST

case class Program(header: List[Recipe], body: List[Instruction]) extends AST

case class Recipe(name: String, body: List[Instruction]) extends AST

case class Instruction(ingredient: Ingredient, quantity: Quantity, verb: Verb) extends AST

case class Ingredient(name: String)

case class Quantity(typename: String, amount: Double)

case class Verb(name: String)
