#include <iostream>
#include <memory>
#include "linked_list.h"

int main() {
    std::cout << "At least I don't crash... yet...\n";
    LinkedList<int> lst {};
    lst.add(1);
    lst.add(2);
    lst.add(3);
    lst.remove(2);
    LinkedList<int> lst2 = std::move(lst);
    lst.clear();
    std::cout << "Printing lst...\n";
    lst.print();
    std::cout << "Printing lst2...\n";
    lst2.print();
    return 0;
}
