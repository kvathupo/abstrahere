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
    // Copy-constructor that deep-copies the smart pointer
    LinkedList(const LinkedList& other): sz{other.sz} { 
        std::shared_ptr<Node> curr = other.head;
        while (curr != nullptr) {
            this->add(curr->getContents());
            curr = curr->getNext();
        }
    }
    LinkedList(LinkedList&&) = default;     // default move of shared_ptr
                                            // transfers ownership, as needed

    void add(T input) {
        if (this->head == nullptr) {
            this->head = std::make_shared<Node>(input);
            this->sz++;
            return;
        }
        std::shared_ptr<Node> new_head = std::make_shared<Node>(input, *(this->head));
        this->head = new_head;
        this->sz++;
    }
    void print() {
        std::cout << "Starting print\n";
        std::shared_ptr<Node> curr = this->head;        // Bumps use count to 2
        while (curr != nullptr) {
            std::cout << "Content: " << curr->getContents() << "\n";
            curr = curr->getNext();
        } 
        std::cout << "Ending print\n";
    }
    T remove(T input) {
        if (this->head == nullptr) {
            std::cout << "No such value found\n";
            return input;
        }
        std::shared_ptr<Node> curr = this->head;
        std::shared_ptr<Node> next = curr;
        while (next->getContents() != input) {
            curr = next;
            next = curr->getNext();
        }
        curr->setNext(next->getNext());
        this->sz--;
        return input;
    }
    void clear() {
        this->head = nullptr;
        this->sz = 0;
    }
    int size() {
        return this->sz;
    }   

    ~LinkedList() = default;
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
            return next;                   // Bumps use count to 2
        }
        void setNext(std::shared_ptr<Node> in_node) {
            next = in_node;
        }
    }; 

    std::shared_ptr<Node> head {};
    int sz;
};

}
