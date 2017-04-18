/**
 * Created by ben on 4/9/2017.
 */
public class Runway{

    private int runtime;                    //the amount of time the simulation is to run
    private int timeToLand;                 //the time it takes a plane to land
    private int timeToTakeOff;              //the time it takes a plane to take off
    private double probArrivals;               //the probability of an arrivals (to land)
    private double probDepartures;             //the probability of a departures (to take off)
    private int maxTimeInLanding;           //the longest a plane can stay in the landing queue
                                            // without running out of fuel

    private LinkedQueue<Plane> arrivals;          //queue of planes to land
    private LinkedQueue<Plane> departures;        //queue of planes to take off
    private int inUse;                            //indicator of the amount of time left until the
                                                  //    runway is available for the next plane
                                                  //    to arrive or depart

    private int numArrivals;                //total number of planes arriving to land
    private int numDepartures;              //total number of planes arriving to take off
    private int sumTotalTimeToLand;         //running total of time in queue to land
    private int sumTotalTimeToTakeOff;      //running total of time in queue to take off

    private int takeOffs;                   //total number of successfull take offs
    private int landings;                   //total number of successfull landings
    private int crashes;                    //total number of burning plane wrecks scattered around
                                            // the death trap you call an airport


    /**
     * This is the constructor for the class and initializes the run time,
     * time it takes to land and take off, average time between arrivals and departures,
     * and the max time a plane can spend in the air waiting to land per the values passed
     * to it in the Airport class.
     *
     * NOTE: all values are in minutes
     * @param runtime how long to run the simulation for
     * @param timeToLand the time it takes a plane to land
     * @param timeToTakeOff the time it takes a plane to take off
     * @param probArrivals the probability of an arrival
     * @param probDepartures the probability of a departures
     * @param maxTimeInLanding the max time a plan can spend waiting to land before crashing
     */
    public Runway(int runtime, int timeToLand, int timeToTakeOff,
                  double probArrivals, double probDepartures,
                  int maxTimeInLanding){

        this.runtime = runtime;
        this.timeToLand = timeToLand;
        this.timeToTakeOff = timeToTakeOff;
        this.probArrivals = probArrivals;
        this.probDepartures = probDepartures;
        this.maxTimeInLanding = maxTimeInLanding;

        arrivals = new LinkedQueue<>();
        departures = new LinkedQueue<>();

    }

    /**
     * start() runs the simulation first setting a clock for the amount of time to run
     * the simulation then queueing any plans for arrival or departure and then if
     * the runway is not in use facilitates either the landing or launch of a plane
     * provided it hasn't crashed while waiting
     */
    public void start(){

        for( int clock = 0 ; clock <= runtime ; clock++ ){

            if( Math.random() < probArrivals ){

                //queue the plane arriving in the arrivals queue and increment numArrivals
                queueArrival( new Plane(clock, maxTimeInLanding) );

            }
            if( Math.random() < probDepartures ){

                //queue the Plane departing in the departures queue and increment numDepartures
                queueDeparturePlane( new Plane(clock, maxTimeInLanding) );

            }

            if( !isBusy() ){

                if( !arrivals.isEmpty() ){

                    //allow next plane in landing queue to land if returns
                        // false the plane has crashed
                    Plane landingFlight = arrivals.dequeue();
                    numArrivals++;

                    //determine if said Plane has crashed while waiting
                    if( !landingFlight.land(clock) ) {

                        crashes++;
                        System.out.println("The Plane landing has crashed");

                    }
                    else{

                        System.out.println("The Plane landing will land in " + inUse + " minutes");
                        inUse = timeToLand;
                        landings++;

                    }

                    sumTotalTimeToLand += landingFlight.getTimeInQueue();

                }
				else if ( !departures.isEmpty() ){

                    Plane takeOffFlight = departures.dequeue();
                    numDepartures++;
                    takeOffs++;
                    //allow next plane in departure queue to take off
                    takeOffFlight.takeOff(clock);

                    inUse = timeToTakeOff;

                    sumTotalTimeToTakeOff += takeOffFlight.getTimeInQueue();

                }
            }
            else{

                inUse--;

            }
        }
    }

    /**
     * @param flight the plane to be queued for departure
     */
    public void queueDeparturePlane(Plane flight){

        System.out.println("New plane in departure queue at " + flight.getTimeArrived() );
        departures.enqueue(flight);
        numDepartures++;

    }

    /**
     * Queues plane for to land
     * @param flight the plane to be queued for landing
     */
    public void queueArrival(Plane flight){

        System.out.println("New plane in arrival queue at " + flight.getTimeArrived() );
        arrivals.enqueue(flight);
        numArrivals++;

    }


    /**
     * @return the number of successful take offs
     */
    public int getTakeOffs(){

        return takeOffs;

    }

    /**
     * @return the number of successful landings
     */
    public int getLandings(){

        return landings;

    }

    /**
     * @return the number of crashes
     */
    public int getCrashes(){

        return crashes;

    }

    /**
     * @return the average time planes waited to land in this simulation
     */
    public double getAvgTimeToLand(){

        return (double) sumTotalTimeToLand / numArrivals;

    }

    /**
     * @return the average time planes waited to take off
     */
    public double getAvgTimeToTakeOff(){

        return (double) sumTotalTimeToTakeOff / numDepartures;

    }

    /**
     * @return true if runway is in use false if it is not
     */
    public boolean isBusy(){

        return inUse > 0;

    }


    private class LinkedQueue <T> implements QueueInterface<T>{

        private Node firstNode;
        private Node lastNode;

        public LinkedQueue(){
            firstNode = null;
            lastNode = null;
        }

        @Override
        public void enqueue(T newEntry){

            Node newNode = new Node(newEntry, null);

            if(isEmpty())
                firstNode = newNode;
            else
                lastNode.setNextNode( newNode );

            lastNode = newNode;
        }

        @Override
        public T dequeue(){
            T front = getFront();
            assert firstNode != null;
            firstNode.setData( null );
            firstNode = firstNode.getNextNode();
            if(firstNode == null)
                lastNode = null;
            return front;
        }

        @Override
        public T getFront(){
            return firstNode.getData();
        }

        @Override
        public boolean isEmpty(){
            return (firstNode == null && lastNode == null);
        }

        @Override
        public void clear(){
            while(firstNode != null && lastNode != null){
                lastNode.setNextNode(null);
            }
        }

        private class Node{
            private T data;
            private Node next;

            public Node(T newData, Node nextNode){
                data = newData;
                next = nextNode;
            }

            public T getData(){
                return data;
            }

            public void setData( T newData ){
                data = newData;
            }

            public Node getNextNode(){
                return next;
            }

            public void setNextNode(Node newNext){
                next = newNext;
            }
        }
    }
}

