#pragma once

#include <memory>
#include <vector>
#include <type_traits>
#include <cassert>
#include <algorithm>

inline namespace strct {

template<typename T,
    typename = typename std::enable_if<std::is_copy_constructible<T>::value, T>::type>
class LinkedList {
public:
    // constructors
    LinkedList(): sz{0} {}
    // Copy-constructor that deep-copies the smart pointer
    LinkedList(const LinkedList& other): sz{other.sz} { 
        std::shared_ptr<Node> curr = other.head;
        std::vector<T> v1 {};

        while (curr != nullptr) {
            v1.push_back(curr->getContents());
            curr = curr->getNext();
        }

        auto it = v1.rbegin();
        auto it_ending = v1.rend();
        while (it != it_ending) {
            this->add(*it);
            ++it;
        }
    }
    LinkedList(LinkedList&&) = default;     // default move of shared_ptr
                                            // transfers ownership, as needed

    // operators
    LinkedList& operator=(const LinkedList& other) {    // oftentimes, no need for self-assignment check
        using std::swap;

        LinkedList tmp {other};                 // copy-assignment allows constructor exceptions and
                                                // partial assignment of member variables
        swap(tmp, *this);
        return *this;
    }
    LinkedList& operator=(const LinkedList&& other) {
        using std::swap;

        swap(other, *this);
        return *this;
    }
    // Zero-indexed access
    T& operator[](std::size_t idx) {
        assert(this->head != nullptr);
        assert(idx < this->sz);
        std::shared_ptr<Node> curr = this->head;
        for (auto i = 0; i < idx; i++) {
            curr = curr->getNext();
        }
        return curr->getContentsAddr();
    }

    // member functions
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
        std::shared_ptr<Node> curr = this->head; 
        while (curr != nullptr) {
            std::cout << curr->getContents() << " ";
            curr = curr->getNext();
        } 
        std::cout << "\nEnding print\n";
    }
    T remove(const T& input) {
        assert(this->head != nullptr);
        std::shared_ptr<Node> curr = this->head;
        std::shared_ptr<Node> next = curr;
        while (next->getContents() != input) {
            curr = next;
            next = curr->getNext();

            if (next == nullptr) {
                std::cout << "No such element!\n";
                return T{input};
            }
        }
        curr->setNext(next->getNext());
        this->sz--;
        return T{input};                        // force RVO (since c++17)
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
    // nested class
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
        T& getContentsAddr() {
            return this->val;
        }
        std::shared_ptr<Node> getNext() {
            return next;                   // Bumps use count to 2
        }
        void setNext(std::shared_ptr<Node> in_node) {
            next = in_node;
        }
    }; 

    // member variables
    std::shared_ptr<Node> head {};
    int sz;
};

}
