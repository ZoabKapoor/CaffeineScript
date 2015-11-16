package caffeineScript.ir

/**
 * @author Zoab
 */


sealed abstract class AST

case class Instruction(ingredient: Ingredient, quantity: Quantity, verb: Verb) extends AST

case class Ingredient(name: String)

case class Quantity(typename: String, amount: Double)
// should there be a standard measurement for volume/mass?
// do the benefits of the canonical representation outweigh the drawbacks?

case class Verb(name: String)
