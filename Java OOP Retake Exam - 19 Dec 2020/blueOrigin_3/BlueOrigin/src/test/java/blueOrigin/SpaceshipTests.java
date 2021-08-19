package blueOrigin;

import org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
public class SpaceshipTests {
    Spaceship spaceship ;
    Astronaut astronaut ;


    @Test
    public  void test1(){
        Spaceship spaceship = new Spaceship("spaceshipName",1);
    }
    @Test(expected = NullPointerException.class)
    public  void test2(){
        Spaceship spaceship = new Spaceship("",1);
    }

    @Test(expected = NullPointerException.class)
    public  void test3(){
        Spaceship spaceship = new Spaceship(null,1);
    }


    @Test(expected = IllegalArgumentException.class)
    public  void test4(){
        Spaceship spaceship = new Spaceship("K",-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public  void test5(){
        Spaceship spaceship = new Spaceship("K",2);
        Astronaut astronaut = new Astronaut("name",99.);
        spaceship.add(astronaut);
        spaceship.add(astronaut);
    }

    @Test(expected = IllegalArgumentException.class)
    public  void test51(){
        Spaceship spaceship = new Spaceship("K",1);
        Astronaut astronaut = new Astronaut("name",99.);
        Astronaut astronaut1 = new Astronaut("name1",99.);
        spaceship.add(astronaut);
        spaceship.add(astronaut1);
    }

    @Test
    public  void test6(){
        Spaceship spaceship = new Spaceship("K",1);
        Astronaut astronaut = new Astronaut("name",99.);
        spaceship.add(astronaut);
        Assert.assertTrue(spaceship.remove("name"));
    }

    @Test
    public  void test7(){
        Spaceship spaceship = new Spaceship("K",1);
        Astronaut astronaut = new Astronaut("name",99.);
        spaceship.add(astronaut);
        Assert.assertEquals(spaceship.getCapacity(),1);
    }

    @Test
    public  void test8(){
        Spaceship spaceship = new Spaceship("K",1);
        Astronaut astronaut = new Astronaut("name",99.);
        spaceship.add(astronaut);
        Assert.assertEquals(spaceship.getCount(),1);
    }


    @Test
    public  void test9(){
        Spaceship spaceship = new Spaceship("K",1);
        Assert.assertEquals(spaceship.getName(),"K");
    }

    @Test
    public  void test10(){
        Astronaut astronaut = new Astronaut("name",99.);
        Assert.assertTrue(astronaut.getOxygenInPercentage()-99. ==0);
    }


}
