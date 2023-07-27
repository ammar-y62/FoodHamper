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
    
    // tests for GUIFoodBank

        // test setters and getters for children under 8
    @Test 
    public void testSetGetUnder8GUI(){
        int givenData = 4;
        GUIFoodBank gui = new GUIFoodBank();
        gui.setUn8(givenData);
        int actual = gui.getUn8();
        assertEquals("Family size did not match what was expected", givenData, actual);
    }

        // test setters and getters for children over 8
    @Test 
    public void testSetGetOver8GUI(){
        int givenData = 2;
        GUIFoodBank gui = new GUIFoodBank();
        gui.setOv8(givenData);
        int actual = gui.getOv8();
        assertEquals("Family size did not match what was expected", givenData, actual);
    }

        // test setters and getters for female adults
    @Test 
    public void testSetGetAdultFemaleGUI(){
        int givenData = 1;
        GUIFoodBank gui = new GUIFoodBank();
        gui.setAdFem(givenData);
        int actual = gui.getFemAd();
        assertEquals("Family size did not match what was expected", givenData, actual);
    }

        // test setters and getters for male adults
    @Test 
    public void testSetGetAdultMaleGUI(){
        int givenData = 3;
        GUIFoodBank gui = new GUIFoodBank();
        gui.setMalAd(givenData);
        int actual = gui.getMalAd();
        assertEquals("Family size did not match what was expected", givenData, actual);
    }

        // test if validateInput catches error and returns false
    @Test
    public void testValidateInputFalseGUI(){
        int incorrectInput = -30;
        GUIFoodBank gui = new GUIFoodBank();
        gui.setAdFem(incorrectInput);
        assertFalse("Method did not catch error and return false.", gui.validateInput());
    }

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

    // tests for Family
        // test if constructor sets values correctly
    @Test
    public void testConstructorFamily(){
        Family fam = new Family(3, 1, 12, 8);
        int actual = fam.getMales() + fam.getFemales() + fam.getUnder8() + fam.getOver8();
        int expected = 3 + 1 + 12 + 8;
        assertEquals("Total number of members in family did not equal expected value", expected, actual);
    }

        // test if FVtotal method adds given argument every time it is called
    @Test
    public void testFVtotalFamily(){
        Family fam = new Family(1, 1, 1, 1);
        fam.FVtotal(126.0);
        fam.FVtotal(142.0);
        double actual = fam.getFVtotal();
        double expected = 268.0;
        assertEquals("Total content of fruits and vegetables did not add up to the correct value", expected, actual, 0);
    }

        // test if WGtotal method adds given argument every time it is called
    @Test
    public void testWGtotalFamily(){
        Family fam = new Family(1, 1, 1, 1);
        fam.WGtotal(265.0);
        fam.WGtotal(384.0);
        double actual = fam.getWGtotal();
        double expected = 649.0;
        assertEquals("Total content of whole grains did not add up to the correct value", expected, actual, 0);
    }

        // test if Prototal method adds given argument every time it is called
    @Test
    public void testPrototalFamily(){
        Family fam = new Family(1, 1, 1, 1);
        fam.Prototal(584.0);
        fam.Prototal(357.0);
        double actual = fam.getPrototal();
        double expected = 941.0;
        assertEquals("Total content of protein did not add up to the correct value", expected, actual, 0);
    }

        // test if Othertotal method adds given argument every time it is called
    @Test
    public void testOthertotalFamily(){
        Family fam = new Family(1, 1, 1, 1);
        fam.Othertotal(637.0);
        fam.Othertotal(421.0);
        double actual = fam.getOthertotal();
        double expected = 1058.0;
        assertEquals("Total content of other did not add up to the correct value", expected, actual, 0);
    }

        // test if FVweek method multiplies given argument by 7
    @Test
    public void testFVWeekFamily(){
        Family fam = new Family(1, 1, 1, 1);
        double actual = fam.FVweek(128);
        double expected = 896.0;
        assertEquals("Weekly content of fruits and vegetables did not return correct value", expected, actual, 0);
    }

        // test if WGweek method multiplies given argument by 7
    @Test
    public void testWGWeekFamily(){
        Family fam = new Family(1, 1, 1, 1);
        double actual = fam.Wgweek(275);
        double expected = 1925.0;
        assertEquals("Weekly content of whole grains did not return correct value", expected, actual, 0);
    }

        // test if Proweek method multiplies given argument by 7
    @Test
    public void testProWeekFamily(){
        Family fam = new Family(1, 1, 1, 1);
        double actual = fam.Proweek(346);
        double expected = 2422.0;
        assertEquals("Weekly content of protein did not return correct value", expected, actual, 0);
    }

        // test if Otherweek method multiplies given argument by 7
    @Test
    public void testOtherWeekFamily(){
        Family fam = new Family(1, 1, 1, 1);
        double actual = fam.Otherweek(741);
        double expected = 5187.0;
        assertEquals("Weekly content of other did not return correct value", expected, actual, 0);
    }

        // test if calories method recieves correct value
    @Test
    public void testCaloriesFamily(){
        Family fam = new Family(1, 1, 1, 1);
        fam.calories(1052.0);
        double actual = fam.getcalories();
        double expected = 1052.0;
        assertEquals("Calories did not recieve correct value", expected, actual, 0);
    }

    // tests for Foods
        // test if constructor sets correct ID
    @Test
    public void testGetIDFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        int actual = food.getID();
        int expected = 17;
        assertEquals("Did not return correct ID number", expected, actual);
    }

        // test if constructor sets correct Name
    @Test
    public void testGetNameFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        String actual = food.getFoodName();
        String expected = "pomegranate";
        assertEquals("Did not return correct food name", expected, actual);
    }

        // test if constructor sets correct WG content
    @Test
    public void testGetWGFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        double actual = food.getGrain();
        double expected = 10;
        assertEquals("Did not return correct whole grain content", expected, actual, 0);
    }    

        // test if constructor sets correct FV content
    @Test
    public void testGetFVFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        double actual = food.getFV();
        double expected = 85;
        assertEquals("Did not return correct fruits and veg content", expected, actual, 0);
    }  

        // test if constructor sets correct Pro content
    @Test
    public void testGetProFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        double actual = food.getPro();
        double expected = 0;
        assertEquals("Did not return correct protein content", expected, actual, 0);
    }  

        // test if constructor sets correct Other content
    @Test
    public void testGetOtherFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        double actual = food.getOther();
        double expected = 5;
        assertEquals("Did not return correct other content", expected, actual, 0);
    }  

        // test if constructor sets correct calories
    @Test
    public void testGetCaloriesFoods(){
        Foods food = new Foods(17, "pomegranate", 10, 85, 0, 5, 100);
        double actual = food.getCalories();
        double expected = 100;
        assertEquals("Did not return correct calories", expected, actual, 0);
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

    // tests for inventory
        // test setters and getters of inventory
    @Test
    public void testSetGetInvenInventory(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Inventory invent = new Inventory();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        ArrayList <Foods> actual = new ArrayList<Foods>();
        invent.setInven(list);
        actual = invent.getInven();
        ArrayList <Foods> expected = list;
        assertEquals("Inventory did not set correct food items.", expected, actual);
    }

        //test getter for specific item in inventory
    @Test
    public void testGetItemInventory(){
        ArrayList <Foods> list = new ArrayList<Foods>();
        Inventory invent = new Inventory();
        Foods item1 = new Foods(23, "Raisin Bran", 50, 20, 20, 10, 160);
        Foods item2 = new Foods(56, "Granola Bar", 65, 25, 5, 5, 120);
        Foods item3 = new Foods(72, "Canned Soup", 20, 50, 25, 5, 240);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        invent.setInven(list);
        Foods actual = invent.getItem(1);
        Foods expected = item2;
        assertEquals("Inventory did not recieve correct item.", expected, actual);
    }
}