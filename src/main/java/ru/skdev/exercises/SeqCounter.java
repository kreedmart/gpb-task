package ru.skdev.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author sergekos
 */
public class SeqCounter {

    public static class InvalidInputException extends RuntimeException {
 
        public InvalidInputException() {
        }

        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void generateData(OutputStream out) {
        final long total = 10000;
        final Random random = new Random();

        try (PrintWriter writer = new PrintWriter(out)) {
            writer.println(total);

            for (int i = 0; i < total; i++) {
                writer.println(random.nextInt(2));
            }
        }
    }

    public static int countSeq(InputStream is) throws IOException {
        int maxSeqLength = 0;
        int seqLength = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int total = Integer.parseInt(reader.readLine());

            if (total > 10000) {
                throw new InvalidInputException("Too much");
            }

            boolean prev1 = false;

            for (int i = 0; i < total; i++) {
                int n = Integer.parseInt(reader.readLine());

                if (n == 1) {
                    if (prev1) {
                        seqLength++;

                    } else {
                        if (seqLength > maxSeqLength) {
                            maxSeqLength = seqLength;
                        }

                        seqLength = 1;
                        prev1 = true;
                    }

                } else {
                    prev1 = false;
                }
            }
        }

        if (seqLength > maxSeqLength) {
            maxSeqLength = seqLength;
        }

        return maxSeqLength;
    }

    public static void main(String[] args) throws IOException {
//        PipedInputStream in = new PipedInputStream();
//        PipedOutputStream out = new PipedOutputStream(in);
//        CompletableFuture.runAsync(() -> generateData(out));
//        int count = countSeq(in);

        int count = countSeq(System.in);

        System.out.println(count);
    }
}
