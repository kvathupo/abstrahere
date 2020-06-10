#include <iostream>
#include <memory>
#include "linked_list.h"

int main() {
    std::cout << "At least I don't crash... yet...\n";
    LinkedList<int> lst {};
    lst.add(1);
    lst.add(2);
    lst.add(3);
    std::cout << "Printing lst...\n";
    lst.print();
    std::cout << "Printing one-by-one!\n";
    std::cout << lst[0]++ << " " << lst[1]++ << " " << lst[2]++ << "\n";
    std::cout << "Printing lst...\n";
    lst.print();
    return 0;
}
