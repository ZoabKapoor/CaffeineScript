package caffeineScript.ir

/**
 * @author Zoab
 */


sealed abstract class AST

case class Instruction(ingredient: Ingredient, quantity: Quantity, verb: Verb) extends AST

case class Ingredient(name: String)

case class Quantity(typename: String, amount: Int)

case class Verb(name: String)
