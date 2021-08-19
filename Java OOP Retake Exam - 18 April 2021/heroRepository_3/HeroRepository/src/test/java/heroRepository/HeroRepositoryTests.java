package heroRepository;

import org.junit.Assert;
import org.junit.Test;

public class HeroRepositoryTests {


    @Test (expected = NullPointerException.class)
    public void testCreate() {
        Hero myTestHero = new Hero("Rum", 1);
        HeroRepository myTestHeroRepository = new HeroRepository();
        myTestHeroRepository.create(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreate1() {
        Hero myTestHero = new Hero("Rum", 1);
        HeroRepository myTestHeroRepository = new HeroRepository();
        myTestHeroRepository.create(myTestHero);
        myTestHeroRepository.create(myTestHero);
    }
    @Test (expected = NullPointerException.class)
    public void testRemove1() {
        HeroRepository myTestHeroRepository = new HeroRepository();
        myTestHeroRepository.remove("   ");
    }
    @Test (expected = NullPointerException.class)
    public void testRemove2() {
        HeroRepository myTestHeroRepository = new HeroRepository();
        myTestHeroRepository.remove(null);
    }
    @Test
    public void testGetHero1() {
        HeroRepository myTestHeroRepository = new HeroRepository();
        Hero myTestHero = new Hero("Rum", 1);
        myTestHeroRepository.create(myTestHero);
        Assert.assertEquals(myTestHeroRepository.getHero("Rum"),myTestHero);
    }
    @Test
    public void testGetHero2() {
        HeroRepository myTestHeroRepository = new HeroRepository();
        Hero myTestHero1 = new Hero("Rum1", 1);
        Hero myTestHero2 = new Hero("Rum2", 2);
        Hero myTestHero3 = new Hero("Rum3", 3);
        myTestHeroRepository.create(myTestHero1);
        myTestHeroRepository.create(myTestHero2);
        myTestHeroRepository.create(myTestHero3);
        Assert.assertEquals(myTestHeroRepository.getHeroWithHighestLevel(),myTestHero3);
    }
}
