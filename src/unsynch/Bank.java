package unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
    public Bank(int n, double initialbalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialbalance);
    }

    public void transfer(int from, int to, double amout) {
        bankLock.lock();
        //if(accounts[from] < amout ) return;
        try {
            System.out.println(Thread.currentThread());
            accounts[from] -= amout;
            System.out.printf("%10.2f from  %d to %d", amout, from, to);
            accounts[to] += amout;
            System.out.printf("Total Balance :%10.2f%n", getTotalBalance());
        }
        finally {
            bankLock.unlock();
        }
    }
    public double getTotalBalance()
    {
        double sum = 0;
        for (double a:accounts)
            sum += a;
        return sum;
    }
    public  int size()
    {
        return  accounts.length;
    }
}
