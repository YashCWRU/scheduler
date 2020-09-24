package scheduler;

public final class SchedulerException extends Exception{

    private static final long serialVersionUID = 2576411339700075538L;
    private final Error error;
    private final TimePoint timePoint;
    private final TimePoint otherTimePoint;
    private final long duration;

    private SchedulerException(Builder builder) {
        error = builder.getError();
        timePoint = builder.getTimePoint();
        otherTimePoint = builder.getOtherTimePoint();
        duration = builder.getDuration();
    }

    public enum Error{
        POINT_FROZEN,
        INVALID_DURATION,
        INVALID_DEPENDENCY,
        TIME_POINT_EXISTS,
        POINT_NOT_FROZEN
    }

    //Getter for error
    public final Error getError(){
        return error;
    }

    public final TimePoint getTimePoint(){
        return timePoint;
    }

    public final TimePoint getOtherTimePoint(){
        return otherTimePoint;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "SchedulerException{" +
                "error=" + error +
                ", timePoint=" + timePoint +
                ", otherTimePoint=" + otherTimePoint +
                '}';
    }

    final static class Builder{
        private final Error error;
        private long duration;
        private TimePoint timePoint;
        private TimePoint otherTimePoint;

        //Constructor that sets the error
        Builder(Error error) {
            this.error = error;
        }
        Error getError() {
            return error;
        }

        long getDuration() {
            return duration;
        }

        TimePoint getTimePoint() {
            return timePoint;
        }

        TimePoint getOtherTimePoint() {
            return otherTimePoint;
        }

        Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        Builder setTimePoint(TimePoint timePoint) {
            this.timePoint = timePoint;
            return this;
        }

        Builder setOtherTimePoint(TimePoint otherTimePoint) {
            this.otherTimePoint = otherTimePoint;
            return this;
        }

        final SchedulerException build(){
            return new SchedulerException(this);
        }

    }
}
