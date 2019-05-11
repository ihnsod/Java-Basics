package com.ihnsod.basics.designpattern.singleton;

public enum Singleton {
    SINGLETON;

    public static Singleton getSingleton() {
        return SINGLETON;
    }
}

class SingletonInnerClass {

    private SingletonInnerClass singletonInnerClass = new SingletonInnerClass();

    public static class Inner {
        public static final SingletonInnerClass getInstance() {
            return new SingletonInnerClass().singletonInnerClass;
        }
    }
}
