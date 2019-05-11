package com.ihnsod.basics.designpattern.statusmodel;

/**
 * 状态模式
 */
public class StatusModel {

    abstract class Status {
        protected VendingMachine vendingMachine;

        public void setVendingMachine(VendingMachine vendingMachine) {
            this.vendingMachine = vendingMachine;
        }

        abstract void insert();//抽象买东西动作

        abstract void shop();//抽象买东西动作

        abstract void pull();//抽象拔卡动作
    }


    class WithCard extends Status {

        @Override
        void insert() {
            System.err.println("已经插入了卡！");
        }

        @Override
        void shop() {
            System.err.println("有卡买东西!");
        }

        @Override
        void pull() {
            System.err.println("有卡可以拔卡");
        }
    }

    class NoCard extends Status {

        @Override
        void insert() {
            System.err.println("可以插卡！");
        }

        @Override
        void shop() {
            System.err.println("无卡不能买东西");
        }

        @Override
        void pull() {
            System.err.println("无卡不能拔卡！");
        }
    }

    class VendingMachine {
        public WithCard withcard = new WithCard();

        public NoCard noCard = new NoCard();

        private Status status;

        public void setVendingMachine(Status status) {//设置自动售货机的状态
            this.status = status;
            this.status.setVendingMachine(this);//把上下文（自动售货机）传给该状态。
        }

        public void insert()//自动售货机的插入操作即为在当下的状态下的插入操作。下同。
        {
            this.status.insert();
        }

        public void shop() {
            this.status.shop();
        }

        public void pull() {
            this.status.pull();
        }

    }
}
