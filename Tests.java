/**
Samira Khan <a href="mailto: samira.khan@ucalgary.ca">
Ammar Elzeftawy <a href="mailto: ammar.elzeftawy1@ucalgary.ca">
Michele Pham <a href="mailto: michele.pham@ucalgary.ca">
Sayma Haque <a href="mailto: sayma.haque@ucalgary.ca">
@version 1.5
@since 1.0
*/

package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import java.sql.*;
import java.io.*;
import java.util.*;


import java.beans.Transient;

public class FoodBankTest {
    // NutritionalInfo and DailyNeeds are not tested as they are classes that solely deal with SQL 
    private Hamper hamper = new Hamper();
    private DailyNeeds needs = new DailyNeeds("jdbc:mysql://localhost/food_inventory","student","ensf");
    private GUIFoodBank gui = new GUIFoodBank();
    

    // tests for OrderForm
        // test if exist method works
    @Test
    public void testExistOrderForm(){
        OrderForm form = new OrderForm();
        // delete any existing file
        form.deleteFile();
        form.printOrder();
        assertTrue("File was not created.", form.Exist());
        
        // delete new file
        form.deleteFile();
    }
    
    // tests for Hamper
        // test if OGRequest formats information correctly
    @Test
    public void testFamilyMemebersOneHamper(){
        String actual = hamper.OgRequest(1, "Child under 8", 3,"Child over 8", 2, "Adult Female", 1, "Adult Male", 1);
        String given = "Hamper Order Form\n" + "Original Request\n" + "\nHamper 1: 1 Child under 8, 3 Child over 8, 2 Adult Female, 1 Adult Male" +"\n";
        assertEquals("Hamper did not return correctly formatted information.", given, actual);
    }

        // test if OGRequest formats information correctly with some categories having 0 members
    @Test
    public void testFamilyMemebersTwoHamper(){
        String actual = hamper.OgRequest(0, "Child under 8", 3,"Child over 8", 0, "Adult Female", 1, "Adult Male", 1);
        String given = "Hamper Order Form\n" + "Original Request\n" + "\nHamper 1: 3 Child over 8, 1 Adult Male" +"\n";
        assertEquals("Hamper did not return correctly formatted information.", given, actual);
    }

        // test if OGRequest formats information correctly with multiple hampers
    @Test
    public void testFamilyMemebersThreeHamper(){
        hamper.OgRequest(1, "Child under 8", 3,"Child over 8", 2, "Adult Female", 1, "Adult Male", 1);
        String actual = hamper.OgRequest(0, "Child under 8", 0,"Child over 8", 1, "Adult Female", 1, "Adult Male", 2);
        String given = "Hamper Order Form\n" + "Original Request\n" + "\nHamper 1: 1 Child under 8, 3 Child over 8, 2 Adult Female, 1 Adult Male" + "\n";
        given += "\nHamper 2: 1 Adult Female, 1 Adult Male" + "\n";
        assertEquals("Hamper did not return correctly formatted information.", given, actual);
    }

        // test if leastCals returns item with least amount of calories
    @Test
    public void testLeastCalsHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastCals(list);
        String actual = returned.getFoodName();
        String expected = "Granola Bar";
        assertEquals("Method did not choose item with least amount of calories", expected, actual);
    }

        // test with wrong item and that it does not equate to leastCals
    @Test
    public void testIncorrectLeastCalsHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastCals(list);
        String actual = returned.getFoodName();
        String expected = "Raisin Bran";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test with wrong item and that it does not equate to leastCals
    @Test
    public void testIncorrect2LeastCalsHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastCals(list);
        String actual = returned.getFoodName();
        String expected = "Canned Soup";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test if leastCals returns item with least amount of grains
    @Test
    public void testLeastGrainsHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastGrain(list);
        String actual = returned.getFoodName();
        String expected = "Canned Soup";
        assertEquals("Method did not choose item with least amount of grains", expected, actual);
    }

        // test with wrong item and that it does not equate to leastGrain
    @Test
    public void testIncorrectLeastGrainsHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastGrain(list);
        String actual = returned.getFoodName();
        String expected = "Raisin Bran";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test with wrong item and that it does not equate to leastGrain
    @Test
    public void testIncorrect2LeastGrainsHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastGrain(list);
        String actual = returned.getFoodName();
        String expected = "Granola Bar";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test if leastCals returns item with least amount of FV
    @Test
    public void testLeastFVHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastFV(list);
        String actual = returned.getFoodName();
        String expected = "Raisin Bran";
        assertEquals("Method did not choose item with least amount of fruits and veggies", expected, actual);
    }

        // test with wrong item and that it does not equate to leastFv
    @Test
    public void testIncorrectLeastFVHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastFV(list);
        String actual = returned.getFoodName();
        String expected = "Granola Bar";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test with wrong item and that it does not equate to leastFv
    @Test
    public void testIncorrect2LeastFVHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastFV(list);
        String actual = returned.getFoodName();
        String expected = "Canned Soup";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test if leastCals returns item with least amount of protein
    @Test
    public void testLeastProHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastPro(list);
        String actual = returned.getFoodName();
        String expected = "Granola Bar";
        assertEquals("Method did not choose item with least amount of protein", expected, actual);
    }

        // test with wrong item and that it does not equate to leastPro
    @Test
    public void testIncorrectLeastProHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastPro(list);
        String actual = returned.getFoodName();
        String expected = "Raisin Bran";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test with wrong item and that it does not equate to leastPro
    @Test
    public void testIncorrect2LeastProHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastPro(list);
        String actual = returned.getFoodName();
        String expected = "Canned Soup";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test if leastCals returns item with least amount of protein
    @Test
    public void testLeastOtherHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastOther(list);
        String actual = returned.getFoodName();
        String expected = "Granola Bar";
        assertEquals("Method did not choose item with least amount of other", expected, actual);
    }

    
        // test with wrong item and that it does not equate to leastOther
    @Test
    public void testIncorrectLeastOtherHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastOther(list);
        String actual = returned.getFoodName();
        String expected = "Raisin Bran";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // test with wrong item and that it does not equate to leastOther
    @Test
    public void testIncorrect2LeastOtherHamper(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Hamper hamper = new Hamper();
        Foods returned = hamper.leastOther(list);
        String actual = returned.getFoodName();
        String expected = "Canned Soup";
        assertNotEquals("Method returned incorrect item", expected, actual);
    }

        // can't test makeHamp caus SQL driver

}