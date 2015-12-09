package caffeineScript.semantics

import caffeineScript.ir._

/**
 * @author Zoab
 */
package object PrintBackend {
  def execute(program: List[RegularInstruction]) = {program.foreach { x => executeInstruction(x)}}
  
  def executeInstruction(instr: Instruction): Unit = {
    instr match {
      case x: RegularInstruction => executeRegularInstruction(x)
      case default =>
    }
  }
  
  def executeRegularInstruction(instr: RegularInstruction): Unit = {
      printVerb(instr.verb)
      printQty(instr.quantity)
      printIngr(instr.ingredient) 
      printNL()
  }
  
  def printVerb(verb: Verb): Unit = {
    print(verb.name + "ing ")
  }
  
  def printQty(quantity: Quantity): Unit = {
    print(quantity.amount + " " + quantity.typename)
  }
  
  def printIngr(ingredient: Ingredient): Unit = {
    print(" of " + ingredient.name)
  }
  
  def printNL(): Unit = {
    print("\n")
  }
  
}