package caffeineScript.semantics

import caffeineScript.ir._

/**
 * @author Zoab
 */
package object PrintBackend {
  def execute(program: List[Instruction]) = {program.foreach { x => executeInstruction(x)}}
  
  def executeInstruction(instr: Instruction) = {
    print(instr.verb.name + "ing " + instr.quantity.amount + " " + instr.quantity.typename + " of " + instr.ingredient.name + "\n")
  }
  
  // could modularize this into calls processing each part of the AST?
  // traits for printing stuff, 
  
  
  // Possible backend: map from ingredient to quantities, which can be queried for "how much ingredient do
  // we have"? and updating for a certain ingredient.
}