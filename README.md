# README #

Just compile the source and you are ready.

### General ###

* This project is born to avoid boxing and autoboxing when using collections in Java.
* 1.1
* [Please report any bug here](https://bitbucket.org/rrev/primitivecollections/issues?status=new&status=open)

### Important ###

Class names will be **[Type]**Collection.

So:

- **Int**ArrayList -> ArrayList<Integer>
- **Float**LinkedList -> LinkedList<Float>

Create a default type (example ArrayList), implement it for a specific type, then write the generic template and use the converter to create the version for the other primitives
Javadoc will be online [here](http://revonline.comuf.com/javadoc/primitivecollections/1/)