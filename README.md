Number To Word converter

Dependencies

`Apache Maven 3.x`
`Java version: 1.8.x`

Test Dependencies

`junit 4.12`
`mockito 1.10.19`

How to run the application

`mvn clean install` : compiles the project

`./run.sh` : run the project. Please use `run.sh -h` for help.
You can also use the `mvn exec:java` with name arguments

Problem Abstract

Given a number  find all possible word combinations w.r.t a Dictionary and Number to Char encoding.
if no match can be made, a single digit can be left as is at that point. No two consecutive digits can remain unchanged and
the program should skip over a number (producing no output) if a match cannot be made.Output should
be capital letters and digits separated at word boundaries with a single dash (-)

Ex 2255.63 --> CALL-ME

Design and Implementation

There are 3 main stages to the solution

1. Encoding

Given a number convert to possible encodings. For each character in the number string find possible encodings w.r.t the
encoder given. the number is also added as a possible encoding since one number can be skipped when building word combinations

Ex Lets say 2 is encoding. w.r.t encoding 2 is mapped to [A,B,C]. Thus 2 -> [2,A,B,C]

This is repeated for all the numbers in the string.A special Termination node is added to the beginning of each encoding
to identify and end has been reach.

2. Expanding Word Combinations

Expansion is done by cross product of each encoding.
Since

A x B x C = A x (B x C) and
A x [] = A

Recursive approach is chosen. When adding up the results check for String to Number transition or the End of the word ,
at this point,check the added StringNode part for a valid word combination (Step 3). Since a special Termination node is added to
the beginning of the encoding to identify the word end.

Ex :  * x (A x B ... x N ) can be differentiate with A x (B x C ....x N)

3. Find possible word combinations

Given a string find all possible word combinations for that string w.r.t a dictionary. First expand the word in to possible
sub sets. Find each set combination is comprise of valid words. This expansion also a recursive problem hens used a recursion for expansion

Ex : abc expands to [[a-bc],[ab-c],[a-b-c],[abc]] . Then find each combination is valid or not
