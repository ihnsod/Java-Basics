package com.ihnsod.basics.designpattern.chainofresponsibility;

/**
 * 责任链设计模式
 * 情景
 * 公司要对项目经理只能报销<=500的餐费，部门经理只能报销<=1000且大于500的餐费，
 * 总经理只能报销1000以上的餐费，这一次，java君在享用了自己制定的菜品之后，
 * 希望经理大爷们给他报销，那么大家可能会这样写。
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        You you = new You(600);

        Manager projectManager = new ProjectManager();
        Manager departmentManager = new DepartmentManager();
        Manager generalManager = new GeneralManager();

        projectManager.setNextManager(departmentManager);
        departmentManager.setNextManager(generalManager);

        String reply = projectManager.reply(you);
        System.err.println(reply);
    }

}

class You {
    private Integer money;

    public You(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return money;
    }
}

abstract class Manager {

    private Manager nextManager;

    public abstract String chainReply(You you);

    public abstract boolean check(You you);

    public final String reply(You you) {
        if (check(you)) {
            return this.chainReply(you);
        }
        if (nextManager != null) {
            return this.nextManager.reply(you);
        }
        return "you消费太高，自己报销吧..";
    }

    //设置下链的下一个接收者
    public void setNextManager(Manager nextManager) {
        this.nextManager = nextManager;
    }
}

class ProjectManager extends Manager {

    private static final Integer AMOUNT_OF_REIMBURSEMENT = 200;

    @Override
    public String chainReply(You you) {
        return "项目经理给你报销了" + you.getMoney() + "元";
    }

    @Override
    public boolean check(You you) {
        return you.getMoney() <= AMOUNT_OF_REIMBURSEMENT;
    }
}

class DepartmentManager extends Manager {

    private static final Integer AMOUNT_OF_REIMBURSEMENT = 500;

    @Override
    public String chainReply(You you) {
        return "部门经理给你报销了" + you.getMoney() + "元";
    }

    @Override
    public boolean check(You you) {
        return you.getMoney() <= AMOUNT_OF_REIMBURSEMENT;
    }
}

class GeneralManager extends Manager {

    private static final Integer AMOUNT_OF_REIMBURSEMENT = 1000;

    @Override
    public String chainReply(You you) {
        return "总经理给你报销了" + you.getMoney() + "元";
    }

    @Override
    public boolean check(You you) {
        return you.getMoney() <= AMOUNT_OF_REIMBURSEMENT;
    }
}