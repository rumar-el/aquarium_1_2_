package computers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTests {
    ComputerManager compManag ;
    Computer comp ;

    @Test
    public void test1() {
        ComputerManager compManag = new ComputerManager();
        Computer comp = new Computer("Dell","Lattitude",100.);
        compManag.addComputer(comp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2() {
        ComputerManager compManag = new ComputerManager();
        Computer comp = new Computer("Dell","Lattitude",100.);
        compManag.addComputer(comp);
        compManag.addComputer(comp);
    }

    @Test
    public void test3() {
        ComputerManager compManag = new ComputerManager();
        Computer comp = new Computer("Dell","Lattitude",100.);
        Computer comp1 = new Computer("HP","G",100.);
        compManag.addComputer(comp);
        compManag.addComputer(comp1);
        Assert.assertEquals(compManag.getCount(),2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void test4() {
        ComputerManager compManag = new ComputerManager();
        Computer comp = new Computer("Dell","Lattitude",100.);
        Computer comp1 = new Computer("HP","G",100.);
        compManag.addComputer(comp);
        compManag.addComputer(comp1);
        compManag.getComputer("HPO","G");
    }

    @Test
    public void test5() {
        ComputerManager compManag = new ComputerManager();
        Computer comp = new Computer("Dell","Lattitude",100.);
        Computer comp1 = new Computer("HP","G",100.);
        compManag.addComputer(comp);
        compManag.addComputer(comp1);
        compManag.getComputer("HP","G");
    }


    @Test
    public void test6() {
        ComputerManager compManag = new ComputerManager();
        List<Computer> testList = new ArrayList<>();
        Computer comp = new Computer("Dell","Lattitude",100.);
        Computer comp1 = new Computer("HP","G",100.);
        Computer comp2 = new Computer("HP","K",120.);
        compManag.addComputer(comp);
        compManag.addComputer(comp1);
        testList.add(comp1);
        compManag.addComputer(comp2);
        testList.add(comp2);
        Assert.assertEquals(compManag.getComputersByManufacturer("HP"),testList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test7() {
        ComputerManager compManag = new ComputerManager();
        List<Computer> testList = new ArrayList<>();
        compManag.addComputer(null);
        compManag.getComputersByManufacturer("FRI");
    }

    @Test
    public void test8() {
        Computer comp3 = new Computer("HP","K",120.);
        Assert.assertTrue(comp3.getPrice() == 120.);
    }

    @Test
    public void test10() {
        ComputerManager compManag = new ComputerManager();
        List<Computer> testList = new ArrayList<>();
        Computer comp = new Computer("Dell","Lattitude",100.);
        Computer comp1 = new Computer("HP","G",100.);
        Computer comp2 = new Computer("HP","K",120.);
        compManag.addComputer(comp);
        testList.add(comp);
        compManag.addComputer(comp1);
        testList.add(comp1);
        compManag.addComputer(comp2);
        testList.add(comp2);
        Assert.assertEquals(compManag.getComputers(),testList);
    }

    @Test
    public void test9() {
        ComputerManager compManag = new ComputerManager();
        Computer comp = new Computer("HP","K",100.);
        compManag.addComputer(comp);
        Assert.assertEquals( compManag.removeComputer("HP","K"),comp);
    }


}