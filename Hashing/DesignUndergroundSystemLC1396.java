package Hashing;

import java.util.*;

class Tuple {
    int totalTime;
    int count;

    Tuple(int totalTime, int count) {
        this.totalTime = totalTime;
        this.count = count;
    }
}

class Pair {
    String stationName;
    int time;

    Pair(String stationName, int time) {
        this.stationName = stationName;
        this.time = time;
    }
}

public class DesignUndergroundSystemLC1396 {

    Map<String, Map<String, Tuple>> railRoutesMap;
    Map<Integer, Pair> checkInMap;

    public DesignUndergroundSystemLC1396() {

        railRoutesMap = new HashMap<>();
        checkInMap = new HashMap<>();

    }

    public void checkIn(int id, String stationName, int t) {

        checkInMap.put(id, new Pair(stationName, t));

        railRoutesMap.putIfAbsent(stationName, new HashMap<>());

    }

    public void checkOut(int id, String stationName, int t) {

        int userCheckInTime = checkInMap.get(id).time;
        String userCheckInStation = checkInMap.get(id).stationName;

        int timeForTravel = t - userCheckInTime;

        if (!railRoutesMap.get(userCheckInStation).containsKey(stationName)) {
            railRoutesMap.get(userCheckInStation).put(stationName, new Tuple(0, 0));
        }

        Tuple temp = railRoutesMap.get(userCheckInStation).get(stationName);
        Tuple newTemp = new Tuple(temp.totalTime + timeForTravel, temp.count + 1);

        railRoutesMap.get(userCheckInStation).put(stationName, newTemp);

        checkInMap.remove(id);

    }

    public double getAverageTime(String startStation, String endStation) {

        Tuple resultTuple = railRoutesMap.get(startStation).get(endStation);

        int totalTime = resultTuple.totalTime;

        int count = resultTuple.count;

        double result = (double) totalTime;

        if (count > 0) {
            result = result / count;
        }

        return result;

    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */