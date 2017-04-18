import java.util.Scanner;


/**
 * Created by ben on 4/9/2017.
 */
public class Airport{

    private static int runtime, timeToLand, timeToTakeOff, maxTimeInLanding;

    private static double probArrivals, probDepartures;

    private static Runway simulate;

    public static void main(String[] args){

        String cont;
        Scanner in = new Scanner(System.in);

        welcome();

        System.out.println("Welcome to Facsimile Airport here we will" +
                " simulate arrivals and departures to and from a one runway" +
                " airport for a given input time frame");
        do{
            input();

            //RUN SIMULATION
            simulate = new Runway(runtime, timeToLand, timeToTakeOff,
                    probArrivals, probDepartures, maxTimeInLanding);

            simulate.start();

            output();

            System.out.println();
            System.out.print("WOULD YOU LIKE TO RUN THE SIMULATION AGAIN? (Y/N): ");
            cont = in.nextLine();

            if(cont.equalsIgnoreCase("Y")){

            }
            else{
                System.out.println("Thanks for flying the facsimile airlines!");
                System.exit(0);
            }

            System.out.println();
        } while(cont.equalsIgnoreCase("Y"));
    }

    private static void welcome(){

        Scanner in = new Scanner(System.in);

        System.out.println(
                "                  WELCOME TO THE FACSIMILE AIRPORT \n\n" +


                        "                                |\n" +
                        "                          --====|====--\n" +
                        "                                |  \n" +
                        "                            .-\"\"\"\"\"-. \n" +
                        "                          .'_________'. \n" +
                        "                         /_/_|__|__|_\\_\\\n" +
                        "                        :'-._       _.-':\n" +
                        "   ,--------------------|    `-. .-'    |--------------------,\n" +
                        "    ``\"\"--..__    ___   :       '       :   ___    __..--\"\"``\n" +
                        "              `\"-// \\\\.._\\             /_..// \\\\-\"`\n" +
                        "                 \\\\_//    '._       _.'    \\\\_//\n" +
                        "                  `\"`        ``---``        `\"`"
        );

        System.out.println();
        System.out.print("WOULD YOU LIKE TO RUN THE SIMULATION? (Y/N): ");
        if(in.nextLine().equalsIgnoreCase("Y")){

        } else{
          System.exit(0);
        }

        System.out.println();
    }

    private static void input(){

        Scanner in = new Scanner(System.in);
        System.out.println();

        //GET INPUT CONDITIONS FROM THE USER
        System.out.print("Please input the amount of time IN HOURS to run the simulation for: ");
        runtime = in.nextInt() * 60;
        System.out.print("Please input the amount of time IN MINUTES it takes a plane to land: ");
        timeToLand = in.nextInt();
        System.out.print("Please input the amount of time IN MINUTES it takes a plane to take off: ");
        timeToTakeOff = in.nextInt();
        System.out.print("Please input the probability of a plane arriving to land as a decimal: ");
        probArrivals = in.nextDouble();
        System.out.print("Please input the probability of a plane queueing to depart (take off) as a decimal: ");
        probDepartures = in.nextDouble();
        System.out.print("Please input the max amount of time IN MINUTES a plane " +
                "can stay in the air waiting to land: ");
        maxTimeInLanding = in.nextInt();

    }

    private static void output(){
        System.out.println();

        //PRINT OUT RESULTS
        System.out.println("In the " + runtime/60 + " hours simulated: ");
        System.out.println(simulate.getTakeOffs() + " planes took off");
        System.out.println(simulate.getLandings() + " planes landed");
        System.out.println(simulate.getCrashes() + " planes crashed and burned" +
                ((simulate.getCrashes() > 0) ? "... oh the humanity" : ""));
        System.out.println();
        System.out.printf("Planes waited an average of "
                + "%.2f  minutes to land \n", simulate.getAvgTimeToLand());
        System.out.printf("Planes waited an average of "
                 + "%.2f minutes to take off", simulate.getAvgTimeToTakeOff());
    }

}
