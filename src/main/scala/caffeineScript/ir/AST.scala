package caffeineScript.ir

/**
 * @author Zoab
 */



case class Program(header: List[Recipe], body: List[Instruction]) 

case class Recipe(name: String, body: List[Instruction]) 

sealed abstract class Instruction

case class RegularInstruction(ingredient: Ingredient, quantity: Quantity, verb: Verb) extends Instruction

case class RemoveInstruction(ingredient: Ingredient) extends Instruction

case class SwapInstruction(thing1: Ingredient, thing2: Ingredient) extends Instruction

case class MakeInstruction(recipeName: String) extends Instruction

case class Ingredient(name: String)

case class Quantity(typename: String, amount: Double)

case class Verb(name: String)
