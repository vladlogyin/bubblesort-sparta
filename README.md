# Bubble sort

Bubble sort works by comparing adjacent elements and swapping them if they're out of order. The pairs of adjacent elements are selected in an incremental fashion; from one end of the collection, to the other;

|-|
4 1 2 3
  |-|
1 4 2 3
    |-|
1 2 4 3

1 2 3 4 

## Implementation

The code is implemented through lots of generics.
Sort class which has an overloaded sort function for different types of collections such as arrays, lists, etc.

All of the public sort functions implement a comparison function and a lambda function so that are then passed to an internal bubble sort function which just ties everything together.


## Similar sorting algorithms

Shaker sort
