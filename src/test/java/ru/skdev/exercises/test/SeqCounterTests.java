package ru.skdev.exercises.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Test;
import ru.skdev.exercises.SeqCounter;

/**
 *
 * @author sergekos
 */
public class SeqCounterTests {

    @Test(expected = SeqCounter.InvalidInputException.class)
    public void tooMuchTest() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream("10001\n".getBytes());
        SeqCounter.countSeq(in);
    }
}
