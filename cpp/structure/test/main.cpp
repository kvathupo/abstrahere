#include <iostream>
#include <memory>
#include "linked_list.h"

int main() {
    std::cout << "At least I don't crash... yet...\n";
    LinkedList<int> lst{};
    lst.add(1);
    lst.add(2);
    lst.add(3);
    lst.remove(2);
    lst.clear();
    lst.print();
    return 0;
}
