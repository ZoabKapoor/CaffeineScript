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
}