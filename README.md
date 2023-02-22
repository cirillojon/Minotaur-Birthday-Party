# Minotaur-Birthday-Party

This program is written in java (openjdk 17.0.4.1)
To run the code use the following commands: 
"javac Problem1.java" "java Problem1"
"javac Problem2.java" "java Problem2"

Each program will request the user in input a N number of guests
Then the program will depict each guest entering and exiting through print statements
Lastly, the total runtime of the program will be outputted.

#1
The approach I used to solve problem 1 is to:
-Each guest enters and exits the labyrinth (where only one guest can enter the labyrinth at a time)
-the others must wait until the previous guest exits. 
This is achieved using a Semaphore object, which allows a limited number of threads to access a shared resource. 
In this case, the Semaphore is used to limit the number of guests inside the labyrinth to one at a time.
To ensure correcteness: 
The code tracks whether each guest has visited the labyrinth and waits until all guests have visited before ending. 
This guarantees that every guest has had a chance to enter the labyrinth and that no guest has been skipped.

#2
I chose to implement the second strategy.
A Semaphore is used again to limit the number of guests allowed in the showroom to only one at a time. 
The Semaphore's acquire() method is called by the guest thread before entering the showroom, 
and the release() method is called upon exiting. A Boolean variable is used to track the showroom's availability. 
If the showroom is busy, the guest thread waits for some time before trying again. 
This guarantees that only one guest can enter the showroom at a time and that no two guests can enter the showroom simultaneously. 

This implementation ensures mutual exclusion, meaning that the shared resource (showroom) is accessed only by one thread at a time, avoiding race conditions and deadlock.

Advantages / Disadvantages of strategies: 
Strategy 1 works well when the room becomes very fast and there is not a long waiting time. However, in other cases it can lead to an unnecessarily high CPU utilization.
Strategy 2 helps when the time spent in the room in longer, but if the waiting time is not optimized, it can cause longer waiting than is necessary. 
Strategy 3 improves upon strategy 2 in that there is no threads/guests competing to get in the room, but can lead to more delays if the wait time is under / over
In conclusion, the strategy that should be chosen is dependent on how long each guest is going to spend in the room.

