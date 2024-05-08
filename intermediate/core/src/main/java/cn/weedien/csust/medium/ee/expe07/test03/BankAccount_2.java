package cn.weedien.csust.medium.ee.expe07.test03;

public class BankAccount_2 {
    private final Object lock = new Object();
    private double balance = 10000;

    public static void main(String[] args) {
        BankAccount_2 account = new BankAccount_2();

        Thread threadA = new Thread(() -> {
            account.withdraw(5000);
        });

        Thread threadB = new Thread(() -> {
            account.withdraw(5000);
        });

        threadA.start();
        threadB.start();
    }

    public void withdraw(double amount) {
        synchronized (lock) {
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
}
