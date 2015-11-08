package caffeineScript.main

import caffeineScript.parser._
import caffeineScript.semantics.PrintBackend

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

				case CaffeineScriptParser.Success(instructions, _) => {
					// error_check(ruleBuilders)

					PrintBackend.execute(instructions)
				}
		}
  }
}