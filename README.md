# README #

[![Build][buildimage]](https://travis-ci.org/rrev/Primitive-Collections)

Just compile the source and you are ready.

### General ###

* This project is born to avoid boxing and autoboxing when using collections in Java.
* 1.1
* Check test folder for examples (i'm moving the project from bitbucket so...)

### Important ###

Class names will be **[Type]**Collection.

So:

- **Int**ArrayList -> ArrayList<Integer>
- **Float**LinkedList -> LinkedList<Float>

Create a default type (example ArrayList), implement it for a specific type, then write the generic template and use the converter to create the version for the other primitives
Javadoc will be online [here](http://revonline.comuf.com/javadoc/primitivecollections/1/)

[buildimage]: https://travis-ci.org/rrev/Primitive-Collections.svg?branch=master 
