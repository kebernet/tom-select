Tom Select
==========

This is a basic library for working with Java collections built on Guava and Gwittir. It allow for
LINQ-ish selection from collections as well as sub-graphs.


Maven
-----

    <repository>
        <id>kebernet</id>
        <name>kebernet</name>
        <url>http://dl.bintray.com/kebernet/maven</url>
    </repository>

    ...

    <dependency>
        <groupId>com.totsp</groupId>
        <artifactId>tom</artifactId>
        <version>0.9</version>
    </dependency>

Getting Started
---------------

The first thing you need to do is statically initialize Tom once in you app with a Gwittir Introspector
implementation. In GWT, you would do this as:

    Tom.initialize(Introspector.INSTANCE);

From java you would initialize it with:

    Tom.initialize(new JVMIntrospector());

Once you have done this, you are ready to start working with the selector. A simple selection might look
like:

    import static com.totsp.tom.Tom.*;
    //...
    Iterable<Person> bobs = tom().select().from(people).where(eq("firstName", "Bob")).asIterable();

This would give you an `Iterable` of all the `Person` instances from the `people` collection where the
firstName property equals "Bob". The argument for the `where()` method is just a simple Guava Predicate<T> object
but tom has some utility methods for constructing them based on properties. Let's look at a slightly more
complex example:

    List<Person> notBobRobertsons = new ArrayList<Person>();
    tom().select().from(people).where(
       allOf(eq("firstName, "Bob"), not(eq("lastName", "Robertson")))
    ).into(notBobRobertsons);

Here we are selecting all the Bobs who aren't Bob Robertson and adding them to the list of notBobRobertsons. Here
`allOf()` is just a logical AND (as opposed to `anyOf()` which is the logical OR).

Property Expressions
--------------------

In addition to just matching the top level objects, you can use dotted notation match on child nodes in graphs.
For example, for find people whose boss is Bill Lumbergh, you might use:

    tom().select().from(people).where(
        allOf( eq("boss.firstName", "Bill"), eq("boss.lastName", "Lumbergh"))
    ).asIterable();

If a property in the expression is a collection, you can find matches on any sub-node of that collection.
For example, fo find people who have an address in the 30308 zip code you might use:

    tom().select().from(people).where(eq("addresses.zip", "30308")).asIterable();

Property expressions can also be used as part of the `select()` method. You can select just one property from each
of the objects. To find a `Set` of all the last names in the collection, you might use:

    Set<String> lastNames = new HashSet<String>();
    tom().select("lastName").from(people).every().into(lastNames);

If you select multiple property expressions in the select() method, the resultant iterable will be an
`Iterable<Map<String, ?>>` of each of the property expressions to the resolved value. Note, if you select
properties that are children of collections, you will get multiple collections of values on each value. In
this case, sometimes it is useful to do multiple selects. For example, if we wanted to get a list of all the
street addresses in the 30308 zip code:

    tom().select("street").from(
       concat(tom().select("addresses").from(people).every().asIterable()))
     .where(eq("zip", "30308")).into(selected);

Here we start by selecting all the List<Address> objects, then we use Guava's `concat()` method to merge these
into a single `Iterable`, where we select the "street" property where "zip" equals "30308".

Stats
-----

Another bit of code that is tossed in here, just because I use it with this a lot, is the `Statistics` class. This
is a collection built around Guava's Multiset that is useful for getting basic statistical information with
Tom Select. For example, if I wanted to know about the age of the Persons in the people collection:

    NumberStatistics<Integer> ages = new NumberStatistics<Integer>();
    tom.select("age").from(people).every().into(ages);
    System.out.println("The median age is: "+ages.median());
    System.out.println("The calculated median age is: "+ages.medianAverage());
    System.out.println("The most common age is: "+ages.mode());
    System.out.println("The average age is: "+ages.mean());
    System.out.println("Breakdown of ages: "+ages.histogram());

There are two classes: `Statistics` and `NumberStatistics`. The first will give a median, mode, and histogram for
any Comparable<T>. The NumberStatistics will use BigDecimal math to calculate the things that need actual numbers.
