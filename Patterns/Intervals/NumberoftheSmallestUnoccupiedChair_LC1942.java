package Patterns.Intervals;

public class NumberoftheSmallestUnoccupiedChair_LC1942 {

    
    class Pair{
        int leavingTime;
        int seatNumber;
        Pair(int leavingTime, int seatNumber){
            this.leavingTime = leavingTime;
            this.seatNumber = seatNumber;
        }
    }
    class Tuple{
        int arrivalTime;
        int leavingTime;
        int index;
        Tuple(int arrivalTime, int leavingTime, int index){
            this.arrivalTime = arrivalTime;
            this.leavingTime = leavingTime;
            this.index = index;
        }
    }
    //Time: O(N + (N * log N) + (2 * (N * log N)))
    //Space: O(2 * N)
    public int smallestChair(int[][] times, int targetFriend) {

        int n = times.length;
        PriorityQueue<Pair> occupiedSeats = new PriorityQueue<>((p1,p2) -> Integer.compare(p1.leavingTime, p2.leavingTime));
        PriorityQueue<Integer> availableSeats = new PriorityQueue<>((a1,a2) -> Integer.compare(a1,a2));
        List<Tuple> timeList = new ArrayList<>();

        for(int i=0; i<n; i++){
            availableSeats.offer(i);

            timeList.add(new Tuple(times[i][0], times[i][1], i));
        }

        //Sort an array based on Arrival time
        Collections.sort(timeList, (t1, t2) -> Integer.compare(t1.arrivalTime, t2.arrivalTime));


        for(int i=0; i<n; i++){
            Tuple time = timeList.get(i);

            while(!occupiedSeats.isEmpty() && occupiedSeats.peek().leavingTime <= time.arrivalTime){
                int seat = occupiedSeats.poll().seatNumber;
                availableSeats.offer(seat);
            }

            int availableSeat = availableSeats.poll();

            if(time.index == targetFriend){
                return availableSeat;
            }


            occupiedSeats.offer(new Pair(time.leavingTime, availableSeat));
        }

        return -1;





        
    }
}
