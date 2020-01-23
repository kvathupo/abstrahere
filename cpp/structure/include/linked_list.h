#pragma once

namespace strct {

template<typename T>
class LinkedList {
private:
    class Node {
    private:
        T val;
        Node *next;
    public:
        virtual T getContents() = 0;
        virtual Node *getNext() = 0;
        ~Node ();                           // Don't intend for inheritance
                                            // from base implementation, hence
                                            // not declared virtual
    }; 
    Node *head;
    int sz;
public:
    virtual void add(T) = 0;
    virtual T remove(T) = 0;
    virtual void clear() = 0;
    int size();
};

}
