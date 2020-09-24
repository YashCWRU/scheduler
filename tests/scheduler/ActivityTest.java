package scheduler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    //Testing create for Activity
    void create() {
        //Throws null pointer if description is null
        assertThrows(NullPointerException.class, () ->{Activity.create(1, new ActivityGroup(),null);} );
        //Throws null pointer if
        assertThrows(NullPointerException.class, () ->{Activity.create(1,null,"hi");} );
        assertThrows(SchedulerException.class, () ->{Activity.create(-2,new ActivityGroup(),"hi");} );
    }

    @Test
    void freeze() {
    }

    @Test
    //Passes if a null pointer exception is throw when setting dependency for activity is null
    void dependency() throws SchedulerException {
        assertThrows(NullPointerException.class, () ->{
            Activity lol = null;
            lol = Activity.create(1, new ActivityGroup(),"hi");
            lol.dependency(null);
        } );
    }

    @Test
    void addDependency() throws SchedulerException {
        assertThrows(NullPointerException.class, () ->{
            Activity lol = null;
            lol = Activity.create(1, new ActivityGroup(),"hi");
            lol.dependency(null);
        } );
    }
}