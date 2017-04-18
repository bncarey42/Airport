# Airport
Airport simulates arrivals and departures to and from a one runway airport for a given input time frame. 

Airport

Airport is the driver for the simulation and contains the package’s main method. It gathers the following input from the user: 
+ main(String[] args) : void
	INPUT
	- runTime : an integer – the amount of time to run the simulation for
	- timeToLand : an integer – the amount of time it takes a plane to land
	- timeToTakeOff : an integer – the amount of time it takes a plane to take off
	- avgBetweenArrivals : an integer – the average time between arrivals to land
	- avgBetweenDepartures : an integer – the average time between departures
	- maxTimeInLanding : an integer – the max amount of time a plane can stay in the air waiting to land
Airport then calls Runway passing it the gathered information which runs the simulation. 
After the simulation completes Airport then displays the results of the simulation and asks if the user would like to run the simulation again.


Runway executes the simulation of planes arriving and departing and also has methods to return the results of the simulation. 

Runway
- departures : Queue <Plane>
- arrivals : Queue <Plane>
- runTime : int
- timeToLand : int
- timeToTakeOff : int
- avgBetweenArrivals : int
- avgBetweenDepartures : int
- maxTimeInLanding : int
- inUse() : int
- numArrivals : int
- numDepartures : int
- takeOffs : int
- landings : int
- crashes : int
+ Runway(runTime, timeToLand, timeToLand, timeToTakeOff, avgBetweenArrivals, avgBetweenDepartures, maxTimeInLanding) : Constructor 
+ start() : void
+ queueDeparture(Plane) : void
+ queueArival(Plane) : void
+ getTakeOffs() : int
+ getLandings() : int
+ getCrashes() : int
+ reset() : void
It’s fields are as follows: 

	FIELDS
	- departures : a Queue of Planes – the planes waiting to take off
	- arrivals : a Queue of Planes – the planes waiting to land
	- runTime : an integer – the amount of time to run the simulation for
	- timeToLand : an integer – the amount of time it takes a plane to land
	- timeToTakeOff : an integer – the amount of time it takes a plane to take off
	- avgBetweenArrivals : an integer – the average time between arrivals to land
	- avgBetweenDepartures : an integer – the average time between departures
	- maxTimeInLanding : an integer – the max amount of time a plane can stay in the air waiting to land
	- inUse : an integer – initialized to the time it takes to land or take off when a plane is landing 							or taking off and is decremented for the duration of the time it takes to land or take off 				to simulate the runway being in use
	- numArrivals : an integer – the number of planes that arrive to land
	- numDepartures : an integer – the number of planes that prep to depart
	- takeOffs : an integer – the number of plans to take off during the simulation
	- landings : an integer – the number of plans to land during the simulation	
	- crashes : an integer – the number of plans to crash during the simulation	

The logic implemented in the methods of Runway facilitate the simulation of the activities of the airport for the given amount of time. Implementation logic is as follows: 
	METHODS
	+ Runway(runTime, timeToLand, timeToLand, timeToTakeOff, avgBetweenArrivals, 							avgBetweenDepartures, maxTimeInLanding) – This is the constructor for the class and 				initializes the run time, time  it takes to land and take off, average time between arrivals and 				departures, and the max time a plane can spend in the air waiting to land per the values passed 			to it in the Airport class
	+ start() : returns void – start() runs the simulation and is called by the main method in Airport after the user 				input has been captured. Logic is as follows:		

		for( clock = 0 ; clock < the amount of time input to run the simulation ; clock ++ ){
			if( a plane arrives to land ){
				queue the Plane arriving in the arrivals queues and increment numArrivals 
			}
			if( a plane arrives to take off ){
				queue the Plane departing in the departures queue and increment numDepartures 
			}
			if( the runway is not in use ){
				if( there are planes waiting to land ) {
					dequeue the next Plane in the arrivals queues 
					determine if said Plane has crashed while waiting				
				else if ( there are planes waiting to depart ) {
					dequeue the next Plane in the departures queues 
				}
			}
		}
	+ queueDeparture(Plane) : returns void – enqueues a plane to the departures queue and increments 						         numDepartures
	+ queueArrival(Plane) : returns void – enqueues a plan to the arrivals queue and increments numArrivals
	+ getNumArrivals : returns integer – returns the total number of planes that arrive to land
	+ getNumDepartures : returns integer – returns the total number of planes that preped to depart
	+ getTakeOffs() :  returns integer – returns the number of successful take offs
	+ getLandings() : returns integer – returns the number of successful landings
	+ getCrashes() :  returns integer – returns the number of crashes
	+ reset() : returns void – resets all values to their default values to run the simulation again




The Plane class simulates a plane that is either waiting to land, waiting to take off, landing, or taking off. It’s fields store the time it entered its respective queue and how long it spends in that queue. The methods land and takeOff both return a boolean to indicate a successful landing/takeOff or a crash(if the Plane is landing).

|Plane
- timeArrived : int
- timeInQueue : int
+ Plane(timeArived) : Constructor
+ land(timeInQueue) : boolean
+ takeOff(timeInQueue) : void


	FILEDS
	 - timeArrived : an integer – the time the plane arrived into it’s respective queue
	- timeInQueue : an integer – the amount of time the plane spends in the queue
	METHODS
	+ Plane(timeArrived) : this is the constructor for the Plane class. It accepts the time the plane arrived in it’s 					queue and initializes the timeArrived field
	+ land(timeInQueue, maxTimeLanding) : returns boolean – accepts the amount of time the plane has been in it’s 						    queue and the max time a plane can remain in the air while waiting to 						    land. If timeInQueue is greater than maxTimeLanding then the return 						    is false indicating that the plane has crashed.
	+ takeOff(timeInQueue) : returns boolean – accepts the amount of time the plane has been in its respective 							    queue. This method returns a boolean indicating if the take off has							     been successful.
