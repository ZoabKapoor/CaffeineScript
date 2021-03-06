package caffeineScript.ir

/**
 * @author Zoab
 */



case class Program(header: List[Recipe], body: List[Instruction]) 

case class Recipe(name: String, body: List[Instruction]) 

sealed abstract class Instruction

case class RegularInstruction(ingredient: Ingredient, quantity: Quantity, verb: Verb) extends Instruction

case class RemoveInstruction(ingredient: Ingredient) extends Instruction

case class SubstInstruction(toAdd: Ingredient, toRemove: Ingredient) extends Instruction

case class MakeInstruction(recipeName: String) extends Instruction

case class Ingredient(name: String) {
  override def equals (o: Any) = o match {
    case that: Ingredient => that.name.equals(this.name)
    case _ => false
  }
}

case class Quantity(typename: String, amount: Double)

case class Verb(name: String)
