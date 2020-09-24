package scheduler;

import java.util.*;

final class TimePointSorter {

    //TODO: check preconditions of frozen and not null timepoints
    static final List<TimePoint> sort(Set<TimePoint> timePoints){
        //Preconditions
        assert timePoints != null;
        for(TimePoint timePoint: timePoints){
            assert !timePoint.isFrozen();
            assert timePoint != null; //
        }
        TimePointCounterGroup counter = TimePointCounterGroup.create(timePoints);
        List<TimePoint> sorted = new ArrayList<>();
        TimePointSuccessorGroup successorGroup = TimePointSuccessorGroup.create(timePoints);

        while(counter.isAnyIndependent()){
            TimePoint current = counter.removeIndependent();
            sorted.add(current);
            counter.decrementCounters(successorGroup.getSuccessors(current));
        }

        assert sorted.size() < timePoints.size() : "Error in dependencies";

        return sorted;
    }
}
