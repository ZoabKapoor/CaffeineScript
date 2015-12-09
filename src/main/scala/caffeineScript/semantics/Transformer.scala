package caffeineScript.semantics

import caffeineScript.ir._
import scala.collection.mutable._

/**
 * @author Zoab
 */
package object Transformer {
  
  val recipes: HashMap[String, List[Instruction]] = new HashMap[String,List[Instruction]]()
  
  def transform(program: Program): List[RegularInstruction] = {
    program.header.foreach { x => if (!recipes.contains(x.name)) recipes.put(x.name, x.body) 
      else throw new IllegalArgumentException("Recipe with name: " + x.name + " is defined multiple times!") }
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
          case rem: RemoveInstruction => result = result.filter { x => !(x.ingredient.equals(rem.ingredient))}
          case make: MakeInstruction => { if (recipes.contains(make.recipeName)) 
        	  result = result ++ transformInstructions(recipes.getOrElse(make.recipeName, null))
        	  else throw new IllegalArgumentException("Recipe with name: " + make.recipeName + " is not defined!")
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