package com.procamp.threadlocal;

class Service {
    void put(int val) {
        SecurityContextHolder.put(val);
    }

}

class Repository {
    int print() {
        return SecurityContextHolder.get();
    }
}