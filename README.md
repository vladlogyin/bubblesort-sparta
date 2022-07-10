# Sort Manager Project

An app that enables you to compare different sorting algorithms.

## Implementation details

This app is written around an MVC design pattern. The sorting algorithms are hot-swappable models, `SortManager` is the controller, and `SortView` is an interface on top of which different UIs can be built.

Instead of having a factory that needs modified to allow for new `Sorter` implementations, the `BackendProvider` class uses reflection to list all "subclasses" within a package. Once a subclass is selected by the user, it is instantiated.

Although the UI methods are declared inside of the `SortView` interface, it is not user selectable, and the only functional implementation so far is the terminal-based UI. 

Concurrent computing concepts are used to implement a request response system between the Controller and the View of the app. This is done to enable future UI extensions such as a proper GUI or alternative terminal UIs.

## Algorithms implemented so far

### BubbleSort
  * Implemented using generics
### MergeSort
  * Not an in-place implementation
  * Recursive
### BinaryTreeSort
  * Does not discard duplicates
  * Iterative
  * Extraction done using `Stack`

## UI stuff

* Request/Response implemented through callbacks

## Benchmarking

Benchmarking is done by repeatedly sorting the same generated array until over 100ms have passed so that overheads are 

## Java language features used

* Range-based for loops (for each)
* Generics (Map<>, Class<>, etc)
* Exception handling and custon exception classes
* Reflection
* Maven build system for automatic dependency resolution

# Troubleshooting
 * Logs are located in `logs/`


# Improvements
 - Use Future and promise instead of callback interfaces
 - Magically refactor all of the technical debt away
 

 
