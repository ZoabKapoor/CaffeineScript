package caffeineScript.main

import caffeineScript.parser._
import caffeineScript.semantics._

/**
 * @author Zoab
 */
object Main {
	def main(args: Array[String]) = {
		if (args.length != 1) {
			println(s"Not the right number of arguments: Got ${args.length}, expected 1")
			sys.exit(1)
		}
		val program = CaffeineScriptParser(io.Source.fromFile(args(0)).mkString)

				program match {
				case e: CaffeineScriptParser.NoSuccess => println(e) 

				case CaffeineScriptParser.Success(result, _) => {
          
          val instructions = Transformer.transform(result);
          
					PrintBackend.execute(instructions)
				}
		}
  }
}
