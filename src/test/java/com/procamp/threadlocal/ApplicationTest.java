package com.procamp.threadlocal;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void testThreadLocalWorks() {

        int sharedValueExpected = 10;

        Service service = new Service();
        service.put(sharedValueExpected);

        Repository repository = new Repository();
        int actualValue = repository.print();

        assertEquals(sharedValueExpected, actualValue);
    }
}