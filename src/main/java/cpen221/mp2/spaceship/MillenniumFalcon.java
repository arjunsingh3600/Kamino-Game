package cpen221.mp2.spaceship;

import cpen221.mp2.controllers.GathererStage;
import cpen221.mp2.controllers.HunterStage;
import cpen221.mp2.controllers.Spaceship;
import cpen221.mp2.graph.ImGraph;
import cpen221.mp2.models.GameModel;
import cpen221.mp2.models.Link;
import cpen221.mp2.models.Planet;
import cpen221.mp2.models.PlanetStatus;
import cpen221.mp2.util.Heap;

import java.util.*;

/**
 * An instance implements the methods needed to complete the mission.
 */
public class MillenniumFalcon implements Spaceship {
    long startTime = System.nanoTime(); // start time of rescue phase

    /**
     * Starts hunter phase of Kamino game,
     * Ship uses depth-first hunt algorithm to follow the strongest signal to Kamino.
     **/
    @Override
    public void hunt(HunterStage state) {
        // TODO: Implement this method
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> depthStack = new Stack<Integer>();

       findKamino(state,visited,depthStack);
    }
    /**
    A recrusive helper function for hunt, finds Kamino.
    @param: depthStack - a stack of planets for DFS
     @param: visitedplanets - a set of visited planets
     */
    private void findKamino(HunterStage state,HashSet<Integer> visP,Stack<Integer> dfsP){
        final int currId = state.currentID();
        int nextId = currId;
        double maxSignal =-2;

        visP.add(currId);
        dfsP.add(currId);


        if(!state.onKamino()){
            PlanetStatus neighbours[] = state.neighbors();
            // iterate through neighbours and get next planet
            for(int i=0;i<neighbours.length;i++){
                if(neighbours[i].signal()>maxSignal && !visP.contains(neighbours[i].id())){
                    nextId = neighbours[i].id();
                    maxSignal = neighbours[i].signal();
                }

            }
            // check if there are any valid neighbours to go to
            if(nextId != currId){
                state.moveTo(nextId);
                findKamino(state,visP,dfsP);

            }
            else{ // if not go to previous planet
               int temp= dfsP.pop();

                state.moveTo(dfsP.pop());
                findKamino(state,visP,dfsP);
            }
     }

    }

    /**
     * Gather stage of Kmaino game.
     * No optimization, The Millennium Falcon returns to earth on the shortest path possible,
     **/
    @Override
    public void gather(GathererStage state) {
        ImGraph<Planet, Link> graph = state.planetGraph();
        List<Planet> shortest = graph.shortestPath(state.currentPlanet(), state.earth());
        shortest.remove(state.currentPlanet());
        for(Planet p:shortest){
            state.moveTo(p);
        }
    }

}
