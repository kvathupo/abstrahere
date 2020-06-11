#include <iostream>
#include <memory>
#include "linked_list.h"
#include "stack.h"

int main() {
    strct::Stack<int> s1 {};

    s1.push(1);
    s1.push(2);
    s1.push(3);

    std::cout << "Printing s1\n";
    s1.print();
    
    std::cout << "Printing s2\n";
    strct::Stack<int> s2 {s1};
    s2.print();

    std::cout << "Printing s1\n";
    s1.print();

    return 0;
}
