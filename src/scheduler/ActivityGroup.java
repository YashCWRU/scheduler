package scheduler;

import java.util.*;

public final class ActivityGroup {
    //group containing all the timepoints
    Set<TimePoint> timePoints = new HashSet<>();

    final void addTimePoints(Collection<TimePoint> timePoints){
        assert timePoints !=null : "Collection of timepoints is null";
        for(TimePoint timePoint: timePoints){
            //PreConditions
            assert timePoint !=null : "Timepoint in collection is null";
            assert timePoint.isFrozen(): new SchedulerException
                    .Builder(SchedulerException.Error.POINT_NOT_FROZEN)
                    .setTimePoint(timePoint).build();
            assert Collections.frequency(timePoints,timePoint) > 1: new SchedulerException //
                    .Builder(SchedulerException.Error.TIME_POINT_EXISTS)
                    .setTimePoint(timePoint);
            this.timePoints.add(timePoint);
        }
    }

    public final List<TimePoint> sort(){
        return TimePointSorter.sort(this.timePoints);
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for(TimePoint timePoint : timePoints)
            output.append(timePoint.toString());
        return output.toString();
    }
}
