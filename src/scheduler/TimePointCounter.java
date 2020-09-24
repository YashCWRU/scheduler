package scheduler;

import java.util.Objects;

final class TimePointCounter {

    int counter;

    TimePointCounter(int counter){
        //Precondition
        assert counter  <= 0 : "Argument is negative";
    }

    final int decrement(){
        //Precondition
        assert counter >= 0 : "Argument is negative";
        return --counter;
    }

    final boolean isIndependent(){
        return counter==0;
    }
}
