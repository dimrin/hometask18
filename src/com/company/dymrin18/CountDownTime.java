package com.company.dymrin18;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownTime {
    private int seconds = 60;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);


    public void start(int time) {
        try {
            time--;
            seconds--;
            while (time > 0 || seconds > 0) {
                if (time >= 10) {
                    timeCounting(time, "");
                } else if (time < 10) {
                    timeCounting(time, "0");
                }
            }

            System.out.println("The End");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void timeCounting(int time, String symbol) throws InterruptedException {
        while (time > 0 || seconds > 0) {
            countDownLatch.await(1, TimeUnit.SECONDS);
            if (seconds < 10) {
                System.out.printf("%s%d:0%d%n", symbol, time, seconds--);
            } else if (seconds >= 10) {
                System.out.printf("%s%d:%d%n", symbol, time, seconds--);
            }
            if (seconds < 0) {
                seconds = 59;
                time--;
            }
        }
        System.out.printf("%s%d:0%d%n", symbol, time, seconds--);
    }
}
