package tests.utils;

public class Verifier {

    public static void assertEquals(String actual, String expected){
        // Handle exception cases for data
        if ((!actual.equals(expected))){
            throw new RuntimeException("[AssertionError] Expecting " + actual + " to be equal "+ expected);
        }

    }


}
