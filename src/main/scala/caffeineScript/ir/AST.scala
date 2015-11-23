package caffeineScript.ir

/**
 * @author Zoab
 */


sealed abstract class AST

case class Recipe(name: String, body: List[Instruction]) extends AST

case class Instruction(ingredient: Ingredient, quantity: Quantity, verb: Verb)

case class Ingredient(name: String)

case class Quantity(typename: String, amount: Double)

case class Verb(name: String)

case class Body(contents: List[Instruction]) extends AST
