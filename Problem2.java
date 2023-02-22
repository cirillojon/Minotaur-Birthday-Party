import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Problem2
{
    public static void main(String[] args) 
    {
        //initialize semaphore to allow only 1 guest at a time
        Semaphore showroomSemaphore = new Semaphore(1);

        //get number of guests from user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of guests: ");
        int numGuests = scanner.nextInt();
        scanner.close();

        long startTime = System.nanoTime();

        // create threads for guests
        Thread[] guestThreads = new Thread[numGuests];
        for (int i = 0; i < numGuests; i++) 
        {
            guestThreads[i] = new Thread(new Guest(i+1, showroomSemaphore));
            guestThreads[i].start();
        }

        // wait for all guest threads to complete
        for (Thread thread : guestThreads) 
        {
            try 
            {
                thread.join();
            } 
            catch (InterruptedException e) 
            {
                System.out.println("An error occured");
            }
        }

        // output runtime
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // convert to milliseconds
        System.out.println("Total simulation runtime: " + duration + " ms");
    }
}

class Guest implements Runnable 
{
    private int guestId;
    private Semaphore showroomSemaphore;

    public Guest(int guestId, Semaphore showroomSemaphore) 
    {
        this.guestId = guestId;
        this.showroomSemaphore = showroomSemaphore;
    }

    public void run() 
    {
        boolean showroomAvailable = true;
        try 
        {
            while (true) 
            {
                // check if the showroom is available
                if (showroomAvailable) 
                {
                    // acquire the semaphore to enter the showroom
                    showroomSemaphore.acquire();

                    //set the showroom availability to BUSY
                    showroomAvailable = false;

                    //guest enters the showroom
                    System.out.println("Thread/Guest " + guestId + " enters");

                    Thread.sleep(1); // simulate time spent in showroom

                    //guest exits the showroom
                    System.out.println("Thread/Guest " + guestId + " leaves");

                    // set the showroom availability to AVAILABLE
                    showroomAvailable = true;

                    // release the semaphore
                    showroomSemaphore.release();
                    break;
                } 
                else 
                {
                    // wait for some time before trying again
                    Thread.sleep(50);
                }
            }
        } 
        catch (InterruptedException e) 
        {
            System.out.println("An error occured");
        }
    }
}
