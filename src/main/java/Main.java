import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public class D implements Cloneable {
        private int aMax;
        private int bMax;
        private int a;
        private int b;
        private int step;

        public D(int aMax, int bMax) {
            this.aMax = aMax;
            this.bMax = bMax;
            a = 0;
            b = 0;
            step = 0;
        }

        public void chargeA() {
            a = aMax;
            step++;
        }

        public void chargeB() {
            b = bMax;
            step++;
        }

        public void dischargeA() {
            a = 0;
            step++;
        }

        public void dischargeB() {
            b = 0;
            step++;
        }

        public void transfer() {
            if (bMax - b >= a) {
                a = 0;
                b += a;
            } else {
                a = bMax - b;
                b = bMax;
            }
            step++;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "D{" +
                    "aMax=" + aMax +
                    ", bMax=" + bMax +
                    ", a=" + a +
                    ", b=" + b +
                    ", step=" + step +
                    '}';
        }

        public long hash() {
            long a1 = (long) a;
            long b1 = (long) b;
            return (a1 + b1) * (a1 + b1 + 1) / 2 + a1;
        }
    }

    private int getStep(int a, int b, int c) {
        Queue<D> queue = new LinkedList();
        D ini = new D(a, b);
        queue.offer(ini);
        int count = 0;
        HashSet<Long> set = new HashSet<>();
        set.add(ini.hash());
        while (!queue.isEmpty() && count < 10000) {
            count++;
            D state = queue.poll();
            D state1 = null;
            D state2 = null;
            D state3 = null;
            D state4 = null;
            D state5 = null;
            try {
                state1 = (D) state.clone();
                state2 = (D) state.clone();
                state3 = (D) state.clone();
                state4 = (D) state.clone();
                state5 = (D) state.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            if (state1.a < state1.aMax) {
                state1.chargeA();
                if (state1.a == c) {
                    return state1.step;
                } else if (!set.contains(state1.hash())) {
                    queue.offer(state1);
                    set.add(state1.hash());
                }
            }

            if (state2.b < state2.bMax) {
                state2.chargeB();
                if (state2.b == c) {
                    return state2.step;
                } else if (!set.contains(state2.hash())) {
                    queue.offer(state2);
                    set.add(state2.hash());
                }
            }

            if (state3.a > 0) {
                state3.dischargeA();
                if (!set.contains(state3.hash())) {
                    queue.offer(state3);
                    set.add(state3.hash());
                }
            }

            if (state4.b > 0) {
                state4.dischargeB();
                if (!set.contains(state4.hash())) {
                    queue.offer(state4);
                    set.add(state4.hash());
                }
            }

            if (state5.a > 0 && state5.b < state5.bMax) {
                state5.transfer();
                if (state5.a == c || state5.b == c) {
                    return state5.step;
                } else if (!set.contains(state5.hash())) {
                    queue.offer(state5);
                    set.add(state5.hash());
                }
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = 0;
        while (count < N) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int step = main.getStep(a, b, c);
            System.out.println(step);
            count++;
        }
        sc.close();
    }
}

