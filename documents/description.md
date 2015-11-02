#### Motivation

Coffee drinks are difficult to make. In the world of the ever-present hipster, it's getting harder and harder to get it just right. In a world where customers actually order a "split-shot, heavy on decaf, 20 oz, soy, extra dry latte, with half pump vanilla & half-pump hazlenut", keeping track of all the modifications to make for a coffee drink is a difficult experience even for a physical barista. On the other hand, automatic coffee machines have a finite amount of settings that a customer can customize, so are not as customizable as ordering from a live barista. 

If there was a standard language by which a customer could specify a drink to an arbitrary level of precision, then you could easily program an automatic coffee machine to make exactly the drink you want. 

We need a DSL for this because we want to enable users to specify their drinks to arbitrary levels of precision. There are so many factors that go into a caffeinated or alcoholic beverage that we need a language (rather than, for instance, an enumerated type), because there are just so many possibilities.

#### Language domain

This language addresses the domain of automated coffee drink specification. The domain is useful because it seems to me like automated drink machines will never be able to attain the quality of a human barista or bartended until you can specify your drinks as much as you want. 

I don't believe there are any other DSLs in this domain. The closest thing I could find while searching for similar languages was [this](https://en.wikipedia.org/wiki/Hyper_Text_Coffee_Pot_Control_Protocol). 

#### Language design

A program in the language is going to look like a list of instructions to execute, in order. We require the language to be imperative instead of declarative because it makes a difference the order in which ingredients are added to what the final product will look/taste like. Thus, we should allow the user to control the order in which things happen. For instance, most baristas add shots of coffee to the milk in a latte, and not the other way around. When a program runs, depending on the backend attached to the language, you could get as a result:
1. A physical coffee drink. (this would be ideal, but is too much work to implement for this class)
2. A sequence of print statements corresponding to the steps taken to produce the drink.
3. A visual rendering of the coffee drink.
4. Analysis of the recipe to see what ingredients you need to make the drink, and in what quantity. 

I'm not sure what kinds of control structures/data structures might be useful yet, except for a mutable list in which to keep the steps to execute that come out of the parser. When I decide exactly what the syntax of a program will look like, anything that doesn't match that syntax will throw an error because the parser won't be able to parse it. As far as compile or runtime errors go, I think figuring out what these will be will happen organically as I start to write the language. One possible semantic error I thought of is trying to add 0 parts of something - though you deal with this by ignoring the instruction, for the sake of readable code it would probably be better to not allow the user to do that. 

#### Example computations

Initially, I had thought that an program could look like this:
```
Begin LATTE:
1. 2 shots espresso
2. Add 10 oz 2% milk
3. Add 2 oz foam
4. Add 4 pumps sugar
End LATTE:
```
However, during the first peer review of my project pitch, it was suggested that I specify the amount of each ingredient to use in terms of weights. This is so that you could calibrate the machine to produce a drink of a particular size based on the size of cups available. 
So instead of specifying quantities as `2 shots espresso`, you could specify `10% espresso` and the machine would deal with figuring out the exact quantity to produce based on the cup size. In this case, we'd need the programmer to have a way to specify the cup size as well (if, for instance, they're using their own cup), maybe at the top of the file. 

So instead an example program might look like this:
```
Begin LATTE:
1. 7 parts 2% milk.
3. Add 1.5 parts foam
3. Add 1 part espresso
4. Add 0.5 parts sugar
End LATTE:
```
When this program runs, the following things should happen:
1. The parser parses the program into a list of rules to execute sequentially.
2. Each rule should be parsed to extract 2 key pieces of data - the name of the ingredient used and the quantity of that ingredient to add. 
3. Once the program is fully parsed, it will be executed by iterating over the list of rules and executing each one. 
4. A rule will be executed by calling the appropriate API function of the backend (the idea is that each of the possible backends I mentioned above implement the same interfact).
5. Depending on which backend is in place at the time, an output of some type will be created.