#pragma once

#include <memory>

inline namespace strct {

// Definition
// * Since you cannot separate a templated class into header and implementation,
// it would be best to simply define the class with the declaration. 

template<typename T>
class LinkedList {
public:
    LinkedList(): sz{0} {}
    void add(T input) {
        if (this->head == nullptr) {
            this->head = std::make_shared<Node>(input);
            return;
        }
        std::shared_ptr<Node> curr = this->head;
        std::shared_ptr<Node> next = curr;
        while (next != nullptr) {
            curr = next;
            next = curr->getNext();
        }
        curr->setNext(std::make_shared<Node>(input));
    }
    void print() {
        std::cout << "Starting print\n";
        std::shared_ptr<Node> curr = this->head;
        while (curr != nullptr) {
            std::cout << curr->getContents() << "\n";
            curr = curr->getNext();
        } 
        std::cout << "Ending print\n";
    }
    T remove(T);
    void clear();
    int size();
private:
    class Node {
    private:
        T val;
        std::shared_ptr<Node> next {};
    public:
        Node(T dat): val{dat} {}
        Node(T dat, Node next_node): val{dat},
            next{std::make_shared<Node>(next_node)}
        {}
        T getContents() {
            return this->val;
        }
        std::shared_ptr<Node> getNext() {
            return next;                    // Does this bump refcount?
                                            // I think so, but check with use_count
        }
        void setNext(std::shared_ptr<Node> in_node) {
            next = in_node;
        }
    }; 

    std::shared_ptr<Node> head {};
    int sz;
};

}
