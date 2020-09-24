package scheduler;

import java.util.ArrayList;

public class Dependency {
    private final TimePoint previous;
    private final long duration;
    

    Dependency(TimePoint previous, long duration){
        assert previous != null : "Timepoint cannot be null"; //makes sure that inputs aren't null
        this.previous = previous;
        this.duration = duration;
    }
    Dependency(TimePoint previous){
        this(previous,0);
    }
    public final TimePoint getPrevious(){
        return this.previous;
    }

    public final long getDuration(){
        return this.duration;
    }

    @Override
    public String toString() {
        return "Previous Timepoint: " + this.getPrevious() + " Duration" + this.getDuration();
    }

}
