package scheduler;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TimePoint {

    public enum Side{
        BEGIN, END
    }

    private final Set<Dependency> dependencies;
    private boolean frozen;
    private final Activity activity;
    private final Side side;
    private final String description;


    TimePoint(Activity activity, Side side){
        dependencies = new HashSet<>();
        frozen = false; //Sets frozen state to false by default
        this.activity = activity;
        this.side = side;
        this.description = activity.getDescription() + getSide();
    }

    final boolean addPrevious(TimePoint previousTimePoint, long duration){
        //PREConditions
        assert !isFrozen(): newException(previousTimePoint, duration, SchedulerException.Error.POINT_FROZEN);
        assert duration >= 0: newException(previousTimePoint, duration, SchedulerException.Error.INVALID_DURATION);

        return dependencies.add(new Dependency(previousTimePoint, duration));
    }
    final boolean addPrevious(TimePoint previousTimePoint){
        return addPrevious(previousTimePoint,0); //dont need assert as it used this method
    }
    public final void freeze(){
        frozen = true;
    }
    public final boolean isFrozen(){
        return frozen;
    }

    public Set<Dependency> getDependencies() {
        return new HashSet<>(dependencies); //Returns a copy of the dependencies
    }

    public final Set<TimePoint> previousTimePoints(){
        return dependencies.stream()
                .map(Dependency::getPrevious)
                .collect(Collectors.toSet());
    }

    public final int inDegree(){
        return dependencies.size();
    }

    public final boolean isIndependent(){
        return dependencies.isEmpty();
    }

    public final String toSimpleString(){
        return "Frozen state: " + frozen;
    }

    public Activity getActivity() {
        return activity;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for(Dependency dependency : dependencies){
            output.append(dependencies.toString());
        }
        return toSimpleString() + "\n" + "Dependencies: " + output.toString() + "\n" +
        "Activity Description: " + description + "\n" + "Side: " + side;
    }

    //Helper Method that makes a SchedulerException depending on the error
    private SchedulerException newException(TimePoint previousTimePoint, long duration, SchedulerException.Error error){
        return new SchedulerException.Builder(error)
                .setTimePoint(this)
                .setDuration(duration)
                .setOtherTimePoint(previousTimePoint).build();
    }
}
