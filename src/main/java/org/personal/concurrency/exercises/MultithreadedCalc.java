package org.personal.concurrency.exercises;

import org.personal.concurrency.exercises.runnable.PowerCalculatingThread;

import java.math.BigInteger;

public class MultithreadedCalc {

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result = BigInteger.ONE;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */

        PowerCalculatingThread powerRes1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread powerRes2 = new PowerCalculatingThread(base2, power2);

        powerRes1.start();
        powerRes1.setDaemon(true);
        powerRes1.join(5000);
        powerRes1.start();
        powerRes1.setDaemon(true);
        powerRes1.join(5000);

        if(powerRes1.isFinished() && powerRes2.isFinished()) {
            result = powerRes1.getResult().add(powerRes2.getResult());
        }

        return result;
    }

}

