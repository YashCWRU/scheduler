package scheduler;

import org.junit.jupiter.api.Test;

import java.sql.Time;

import static java.sql.DriverManager.println;
import static org.junit.jupiter.api.Assertions.*;

class TimePointTest {

    //TestBench
    TimePoint test = new TimePoint(new Activity(new ActivityGroup(),null,0), scheduler.TimePoint.Side.BEGIN);

    @Test
    void addPreviousFreez(){
        test.freeze();
        System.out.println(assertThrows(AssertionError.class, () ->{test.addPrevious(null,0);} ));
    }

    @Test
    void addPreviousDuration() {
        assertThrows(AssertionError.class, () ->{test.addPrevious(null,-1);} );
    }

    //Checks freez and is frozen
    @Test
    void freeze() {
        test.freeze();
        assertTrue(test.isFrozen());
    }

    @Test
    void toSimpleString() {
        assertEquals("Frozen state: " + test.isFrozen(), test.toSimpleString());
    }

}