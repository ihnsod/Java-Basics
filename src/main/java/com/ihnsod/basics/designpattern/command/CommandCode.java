package com.ihnsod.basics.designpattern.command;

/**
 * 命令模式
 */
public class CommandCode {
    public static void main(String[] args) {
        Invoker invoker = new Invoker(new MyCommand(new Receiver()));
        invoker.excute();
    }
}

interface Command {
    void excute();
}

class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void excute() {
        this.receiver.doString();
    }
}

class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void excute() {
        command.excute();
    }
}

/**
 * 此类可以使用抽象类进行进一步抽象
 */
class Receiver {
    public void doString() {
        System.err.println("I'm to do something");
    }
}


