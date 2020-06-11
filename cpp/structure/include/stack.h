#pragma once

#include <memory>
#include <vector>
#include <type_traits>
#include <cassert>

inline namespace strct {

template<typename T,
    typename = typename std::enable_if<std::is_constructible<T>::value, T>::type>
class Stack {
public:
    // constructors
    Stack() = default;
    Stack(const Stack& other): sz{other.sz} {
        std::vector<T> v(other.sz);
        std::shared_ptr<Node> curr = other.top;
        int itr {0};

        while (curr) {
            v[itr] = curr->dat;
            curr = curr->below;
            itr++;
        }

        for (auto i = other.sz - 1; i >= 0; i--) {
            this->push(v[i]);
        }
    }
    Stack(Stack&&) = default;

    // member functions
    void push(T ele) {
        this->top = std::make_shared<Node>(ele, this->top);
        this->sz++;
    }
    T poll() {
        assert(this->isTop());

        T temp {this->top->dat};
        this->top = this->top->below;
        this->sz--;
        return T {temp};
    }
    T peek() {
        assert(this->isTop());
        
        return T {this->top->dat};
    }
    void print() {
        std::shared_ptr<Node> curr {this->top};

        std::cout << "Starting print (top to bottom)\n";
        while (curr) {
            std::cout << curr->dat << " ";
            curr = curr->below;
        }
        std::cout << "\nEnding print\n";
    }
    

    // destructor
    ~Stack() = default;
    
private:
    // nested class
    class Node {
    public:
        std::shared_ptr<Node> below {};
        T dat {};

        Node();
        Node(T val, std::shared_ptr<Node> bottom): dat{val},
                                                  below{bottom}
        {}
    };

    bool isTop() {
        return this->top != nullptr;
    }
    // member variables
    int sz {0};
    std::shared_ptr<Node> top {};
};

}
