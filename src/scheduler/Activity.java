package scheduler;


import java.util.EnumMap;
import java.util.Objects;
import java.util.Set;

import static scheduler.SchedulerException.Error.INVALID_DEPENDENCY;

public final class Activity {
    private final ActivityGroup activities;
    String description;
    long duration;
    boolean frozen;
    private final EnumMap<TimePoint.Side, TimePoint> timePointEnumMapMap;

    public Activity(ActivityGroup activities, String description, long duration) {
        this.activities = activities;
        this.description = description;
        this.duration = duration;
        this.timePointEnumMapMap = new EnumMap<>(TimePoint.Side.class);
        TimePoint startPoint = new TimePoint(this, TimePoint.Side.BEGIN);//start timepoint
        TimePoint endPoint = new TimePoint(this, TimePoint.Side.END); //end timepoint
        //adding timepoints to enum map
        timePointEnumMapMap.put(startPoint.getSide(),startPoint);
        timePointEnumMapMap.put(endPoint.getSide(),endPoint);
        //adding startpoint as a previous to endpoint
        endPoint.addPrevious(startPoint, duration);
    }

    public String getDescription() {
        return description;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isFrozen() {
        return frozen;
    }

    final TimePoint extremePoint(TimePoint.Side side){
        return timePointEnumMapMap.get(side);
    }

    public final Set<Dependency> dependency(TimePoint.Side side){
        Objects.requireNonNull(side);
        return extremePoint(side).getDependencies();
    }

    //TODO: throw custom exceptions
    public static final Activity create(long duration, ActivityGroup activities, String description) throws SchedulerException {
        //PRECONDITIONS
       if(duration <= 0)
           throw new SchedulerException
                .Builder(SchedulerException.Error.INVALID_DURATION).build();
        Objects.requireNonNull(activities);
        if(Objects.isNull(description))
            description = "";
        return new Activity(activities, description, duration);
    }

    public final void freeze(){
        frozen = true;
        extremePoint(TimePoint.Side.BEGIN).freeze();
        extremePoint(TimePoint.Side.END).freeze();
        activities.addTimePoints(timePointEnumMapMap.values()); //adds all map values to activity group
    }
    public final boolean addDependency(TimePoint.Side side, TimePoint other) throws SchedulerException {
        //Precondition
        Objects.requireNonNull(activities, "Activity cannot be null");
        Objects.requireNonNull(description, "Description cannot be null");
        if(isFrozen())
            throw new SchedulerException
                    .Builder(INVALID_DEPENDENCY)
                    .setTimePoint(extremePoint(side))
                    .setDuration(0)
                    .setTimePoint(other).build();

        return extremePoint(side).addPrevious(other); //adds other time point as previous timepoint to the current side.
    }

    @Override
    public String toString(){
        return "ActivityGroup: " + activities.toString()+  "\n"
                +"Description: " + description + "\n"
                +"Frozen state: " + frozen + "\n";
    }

}
