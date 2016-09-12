package com.mikhail.exc;

public class TheMain {

    public static void main(String[] args) {
        new MainProcessing(args[0], args[1]).runProcessing();
        //new MainProcessingMemoryUsageReduce(args[0], args[1]).runProcessing();
    }
}
