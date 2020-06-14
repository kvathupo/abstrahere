#include <iostream>
#include <memory>
#include "linked_list.h"
#include "stack.h"

strct::LinkedList<int> foo() {
    strct::LinkedList<int> lst {};

    lst.add(1);
    lst.add(2);
    lst.add(3);

    return lst; 
}

int main() {
    strct::LinkedList<int> lst = foo();
    lst.print();
    strct::LinkedList<int> lst2 = lst;
    lst2.print();
    lst.print();
    

    return 0;
}
