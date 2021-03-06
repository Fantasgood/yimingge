# `[[REDACTED]]`

Run `node redacted.js [options] <filename>` to execute the program. Any non-ancient
nodejs version should be enough.

## Options

* **--no-execute**: Perform the requested tasks but don't execute the program
* **--dump-bytecode**: Dump the generated bytecode
* **--dump-ast**: Dump the generated AST

## The idea

The theme of the compo is _"first-class comments"_. Redacted tries to accomplish
this by offering mechanisms for manipulating the behaviour of the comments.

There is a suggested syntax that comments _can_ use: `[[TAG(arg1, arg2, arg2): Description]]`
or `[[TAG: Description]]`). Comments that don't match that structure are
categprozed as 'naked' and can be handled with the corresponding handler.

The original idea included the ability to iterate the comments of a given
function, and exposing the program state to the handler. Because of time and
manpower limitations only this initial sketch is available.

## Pseudo-BNF

During the writing of the parser we changed things a bit. This may not be 100%
to the code.

```
literal := STRING | NUMBER
ident   := IDENT

simple_expr  := literal | ident | parenthesized_expr | lambda | block | cond | loop
expr         := assignment | call | field_access | simple_expr
assignment   := ((field_access | ident) ':=')+ (call | simple_expr)
field_access := (ident | parenthesized_expr) ('>>' ident)+
call         := (field_access | simple_expr) (field_access | simple_expr)+
sequence     := expr ('.' expr)* '.'?
parenthesized_expr := '(' sequence ')'
lambda := 'with' ident+ block
block  := 'do' sequence 'done'
cond   := 'if' expr 'then' expr 'else' expr
loop   := 'while' expr 'holds' expr
```

### Caveats

* Numbers are only integers
* Strings have SQL-like quoting (that is `hello 'world` is written `'hello ''world'`)

## Language

The language is dynamically typed, with the primitive types represented with
proxies to javascript values.

### Functions

Both "native" (written in the host language) and `[[redacted]]` functions have
the following special properties:

* **Function>>call**: Calls the function without arguments
* **Function>>with_args**: A function that takes an array of arguments and
  calls the underlying function with those arguments

### Arrays

Arrays are created with the `array` primitive. They have the following special
properties:

* **Array>>at**: A function that takes a number and performs a dynamic property
  load for that index in the array
* **Array>>set**: A function that takes a number and a value and performs a
  dynamic property store for that index in the array
* **Array>>length**: The length of the array
* **Array>>push**: A function that takes an element and adds it to the end of
  the array
* **Array>>pop**: A function that removes the last element of the array and
  returns it

### Objects

Objects are created with the `obj>>call` primitive.

### Other primitives available

* **<**: A function that makes comparisons for host values. If the values
  cannot be compared returns `none`
* **+**: A function that add two numbers
* **=**: A function that makes an equality comparison.
* **none**: A kind of dummy value, our `undefined`
* **print**: Prints to the screen, our `console.log`
* **str_concat**: Mashes an arbitrary number of values into a string
* **comments**: The global CommentsController, it only has the `category`
  field: a function to retrieve or configure comment handlers

## Examples

Some comentated code examples are available in the `examples` folder.
