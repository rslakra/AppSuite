# Algorithms
This project contains all the algorithms.

****
How to set up
---

#### 1. Clone the repository in your GIT account or local machine

> ```
>   git clone https://github.com/rslakra/AppSuite.git
> ```

#### 2. Build the project

> ```
>   cd Algorithms
>   ./build.sh
> ```
>

#### 3. Run the program

Run the program with the following command
  
>   ```./run.sh```

## Built With

* [Java](https://www.java.com/en/download/mac_download.jsp) - The Java Download Location
* [Maven](https://maven.apache.org/) - Dependency Management

## Data Structures

- Data Types
- Arrays
    - One Dimensional
        How to calculate the address of an element in 1-Dimensional array?
        
        ```
        A[i] = L0 + (i - LB) * W
        ```
        Here, ```i``` is an index of an element.
        - ```L0``` represents the base address of an array.
        - ```LB``` represents the lower bound of an array.
        - ```W``` represents the word size (the size of datatype).

    - Multi-Dimensional
    
        The memory is linear, so even in case of 2-dimensional array, the values are stored in 1-dimensional array.
        
        How to calculate the address of an element in 2-Dimensional array?
        
        ####Row-Major Mapping (If address starts with 0 onward)
        
        ```
        A[i, j] = L0 + (i * N + j) * W
        ```
        
        ####Row-Major Mapping (If address starts with 1 onward)
        
        ```
        A[i, j] = L0 + ((i - 1) * N + (j - 1)) * W
        ```

        Here, ```i``` and ```j``` are indices of row and column elements.
        - ```L0``` represents the base address of an array.
        - ```N``` represents the no. of columns in the array.
        - ```W``` represents the word size (the size of datatype).

        ####Column-Major Mapping (If address starts with 0 onward)
        
        ```
        A[i, j] = L0 + (j * M + i) * W
      
        ```
        ####Column-Major Mapping (If address starts with 1 onward)

        ```
        A[i, j] = L0 + ((j - 1) * M + (i - 1)) * W
        ```

        Here, ```i``` and ```j``` are indices of row and column elements.
        - ```L0``` represents the base address of an array.
        - ```M``` represents the no. of rows in the array.
        - ```W``` represents the word size (the size of datatype).

    - Adjutancy Matrix
- List
    - Array List
    - Linked List
    - Circular List
    - 
- Hashing
    - Collision
    - Open Hashing
        - Chaining
    - Closed Hashing
        - Linear Probing
        - Quadratic Probing
- Set
    - HashSet
    - Linked Set
- Map
    - HashMap
    - LinkedHashMap
    - ConcurrentHashMap
- Traversal
    - Linear
    - DFS
    - BFS
- Tree
    - Tree Traversal
        - In-Order
        - Pre-Order
        - Post-Order
    - Tree
    - Binary Tree
    - AVL Tree
    - B-Tree and B+-Tree
- Graph
    - Directed
    - Undirected
    - Hamiltonian Cycles
    - Articulation Point
    - Pendant
    - Relaxation
- Heap
- Search
    - Linear Search
    - Binary Search
- Sort

## Authors

* **Rohtash Lakra** - [Algorithms](https://github.com/rslakra/AppSuite.git/Algorithms)

See also the list of [contributors](https://github.com/rslakra/AppSuite.git/contributors) who participated in 
this project.
