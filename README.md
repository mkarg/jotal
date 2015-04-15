# jotal
The **Java Open Type Adapters Library** provides common off-the-shelf drop-in type mapping adapters for frameworks like JAX-RS, JPA, JAXB, and JSON-B. This not only includes mappings for common JRE types like `Instant`, `URL` or `Image`, but also the ability to implement custom type adapters once that fit into all four framework! Ain't that great? :-)

## Why you want *jotal*
Annoyed by JPA's inability to deal with `Instant` or `Image`, effectively forcing you to write a type adapter for JRE common classes? Bugged by re-implementing your custom type adapter four times to make it work with not only JPA but also with JAX-RS, JAXB and JSON-B? Then **jotal** is what you want!

## What *jotal* does
**jotal** not only provides default type adapters for JRE common classes working with JAX-RS, JPA, JAXB and JSON-B, it actually contains a framework which makes it pretty easy to use *any* existing type mapping technology (like *Apache Commons Convert*, *Google Guava Converter*, or simply any custom lambda expression) and turn it into a type conversion adapter. It's just second's and the conversion runs well in all these frameworks!

## How *jotal* works
Pure java, no magic, but dead simple: **jotal** comes with abstract conversion adapters implementing all the named technology in one place. The included JRE type adapters (or your custom ones) just extend these by providing callbacks to the super constructor. It cannot be simpler.
