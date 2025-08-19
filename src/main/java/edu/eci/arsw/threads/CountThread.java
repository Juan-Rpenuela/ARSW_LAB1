package edu.eci.arsw.threads;

/**
 *
 * @author juan.rpenuela
 */
public class CountThread extends Thread {
    int numberA ;
    int numberB;
public CountThread(int numberA, int numberB) {
    this.numberA = numberA;
    this.numberB = numberB;
}
    public void run() {
        count();

    }
    private void count() {
        for (int i = this.numberA; i <= this.numberB; i++) {
            System.out.println(i);
        }
    }
}