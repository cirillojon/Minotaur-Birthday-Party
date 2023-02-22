import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Problem1
{
    public static void main(String[] args)
    {
    //prompt user for number of guests
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of guests: ");
    int numberOfGuests = scanner.nextInt();
    scanner.close();
    //create semaphore and array to track which guests have visited the labyrinth
    Semaphore semaphore = new Semaphore(1);
    boolean[] guestsVisited = new boolean[numberOfGuests];
        //record start time for performance measurement
        long startTime = System.currentTimeMillis();

    //create threads for each guest and start them
    for (int i = 0; i < numberOfGuests; i++) 
    {
        //define the guestId variable as the current index
        final int guestId = i;
        
        //create a new thread for the current guest
        Thread thread = new Thread(() -> 
        {
            try 
            {
                //keep looping indefinitely
                while (true) 
                {
                    //try to obtain a pass from the semaphore
                    if (semaphore.tryAcquire()) 
                    {
                        //if the guest hasn't visited the labyrinth before, mark them as visited
                        if (!guestsVisited[guestId]) 
                        {
                            guestsVisited[guestId] = true;
                        }
                        //print a message indicating the guest has entered the labyrinth
                        System.out.println("Thread/Guest " + guestId + " enters");
                        
                        //simulate the guest spending time in the labyrinth
                        Thread.sleep(1); 
                        
                        //print a message indicating the guest has exited the labyrinth
                        System.out.println("Thread/Guest " + guestId + " leaves");
                        
                        //release the pass back to the semaphore
                        semaphore.release();
                        
                        //break out of the infinite loop
                        break;
                    } 
                    else 
                    {
                        //if the semaphore doesn't have any passes available, wait for a short time
                        Thread.sleep(1); 
                    }
                }
            } 
            catch (InterruptedException e) 
            {
                //print the stack trace in case of an interruption
                System.out.println("An error occured");
            }
        });
    
    //start the thread for the current guest
    thread.start();
}

        //wait for all guests to visit the labyrinth
        while (!allGuestsVisited(guestsVisited)) 
        {
            try 
            {
                //wait for guests to finish
                Thread.sleep(1); 
            } 
            catch (InterruptedException e) 
            {
                System.out.println("An error occured");
            }
        }

        //record end time for performance measurement and print runtime
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
    }

    //check if all guests have visited the labyrinth
    private static boolean allGuestsVisited(boolean[] guestsVisited) 
    {
        for (int i = 0; i < guestsVisited.length; i++) 
        {
            if (!guestsVisited[i]) 
            {
                return false;
            }
        }
        return true;
    }
}