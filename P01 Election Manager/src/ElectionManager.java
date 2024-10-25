//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ElectionManager
// Course: CS 300 Fall 2024
//
// Author: Tristin Yun
// Email: tyun7@wisc.edu
// Lecturer: Dr. Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: The only help I received was from the instructor comment
// @201 on Piazza. But this was a broad hint, not code.
// Online Sources: All code is original
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class has various static methods that help keep track of
 * candidates in an election and determine the winner based on votes
 */
public class ElectionManager {
  /**
   * adds a candidate to a provided 2d array, keeping the array alphabetical sorted
   * 
   * @param candidates an oversize 2d array that is presumed to be alphabetical
   * @param numCandidates the size of the 2d array (# of valid candidates)
   * @param name the name of the candidate to be added
   * @param party the name of the party of the candidate to be added
   * @param numVotes the # of votes of the candidate being added
   * @return the updated size of the 2d array as an integer
   */
  public static int addCandidate(String[][] candidates, int numCandidates, String name,
      String party, int numVotes) {
    
    //stores a copy of the original candidate list so it can
    //be referenced later to adjust the passed candidates list
    String[][] tempCandidates = Arrays.copyOf(candidates, candidates.length);

    // ensures that the array is oversize (has room to add)
    // and that the vote count is not negative
    // and that it does not already contain the candidate
    if (candidates.length > numCandidates && numVotes >= 0
        && containsCandidate(candidates, numCandidates, name, party) == false) {
      if (numCandidates > 0) {
        //iterates through each candidate
        for (int i = 0; i < numCandidates; i++) {
          if ((name).compareTo(candidates[i][0]) < 0) {
            // comes before the element at the index alphabetically

            for (int j = i + 1; j < numCandidates + 1; j++) {
              // fills in the rest of the elements after the insertion
              // index with the information from the elements of the 
              // previous index; essentially bumps all candidates up a spot
              candidates[j] = new String[3];  // CITE: the "= new String[...]" was inspired
                                              // by the hint from @201. Replaces the array with
                                              // an empty one (in case it is null)
              candidates[j][0] = tempCandidates[j - 1][0];
              candidates[j][1] = tempCandidates[j - 1][1];
              candidates[j][2] = tempCandidates[j - 1][2];
            }
            
            //replaces the element at the index where the new
            //candidate fits alphabetically
            candidates[i][0] = name;
            candidates[i][1] = party;
            candidates[i][2] = "" + numVotes;
            
            //returns the size + 1, since a candidate has been added
            return numCandidates + 1;
          }
          if (i == numCandidates - 1) { // comes last alphabetically
            //appends the candidate at the end of the 2d array 
            //(this works since the array is assumed to be oversize)
            candidates[i + 1] = new String[3];
            candidates[i + 1][0] = name;
            candidates[i + 1][1] = party;
            candidates[i + 1][2] = "" + numVotes;

            return numCandidates + 1;
          }
        }
      } else if (numCandidates == 0) { // there are no valid candidates
        candidates[0] = new String[3];
        candidates[0][0] = name;
        candidates[0][1] = party;
        candidates[0][2] = "" + numVotes;

        return numCandidates + 1;
      }
    }
    return numCandidates; //nothing has changed
  }
  
  /**
   * checks if the passed candidates list contains a specific candidate,
   * determined by name and party affiliation
   * 
   * @param candidates an oversize 2d array in alphabetical order
   * @param numCandidates the size of the 2d array (# of valid candidates)
   * @param name the name of the candidate to be detected
   * @param party the name of the party of the candidate to be detected
   * @return true if the candidate is found, false otherwise
   */
  public static boolean containsCandidate(String[][] candidates, int numCandidates, String name,
      String party) {
    //iterates through each candidate
    for (int i = 0; i < numCandidates; i++) {
      //if the party and name match, return true
      if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
        return true;
      }
    }
    return false;
  }

  /**
   * drops a candidate from a provided 2d array, keeping the array alphabetical sorted
   * 
   * @param candidates an oversize 2d array that is presumed to be alphabetical
   * @param numCandidates the size of the 2d array (# of valid candidates)
   * @param name the name of the candidate to be dropped
   * @param party the name of the party of the candidate to be dropped
   * @return the updated size of the 2d array as an integer
   */
  public static int dropCandidate(String[][] candidates, int numCandidates, String name,
      String party) {
    
    String[][] tempCandidates = Arrays.copyOf(candidates, candidates.length);
    
    // verifies that the 2d array actually contains the candidate to be dropped
    if (containsCandidate(candidates, numCandidates, name, party) == true) {
      // iterates through until the candidate is found
      for (int i = 0; i < numCandidates; i++) {
        if (candidates[i][0].equals(name) && candidates[i][1].equals(party)) {
          for (int j = i; j < candidates.length - 1; j++) { //starting at the index where
            //we want to drop a candidate and moving forward
            candidates[j] = new String[3];
            if (tempCandidates[j + 1] != null) { //as long as the array at the current index
              //+1 isnt null, we can replace the current array with it; essentially bumps 
              //all of the candidates down a spot.
                                    
              candidates[j][0] = tempCandidates[j + 1][0];
              candidates[j][1] = tempCandidates[j + 1][1];
              candidates[j][2] = tempCandidates[j + 1][2];
            } else {
              //otherwise, the next array must be null so we replace the current index
              //with null
              candidates[j] = null;
            }
          }
          
          //since we can't use the above loop to update the very last array in the 2d array,
          //since no array follows it, we manually change it to null
          candidates[candidates.length - 1] = null;
          
          //since we dropped a candidate, the size decreases by 1
          return numCandidates - 1;
        }
      }
    }
    
    //nothing has changed
    return numCandidates;
  }

  /**
   * determines the candidate with the least votes; or, if there is a tie,
   * the candidate that comes first alphabetically
   * 
   * @param candidates an oversize 2d array that is presumed to be alphabetical
   * @param numCandidates the size of the 2d array (# of valid candidates)
   * @return a string in the form of "name (party) - numVotes"
   */
  public static String findLowestPollingCandidate(String[][] candidates, int numCandidates) {
    //if there is more than one candidate
    if (numCandidates > 1) {
      //assume that the lowest polling is the very first pokemon in the 2d array.
      //This is quicker for iteration.
      int currentLowestVotes = Integer.valueOf(candidates[0][2]);
      //Index of the pokemon with lowest votes
      int loserIndex = 0;
      //iterates through each pokemon and if their votes is lower than the 
      //current lowest, change the loserIndex and currentLowestVotes to that 
      //of the current pokemon
      for (int i = 1; i < numCandidates; i++) {
        if (Integer.valueOf(candidates[i][2]) < currentLowestVotes) {
          currentLowestVotes = Integer.valueOf(candidates[i][2]);
          loserIndex = i;
        }
      }

      return candidates[loserIndex][0] + " " + "(" + candidates[loserIndex][1] + ")" + " - "
          + currentLowestVotes;
    } else { //if there is just one or less candidates
      return "UNCONTESTED";
    }
  }

  /**
   * determines the candidate with the greatest votes/greatest percentage
   * of toal votes
   * 
   * @param candidates an oversize 2d array that is presumed to be alphabetical
   * @param numCandidates the size of the 2d array (# of valid candidates)
   * @return a string in the form of "name (party) - votePct%"
   */
  public static String findWinner(String[][] candidates, int numCandidates) {
    int currentHighestVotes = 0;
    int winnerIndex = 0;
    int combinedVotes = 0; //total votes of all candidates; used in pct calculation
    //iterates through each pokemon and if their votes is higher than the 
    //current highest, change the winnerIndex and currentHighestVotes to that 
    //of the current pokemon
    for (int i = 0; i < numCandidates; i++) {
      if (Integer.valueOf(candidates[i][2]) > currentHighestVotes) {
        currentHighestVotes = Integer.valueOf(candidates[i][2]);
        winnerIndex = i;
      }
      //Add the vote counts of the pokemons to the combinedVotes
      combinedVotes += Integer.valueOf(candidates[i][2]);
    }
    
    //if the pokemon with the highest votes has more than 50% of all votes
    //then they are the clear majority winner
    if (((double) currentHighestVotes) / combinedVotes * 100 > 50.0) {

      return candidates[winnerIndex][0] + " " + "(" + candidates[winnerIndex][1] + ")" + " - "
          + (double) currentHighestVotes / combinedVotes * 100 + "%";
    } else { //otherwise, this is a contingent election
      return "CONTINGENT";
    }
  }
}
