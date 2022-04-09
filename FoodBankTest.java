// ENSF 409 Final project - Group 62
package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

import java.beans.Transient;

public class FoodBankTest {
private final int CLIENTID = 4;
private final String[] TEST_MEMBERS = {
                "Michie, adult female, Gender: Female",
                "Ewa, adult female, Gender, Female", 
                "Shadow, Age: under eight, Gender: Male",
                "Bubby, adult male, Gender, Male",
                "Mom, adult female, Gender: Female,"
            };
private final String[] TEST_INVENTORY = {"tomatoes", "cabbage", "marmalade", "brownie", "guava"}; 
private final String[] TEST_HAMPER = {"21, Watermelon, large", "23, Roast Beef, one pot", "27, Cereal, one box", "32, Bananas, dozen"};
private final DailyNeeds NEEDS = new DailyNeeds(29, 17, 34, 11);
    
    @Test
    public void testSetGetFamSize(){
        int givenData = 4;
        Members members = new Members();
        members.setFamSize(givenData);
        String actual = members.getFamSize();
        assertEquals("Family size did not match what was expected", givenData, actual);
    }
    
    @Test
    public void testSetGetAdultMale(){
        int givenData = 24;
        Members members = new Members();
        members.setAdultMale(givenData);
        String actual = members.getAdultMale();
        assertEquals("Age did not match what was expected", givenData, actual);
        }    
  
