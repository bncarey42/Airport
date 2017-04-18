/**
 * Created by ben on 4/9/2017.
 */
public class Plane{

    private int timeArrived;
    private int timeInQueue;
    private int timeBeforeCrash;


    /**
     * Constructor for Plane sets time arrived with the first value
     * passed as well as timeBeforeCrash with the second
     * @param clock the planes arrival time
     * @param maxTimeInLanding the max amount of time a plane can
     *                         wait in line to land
     */
    public Plane(int clock, int maxTimeInLanding)
    {
        timeArrived = clock;
        timeBeforeCrash = maxTimeInLanding;
    }


    /**
     * Simulates the plane landing returns true if successful
     * of false if the plan crashed while waiting to land
     * it is assumed that if the plane is allowed to start landing
     * at the last possible minute that the plane will be able to
     * glide into a landing. At this time timeInQueue is also calculated
     * @param currentTime the time the plane was dequeued to land
     * @return true if the plane successfully landed or false if the
     *         plane is now a burning heap in the fields near the runway
     */
    public boolean land(int currentTime){
        System.out.println("A plane has been approved to land at " + currentTime);
        timeInQueue = currentTime - timeArrived;
        return timeBeforeCrash >= timeInQueue;
    }

    /**
     * As a plane cannot fail taking off no value is returned.
     * At this time timeInQueue is also calculated
     * @param currentTime the time the plane was dequeued to take off
     */
    public void takeOff(int currentTime){
        System.out.println("A Plane has debarked at " + currentTime);
        timeInQueue = currentTime - timeArrived;
    }


    /**
     * returns the amount of time you kept the passengers of this
     * plane waiting in some sort of line
     * @return the amount of time this plane was in line
     *         to either land or take off
     */
    public int getTimeInQueue(){
        return timeInQueue;
    }

    public int getTimeArrived(){
        return timeArrived;
    }
}
