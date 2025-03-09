package org.personal.concurrency.exercises.runnable;

import java.math.BigInteger;

public class PowerCalculatingThread extends Thread {
    private BigInteger result = BigInteger.ONE;
    private final BigInteger base;
    private final BigInteger power;
    private Boolean isFinished = false;

    public PowerCalculatingThread(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    @Override
    public void run() {

        pow(base, power);
        this.isFinished = true;

    }

    private BigInteger pow(BigInteger base, BigInteger power) {
        for(BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0; i=i.add(BigInteger.ONE)) {
            this.result = this.result.multiply(base);
        }
        return this.result;
    }

    public BigInteger getResult() { return result; }
    public Boolean isFinished() { return isFinished; }
}

