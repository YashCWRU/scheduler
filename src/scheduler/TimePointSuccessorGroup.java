package scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class TimePointSuccessorGroup {
    private final Map<TimePoint, Set<TimePoint>> successors;

    private TimePointSuccessorGroup(Map<TimePoint, Set<TimePoint>> successors) {
        this.successors = successors;
    }

    final Set<TimePoint> getSuccessors(TimePoint timePoint){
        return successors.get(timePoint);
    }

    //TODO: Set the successors by running thr the previous time points of the arguments.
    static final TimePointSuccessorGroup create(Set<TimePoint> timePoints){
        Map<TimePoint, Set<TimePoint>> successors = new HashMap<>();
        for(TimePoint timePoint : timePoints){
            successors.put(timePoint,timePoint.previousTimePoints());
        }
        return new TimePointSuccessorGroup(successors);
    }
}
