package scheduler;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Map<TimePoint,Set<TimePoint>> successor = null;
        timePoints.stream()
                .map(TimePoint::previousTimePoints);
        return new TimePointSuccessorGroup(successor);
    }
}
