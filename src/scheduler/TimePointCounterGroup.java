package scheduler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

final class TimePointCounterGroup {
    private Map<TimePoint, TimePointCounter> counters;
    private List<TimePoint> independentTimePoints;

    TimePointCounterGroup(Map<TimePoint, TimePointCounter> counters, List<TimePoint> independentTimePoints) {
        this.counters = counters;
        this.independentTimePoints = independentTimePoints;
    }

    //TODO: finish the create the thingy
    static final TimePointCounterGroup create(Set<TimePoint> timePoints){
        return new TimePointCounterGroup(null,null);
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

    void decrementCounters(Set<TimePoint> timePoints){
        for (TimePoint timePoint : timePoints){
            counters.get(timePoint).decrement();
            if (counters.get(timePoint).isIndependent()){
                independentTimePoints.add(timePoint);
            }
        }
    }
}
