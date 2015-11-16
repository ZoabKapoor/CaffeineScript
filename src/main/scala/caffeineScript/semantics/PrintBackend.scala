package caffeineScript.semantics

import caffeineScript.ir._

/**
 * @author Zoab
 */
package object PrintBackend {
  def execute(program: List[Instruction]) = {program.foreach { x => executeInstruction(x)}}
  
  def executeInstruction(instr: Instruction) = {
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
  
  // could modularize this into calls processing each part of the AST?
  // traits for printing stuff, 
  
  
  // Possible other backend: map from ingredient to quantities, which can be queried for "how much ingredient do
  // we have"? and updating for a certain ingredient.
}