import java.util.HashMap;
import java.util.Map;

public class GetIdealPartyLocation{
	public static void main(String[] args){
		int[] xCoords = {0, 1, 2, 3, 4, 5, 6};
		int[] yCoords = {0, 2, 1, 0, 2, 2, 0};
		int[] pops = {3, 5, 10, 5, 5, 5, 5};
		int[] idealLocation = getIdealPartyLocation(xCoords, yCoords, pops);
		System.out.println("[" + idealLocation[0] + "," + idealLocation[1] + "]");
	}

	/**
	 *
	 * @param xCoords x-coordinates of cities
	 * @param yCoords y-coordinates of cities
	 * @param pops population of cities
	 * @return the ideal location where the x-coordinate is stored in index 0 and the y-coordinate in index 1
	 */
	public static int[] getIdealPartyLocation(int[] xCoords, int[] yCoords, int[] pops){
		double totalPop = 0; //total population
		int minX = 0; //lowest x-coordinate in the map
		int minY = 0; //lowest y-coordinate in the map
		int maxX = 0; //highest x-coordinate in the map
		int maxY = 0; //highest x-coordinate in the map
		Map<Integer, Integer> xPopMap = new HashMap<>(); //maps x-coordinate to pop that resides there
		Map<Integer, Integer> yPopMap = new HashMap<>(); //maps y-coordinate to pop that resides there
		for(int i = 0; i < xCoords.length; i++){
			totalPop += pops[i]; //increment total population by population of current city
			int previousXPop = xPopMap.getOrDefault(xCoords[i],0); //get previous population mapped from x
			xPopMap.put(xCoords[i], previousXPop + pops[i]); //update x-coordinate to population mapping
			int previousYPop = xPopMap.getOrDefault(yCoords[i],0); //get previous population mapped from y
			yPopMap.put(yCoords[i], previousYPop + pops[i]); //update y-coordinate to population mapping
			if(xCoords[i] < minX){
				minX = xCoords[i];
			}
			if(yCoords[i] < minY){
				minY = yCoords[i];
			}
			if(xCoords[i] > maxX){
				maxX = xCoords[i];
			}
			if(yCoords[i] > maxY){
				maxY = yCoords[i];
			}
		}
		int halfPop = (int) Math.ceil(totalPop / 2); //round up decimal because halfPop needs to be reached or exceeded
		int idealX = getIdealCoord(minX, maxX, halfPop, xPopMap);
		int idealY = getIdealCoord(minY, maxY, halfPop, yPopMap);
		return new int[]{idealX, idealY};
	}

	/**
	 * gets the ideal coordinate along a dimension
	 *
	 * @param minCoord the minimum possible value of a coordinate in the dimension at hand
	 * @param maxCoord the maximum possible value of a coordinate in the dimension at hand
	 * @param halfPop half the total population (rounded up)
	 * @param popMap the coordinate-to-population map of the dimension at hand
	 * @return the ideal coordinate along the dimension at hand or -1 if no such coordinate exists
	 */
	private static int getIdealCoord(int minCoord, int maxCoord, int halfPop, Map<Integer, Integer> popMap){
		int popSoFar = 0;
		for(int coord = minCoord; coord <= maxCoord; coord++){
			popSoFar += popMap.getOrDefault(coord, 0);
			if(popSoFar >= halfPop){ //once half the population is reached or exceeded then this is ideal coordinate
				return coord;
			}
		}
		return -1;
	}
}