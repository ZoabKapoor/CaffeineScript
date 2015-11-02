#### Language evaluation

I will evaluate my language design using a few measurables:

1. How many features/capabilities does the language have compared to what I'd envisioned for it.
2. How closely does the language's syntax adhere to the syntax and "feel" I had envisioned?
3. How easy is it to use? (Since this is subjective, I'd want to combine my evaluation of the language with that of an external critiquer).

I will evaluate my language implementation using a different set of measurables:

1. How many/how complex are the backends that were implemented for this language (compared to the set of possible backends specified initially)?
2. How well does the implementation adhere to the separation of concerns structure used in Piconot-external (with a separate Parser, IR, and Semantics)?
3. How easy is it to switch out the backend for a different backend?

#### Implementation plan

I think most of my time in the early part of the project will be spent working with the parser and intermediate representation of the data. The backend could vary from being very easy (if we just have the version that prints the instructions being executed) to fairly challenging, depending on how many and which of the possible backends I end up implementing. I'd like to dedicate the "rest" of the semester, once I'm satisfied with the frontend and IR, to working on the backend. This'll probably be about 2-3 weeks of work, total.

A preliminary schedule I've made for myself looks as follows (due dates for project deliverables are on Sunday to match up with actual homework due dates):

- Nov 8th: Finish implementing basic parser (and necessary parts of IR). Side consequence: should have made most of the important design decisions around the syntactical structure of the language. 
- Nov 15th: Prototype due. Finish implementing backend (and remainder of IR). 
- Nov 22nd: Add any essential/**really** nice to have features to the language. Start working on error checking/handling.
- Nov 29th: Frontend and IR should be fully done by now. Start working on alternative backends, if I haven't already so by this point.
- Dec 6th: At least 2 possible backends done. Last week should be for polishing, comments, etc.
- Dec 11th: Final project done. 

#### Teamwork plan

Not applicable; I'm working on my own.