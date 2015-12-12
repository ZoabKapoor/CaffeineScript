# CaffeineScript

### Introduction

Welcome to the CaffeineScript programming language; a language to precisely specify your favourite coffee drink so we can make it **exactly** the way you want it!

### How to write a program

A program in CaffeineScript has 2 sections: the (optional) header and the body. Within the header, you can define recipes for coffee drinks which you can reference from the body of the program. In the body, you'll write the instructions to make the final drink that you want.

The basic building block of a program is the instruction. There are 4 different kinds of instructions: regular instructions, swap instructions, make instructions, and remove instructions. A regular instruction is used to add a certain quantity of an ingredient. a make instruction adds all the contents of a recipe that you defined in the header to the current drink. A swap instruction swaps every instance of some ingredient for another ingredient of your choosing. Finally, a remove instruction removes all instances of the ingredient in the recipe so far. It's important to note that swap and remove instructions only modify the ingredients that have already been added to the drink when they are executed or, equivalently, only modify instructions that precede them in the program.

Here's the syntax of a program: 
```
{ header } body
```
or 
```
body
``` 

The header consists of 0 or more recipe definitions, whose syntax is as follows:
``` 
RECIPENAME {
  recipebody
}
```
where the recipe name must be in all capital letters, and the body is a series of instructions of the 4 types mentioned above. A recipe should only be defined once in the header - creating 2 recipes with the same name will lead to undefined behaviour. 

Here's the syntax of the 4 basic types of instructions:
  - Regular instruction : `<verb> <quantity> @ <ingredient>`. For example, `scoop 2 spoons @ sugar;`.
  - Make instruction : `make <RECIPENAME>`. For example, `make LATTE`. If you have a `make <RECIPE>` in your program, `RECIPE` should be defined in the header of the program.
  - Swap instruction : `swap <ingredient1> -> <ingredient2>`. For example, `swap water -> espresso;`. This substitues in water for espresso for every instance of espresso that's currently in the drink.
  - Remove instruction : `remove <ingredient>`. This removes all instances of the ingredient specified that have been added before this line.

In an instruction, \<verb\> is any English verb (describing how to add the ingredient in question), \<quantity\> is 
a number followed by a word to specify how much of the ingredient to add, and \<ingredient\> is the name of 
the ingredient. For example, in the line `add 2 shots of espresso`, `add` is the verb, `2 shots` is the quantity, and
`espresso` is the ingredient. It's important to note that while an ingredient can contain multiple words, a quantity must be a number followed by 1 word, and the verb can only be one word. 

### How to run a program

You'll need to have sbt and scala installed for this. First, clone this repository so you have the source code. Then, you can run a program by typing the following into your terminal (when located in the top level directory of the CaffeineScript project) `sbt "project caffeinescript" "run path/to/file"` where `path/to/file` is the path to the script you wish to run. Alternatively, I've included a simple Makefile that runs testscript.caf, so if you're writing your program in that file you can run it by typing `make`.

## Example valid program

### Input

```
{
    MOCHA {
        make LATTE;
        add 2 scoops @ chocolate powder;
    }

    LATTE {
        add 4 shots @ espresso;
        pour 3 oz @ milk;
        scoop 2 spoons @ sugar;
    }
}

make MOCHA;
swap water -> espresso;
remove milk;
```

### Output

```
adding 4.0 shots of water 
scooping 2.0 spoons of sugar
adding 2.0 scoops of chocolate powder
```

## Example invalid program

### Input

```
{
    MOCHA {
        make LATTE;
        add 2 scoops @ chocolate powder;
    }

    LATTE {
        add 4 shots @ espresso;
        pour 3 oz @ milk;
        scoop 2 spoons @ sugar;
    }

    LATTE {
        add 4 shots @ espresso;
        pour 3 oz @ milk;
        scoop 2 spoons @ sugar;
    }
}

make MOCHA;
```

### Output

```
[error] (run-main-0) java.lang.IllegalArgumentException: Recipe with name: LATTE is defined multiple times!
java.lang.IllegalArgumentException: Recipe with name: LATTE is defined multiple times!
	at caffeineScript.semantics.Transformer.package$$anonfun$transform$1.apply(Transformer.scala:15)
	at caffeineScript.semantics.Transformer.package$$anonfun$transform$1.apply(Transformer.scala:14)
	at scala.collection.immutable.List.foreach(List.scala:381)
	at caffeineScript.semantics.Transformer.package$.transform(Transformer.scala:14)
	at caffeineScript.main.Main$.main(Main.scala:22)
	at caffeineScript.main.Main.main(Main.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
[trace] Stack trace suppressed: run last compile:run for the full output.
java.lang.RuntimeException: Nonzero exit code: 1
	at scala.sys.package$.error(package.scala:27)
[trace] Stack trace suppressed: run last compile:run for the full output.
[error] (compile:run) Nonzero exit code: 1
[error] Total time: 1 s, completed Dec 9, 2015 8:43:54 PM
make: *** [all] Error 1
```
