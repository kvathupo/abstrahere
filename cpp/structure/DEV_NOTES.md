# Dev Notes
* When working with nested classes that are used as class members, the 
nested class members _must_ be defined after the nested class defintiion.
Otherwise, there is a compiler error of "type declared out of scope."
* A constructor _must_ be explicitly said to be default with `=default`.
Otherwise, the linker will fail.
* When calling the constructor of `std::vector` that allocates space, you
_must_ call the constructor, not use initializer braces!

## TODO
* In the copy constructor, why can I access the private member variables of
`other`?
