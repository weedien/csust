package cn.weedien.csust.medium.ee.expe07.test03;

public class BankAccount_1 {
    private double balance = 10000;

    public static void main(String[] args) {
        BankAccount_1 account = new BankAccount_1();

        Thread threadA = new Thread(() -> {
            account.withdraw(5000);
        });

        Thread threadB = new Thread(() -> {
            account.withdraw(5000);
        });

        threadA.start();
        threadB.start();
    }

    public synchronized void withdraw(double amount) {
        try {
            Thread.sleep(5000); // 模拟网络传输延迟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 取款 " + amount + " 元，余额：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " 余额不足！");
        }
    }
}
