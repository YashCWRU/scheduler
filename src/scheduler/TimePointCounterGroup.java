package scheduler;

import java.util.*;

final class TimePointCounterGroup {
    private Map<TimePoint, TimePointCounter> counters;
    private List<TimePoint> independentTimePoints;

    TimePointCounterGroup(Map<TimePoint, TimePointCounter> counters, List<TimePoint> independentTimePoints) {
        this.counters = counters;
        this.independentTimePoints = independentTimePoints;
    }

    static final TimePointCounterGroup create(Set<TimePoint> timePoints){
        Map<TimePoint, TimePointCounter> counters = new HashMap<>();
        List<TimePoint> independentTimePoints = new ArrayList<>();
        for(TimePoint timePoint: timePoints) {
            counters.put(timePoint, new TimePointCounter(timePoint.inDegree()));
            if (timePoint.isIndependent()) //Prolly need too to add counters for independent timepoints.
                independentTimePoints.add(timePoint);
        }
        return new TimePointCounterGroup(counters, independentTimePoints);
   }

   //Package private helper getter
   List<TimePoint> getIndependentTimePoints(){
        return independentTimePoints;
   }

    boolean isAnyIndependent(){
        return !Objects.isNull(independentTimePoints); //Will return true if the independentTimePoints has elements.
    }

    //
    TimePoint removeIndependent(){
        //Preconditions
        assert !isAnyIndependent() : "No independent timepoints in the list to remove";

        return independentTimePoints.remove(0); //Removes the first object in the list.
    }

    //TODO: Check pre conditions
    void decrementCounters(Set<TimePoint> timePoints){
        for (TimePoint timePoint : timePoints){
            //Preconditions. No need to handle errors outside of asserts for non public method.
            assert !counters.containsKey(timePoint) : "Time point is unknown to the group";
            assert independentTimePoints.contains(timePoint) : "Time point is independent";

            counters.get(timePoint).decrement();
            if (counters.get(timePoint).isIndependent()){
                independentTimePoints.add(timePoint);
            }
        }
    }
}
