package caffeineScript.semantics

import caffeineScript.ir._
import scala.collection.mutable._

/**
 * @author Zoab
 */
package object Transformer {
  
  var recipes: List[Recipe] = Nil
  
  def transform(program: Program): List[RegularInstruction] = {
    recipes = program.header
    transformInstructions(program.body)
  }
  
  def transformInstructions(instructions: List[Instruction]): List[RegularInstruction] = {
    var result: MutableList[RegularInstruction] = new MutableList[RegularInstruction]()
    instructions.foreach { 
      instr => 
        instr match {
          // if it's a regular instruction, just add it to the result
          case reg: RegularInstruction => result += reg
          // iterate over the current result and remove every instruction whose ingredient 
          // is equal to the remove instruction's ingredient
          case rem: RemoveInstruction => result = result.flatMap { x => if (x.ingredient.equals(rem.ingredient)) Nil else List{x}}
          case make: MakeInstruction => {
            recipes.foreach { x => if (x.name.equals(make.recipeName)) result = result ++ transformInstructions(x.body)
              else throw new IllegalArgumentException("Make instruction references recipe: " + make.recipeName + " which is not defined!") }
          }
          // iterate over the current result and transform each instruction whose ingredient is equal to the swap
          // instruction's thing1 to an instruction whose ingredient is equal to the swap instruction's thing2
          case swap: SubstInstruction => result = result.map { x => if (x.ingredient.equals(swap.toRemove)) 
            RegularInstruction(swap.toAdd , x.quantity , x.verb) else x}
          }
        }
    result.toList
  }
}