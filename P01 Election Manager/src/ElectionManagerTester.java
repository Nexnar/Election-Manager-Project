//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ElectionMangerTester
// Course: CS 300 Fall 2024
//
// Author: Tristin Yun
// Email: tyun7@wisc.edu
// Lecturer: Dr. Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NONE
// Online Sources: All code is original; I did copy and paste some of the
// tester method logic that was already provided below
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class is used to test the various methods in the ElectionManager class
 */
public class ElectionManagerTester {

  /**
   * Checks whether the containsCandidate method 
   * will return false for an empty list
   * 
   * @return true if the containsCanddiate method returns false
   * since theres no candidates
   */
  public static boolean testContainsEmpty() {
    // an "empty" candidate list
    String[][] candidateList = {null, null, null, null, null, null};
    int size = 0;
    //details of a candidate
    String targetName = "Raichu";
    String targetParty = "Electric";
    
    //expected is false since there shouldn't be any candidates in this empty list
    boolean expected = false;
    
    //calls the method and stores the boolean
    boolean actual =
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);
    
    if (expected != actual)
      return false;

    return true;
  }

  /**
   * Checks whether the containsCandidate can tell that a candidate
   * list does NOT contain the target candidate
   * 
   * @return true if the containsCandidate method returns false since
   * the candidate isnt in the list
   */
  public static boolean testDoesNotContain() {
    // initializing the arrays and strings
    String[][] candidateList = {{"Bulbasaur", "Grass", "200"}, {"Charmander", "Fire", "203"},
        {"Wartortle", "Water", "169"}, null, null};
    //make a copy to compare against original later
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Blastoise";
    String targetParty = "Water";
    //there shouldnt be a blastoise
    boolean expected = false;

    boolean actual =
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);

    if (expected != actual)
      return false;
    
    //if the updated candidatelist isn't equal to the copy of the
    //original one, then something went wrong
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a candidate has already been
   * added to the race.
   * 
   * NOTE: This method ONLY tests scenarios where the candidate IS PRESENT in the list; situations
   * where the candidate is not present or the list is empty should be tested in the other contains
   * tester methods.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testDoesContain() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    int size = 3;
    String targetName = "Wooper";
    String targetParty = "Water";
    boolean expected = true;

    // (1b) call the method we are testing
    boolean actual =
        ElectionManager.containsCandidate(candidateList, size, targetName, targetParty);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) since THIS method should not modify the array, check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  /**
   * Checks whether the addCandidate method can add a candidate
   * to an empty 2d array
   * 
   * @return true iff all of the expectations we have are met
   */
  public static boolean testAddToEmpty() {
    //Declare variables
    String[][] candidateList = {null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;
    //we expect goldeen to be placed in the first index to 
    //keep the array oversize and compact
    String[][] expectedList = {{"Goldeen", "Water", "5"}, null};
    int size = 0;
    int expected = 1;
    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    
    //if the expected size of the new array (1) 
    //does not match the actual, then there is an error
    if (expected != actual)
      return false;

    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether a new candidate has been
   * added correctly to the race.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testAddToNonEmpty() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String newName = "Goldeen";
    String newParty = "Water";
    int newVotes = 5;

    String[][] expectedList = {{"Goldeen", "Water", "5"}, // alphabetically first, new candidate
                                                          // will be added here
        {"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"}, {"Wooper", "Water", "300"}, null,
        null}; // now only TWO null values in this length-6 array!
    int size = 3;
    int expected = 4;

    // (1b) call the method we are testing
    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) this method modifies the input array; verify that it was modified correctly
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;
  }
  
  /**
   * Verifies if the addCandidate method responds correctly to erroneous inputs
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testAddCandidateErrors() {
    String[][] candidateList = {{"Caterpie", "Bug", "2"}, {"Pidgey", "Normal", "101"},
        {"Rattata", "Dark", "57"}, null, null, null};
    int size = 3;
    
    //declare a duplicate candidate
    String newName1 = "Rattata";
    String newParty1 = "Dark";
    int newVotes1 = 5;
    
    //declares a candidate with negative votes
    String newName2 = "Spearow";
    String newParty2 = "Normal";
    int newVotes2 = -10;

    //the expectedList is the same for both of the above cases; NOTHING should change
    String[][] expectedList = {{"Caterpie", "Bug", "2"}, {"Pidgey", "Normal", "101"},
        {"Rattata", "Dark", "57"}, null, null, null};
    int expected = 3;
    
    int actual1 = ElectionManager.addCandidate(candidateList, size, newName1, newParty1, newVotes1);
    if (expected != actual1)
      return false;
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    int actual2 = ElectionManager.addCandidate(candidateList, size, newName2, newParty2, newVotes2);
    if (expected != actual2)
      return false;
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    return true;
  }
  
  /**
   * Verifies if the addCandidate method prevents a candidate
   * being added if the array is full
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testAddToFull() {
    //declare perfect size array
    String[][] candidateList =
        {{"Caterpie", "Bug", "2"}, {"Pidgey", "Normal", "101"}, {"Rattata", "Dark", "57"},};
    int size = 3;
    String newName = "Spearow";
    String newParty = "Normal";
    int newVotes = 5;
    
    //nothing should change in our expected list
    String[][] expectedList =
        {{"Caterpie", "Bug", "2"}, {"Pidgey", "Normal", "101"}, {"Rattata", "Dark", "57"},};
    int expected = 3;

    int actual = ElectionManager.addCandidate(candidateList, size, newName, newParty, newVotes);
    if (expected != actual)
      return false;
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    return true;
  }

  /**
   * Verifies if the addCandidate method can drop just one candidate
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testDropOnlyCandidate() {
    String[][] candidateList = {{"Pikachu", "Electric", "420"}, null};
    int size = 1;
    String name = "Pikachu";
    String party = "Electric";
    
    //the sole candidate should be dropped and replaced by null
    String[][] expectedList = {null, null};

    //the size should decrease from 1 to 0
    int expected = 0;

    int actual = ElectionManager.dropCandidate(candidateList, size, name, party);

    if (expected != actual)
      return false;
    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    return true;
  }
  
  /**
   * Verifies if the addCandidate method can drop the first candidate
   * on a list and result in a COMPACT OVERSIZE array
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testDropFirstCandidate() {
    String[][] candidateList = {{"Pikachu", "Electric", "420"}, {"Nidoran", "Poison", "200"},
        {"Sandshrew", "Ground", "150"}, null};
    int size = 3;
    String name = "Pikachu";
    String party = "Electric";
    
    //the null should be at the end in order to keep the 2d array compact
    String[][] expectedList =
        {{"Nidoran", "Poison", "200"}, {"Sandshrew", "Ground", "150"}, null, null};
    int expected = 2;

    int actual = ElectionManager.dropCandidate(candidateList, size, name, party);

    if (expected != actual)
      return false;

    if (!Arrays.deepEquals(candidateList, expectedList))
      return false;

    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying whether trying to drop a candidate
   * who is not running in the race correctly has NO effect on the candidate list.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testDropCandidateNotRunning() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "127"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String name = "Goldeen";
    String party = "Water";
    int size = 3;
    int expected = 3;

    // (1b) call the method we are testing
    int actual = ElectionManager.dropCandidate(candidateList, size, name, party);

    // (2) verify that the expected method return value and the actual return value match
    if (expected != actual)
      return false;

    // (2a) sometimes you may want to REPEAT the process with slightly different variables:
    name = "Slowpoke";
    party = "Fire"; // try with a name that's present but a different PARTY; should still not drop
    actual = ElectionManager.dropCandidate(candidateList, size, name, party);
    if (expected != actual)
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  /**
   * Verifies if the findWinner method will respond correctly
   * if there is only one candidate
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testUncontestedWinner() {
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, null};
    int size = 1;
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Slowpoke";
    String expectedParty = "(Water)";
    //the candidate should have 100% of the votes
    double expectedVotePct = 100.0;

    String result = ElectionManager.findWinner(candidateList, size);

    String[] resultPieces = result.split(" "); // get the separate pieces of the string

    if (resultPieces.length != 4)
      return false; // wrong format

    if (!resultPieces[3].endsWith("%"))
      return false; // must have % at end

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name/party

    if (!resultPieces[2].equals("-"))
      return false; // no hyphen

    // do a range check on the calculated vote percentage, since it's not always going to come out
    // exactly the same:
    double actualVotePct =
        Double.valueOf(resultPieces[3].substring(0, resultPieces[3].length() - 1));
    if (Math.abs(actualVotePct - expectedVotePct) > 0.01)
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    //all the tests have passed
    return true;
  }

  /**
   * PROVIDED TESTER METHOD: example test method for verifying the results of an election where one
   * candidate has received a clear majority of the votes cast.
   * 
   * @return false if any of the scenarios we test have results other than what we expect; true ONLY
   *         if all of our expectations are met by the method we are testing
   */
  public static boolean testClearWinner() {

    // (1a) set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, {"Squirtle", "Water", "97"},
        {"Wooper", "Water", "300"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Wooper";
    String expectedParty = "(Water)";
    //divide the votes of the winning candidate by the total votes of all candidates
    double expectedVotePct = 300.0 / (300 + 97 + 3) * 100;
    int size = 3;

    // (1b) call the method we are testing
    String result = ElectionManager.findWinner(candidateList, size);

    // (2) verify that the expected method return value and the actual return value match
    // NOTE: for a String, this takes a little more processing to do sensitively.
    // We expect this result to be "Wooper (Water) - 75.0%" but there may be some weirdness
    // especially with that percentage. See how we do it here:

    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string

    if (resultPieces.length != 4)
      return false; // incorrect formatting
    if (!resultPieces[3].endsWith("%"))
      return false; // no % at the end

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and %

    // do a range check on the calculated vote percentage, since it's not always going to come out
    // exactly the same:
    double actualVotePct =
        Double.valueOf(resultPieces[3].substring(0, resultPieces[3].length() - 1));
    if (Math.abs(actualVotePct - expectedVotePct) > 0.01)
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    // (4) if we have not yet returned false, we can now return true as all tests have passed!
    return true;

  }

  /**
   * Verifies if the findWinner method can produce correct results
   * in a contingent election (no majority winner)
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testContingentElection() {
    // set up the test variables
    String[][] candidateList = {{"Slowpoke", "Water", "60"}, {"Squirtle", "Water", "72"},
        {"Wooper", "Water", "54"}, null, null, null};
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedOutput = "CONTINGENT";
    int size = 3;

    String result = ElectionManager.findWinner(candidateList, size);

    if (!result.equals(expectedOutput))
      return false;

    return true;
  }

  /**
   * Verifies if the findLowestPollingCandidate method will perform
   * correctly if there is only one (or less) candidate
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testUncontestedLowestPolling() {
    //declare test variables
    String[][] candidateList = {{"Slowpoke", "Water", "3"}, null};
    int size = 1;
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedOutput = "UNCONTESTED";

    String result = ElectionManager.findLowestPollingCandidate(candidateList, size);
    
    if (!result.equals(expectedOutput))
      return false;

    return true;
  }
  
  /**
   * Verifies if the findLowestPollingCandidate method can
   * determine the lowest poller given unique vote counts
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testLowestUniqueVoteCount() {
    //declare test variables
    String[][] candidateList = {{"Slowpoke", "Water", "60"}, {"Squirtle", "Water", "7"},
        {"Wooper", "Water", "300"}, null, null, null};
    int size = 3;
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Squirtle";
    String expectedParty = "(Water)";
    int expectedVotes = 7;

    String result = ElectionManager.findLowestPollingCandidate(candidateList, size);

    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string

    if (resultPieces.length != 4)
      return false; // incorrect formatting

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and votes

    // if the actual vote count does not match the expected votes of the lowest poller
    Integer actualVotes = Integer.valueOf(resultPieces[3]);

    if (!(actualVotes == expectedVotes))
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    return true;
  }

  /**
   * Verifies if the findLowestPollingCandidate can determine
   * the lowest poller given two tide candidates (returns the
   * one first alphabetically)
   * 
   * @return true iff all of our expectations are met
   */
  public static boolean testLowestVoteCountTied() {
    String[][] candidateList = {{"Slowpoke", "Water", "200"}, {"Squirtle", "Water", "60"},
        {"Wooper", "Water", "60"}, null, null, null};
    int size = 3;
    String[][] candidateCopy = Arrays.copyOf(candidateList, candidateList.length);
    String expectedName = "Squirtle";
    String expectedParty = "(Water)";
    int expectedVotes = 60;

    String result = ElectionManager.findLowestPollingCandidate(candidateList, size);

    String[] resultPieces = result.split(" "); // get the space-separated pieces of the string

    if (resultPieces.length != 4)
      return false; // incorrect formatting

    if (!resultPieces[0].equals(expectedName) || !resultPieces[1].equals(expectedParty))
      return false; // wrong name or wrong party

    if (!resultPieces[2].equals("-"))
      return false; // forgot the "-" between party and votes

    // if the actual vote count does not match the expected votes of the lowest poller
    Integer actualVotes = Integer.valueOf(resultPieces[3]);

    if (!(actualVotes == expectedVotes))
      return false;

    // (3) this scenario should NOT modify the input array; check it against a copy we made
    if (!Arrays.deepEquals(candidateList, candidateCopy))
      return false;

    return true;
  }

  /**
   * PROVIDED MAIN METHOD to manage the tester methods above.
   * 
   * We're getting a little esoteric here to take advantage of loops to keep the code short; each
   * pass through the loop could also be written as follows:
   * 
   * boolean singleTest = testMethodCall(); allPass &= singleTest;
   * System.out.println("testMethodCall : " + singleTest);
   * 
   * @throws NoSuchMethodException                       if you spell a method name incorrectly
   * 
   *                                                     And a couple of other "checked" exceptions
   *                                                     that should never happen with our usage
   *                                                     here:
   * @throws IllegalAccessException
   * @throws java.lang.reflect.InvocationTargetException
   */
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
      java.lang.reflect.InvocationTargetException {
    boolean allPass = true, singlePass = true;
    String printFormat = "%-29s: %s\n";

    // NOTE TO STUDENTS: If you create any additional tests for any of the methods in
    // ElectionManager, add their names to the appropriate array below!
    String[] containsTests = {"testContainsEmpty", "testDoesNotContain", "testDoesContain"};
    String[] addTests =
        {"testAddToEmpty", "testAddToNonEmpty", "testAddCandidateErrors", "testAddToFull"};
    String[] dropTests =
        {"testDropOnlyCandidate", "testDropFirstCandidate", "testDropCandidateNotRunning"};
    String[] winTests = {"testUncontestedWinner", "testClearWinner", "testContingentElection"};
    String[] lowTests =
        {"testUncontestedLowestPolling", "testLowestUniqueVoteCount", "testLowestVoteCountTied"};

    String[][] testNames = {containsTests, addTests, dropTests, winTests, lowTests};

    // NOTE TO STUDENTS: this for-loop is moving through the method names we've added to the 2D
    // array testNames and attempting to call methods with those names from this tester
    // (specifically line 286 here). See Java's reflection framework for more details!
    for (String[] testSet : testNames) {
      for (String name : testSet) {
        singlePass = (boolean) ElectionManagerTester.class.getDeclaredMethod(name).invoke(null);
        allPass &= singlePass;
        System.out.printf(printFormat, name, singlePass);
      }
      System.out.println();
    }

    System.out.println("ALL TESTS: " + allPass);

  }

}
