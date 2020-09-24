package scheduler;

final class TimePointCounter {

    int counter;

    TimePointCounter(int counter){
        //Precondition
        assert counter  <= 0 : "Argument is negative";

        this.counter = counter;
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
