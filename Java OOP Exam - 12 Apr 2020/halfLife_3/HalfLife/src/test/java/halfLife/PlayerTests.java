package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    Player player;
    Gun gun;

    @Before
    public void setUp(){
        player = new Player("Username", 1);
        gun = new Gun("Gun_Name", 10);
    }

    @Test
    public void testPlayerConstructorWithValidData(){
        Assert.assertEquals("Username", player.getUsername());
        Assert.assertEquals(1, player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testPlayerConstructorWithNullUsername(){
        player = new Player(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testPlayerConstructorWithEmptyUsername(){
        player = new Player("   ", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerConstructorWithNegativeHealth(){
        player = new Player("Username", -1);
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunNull() {
        player.addGun(null);
    }

    @Test
    public void testAddGunWithValidData() {
        player.addGun(gun);
        Assert.assertEquals(gun, player.getGun(gun.getName()));
    }

    @Test
    public void testGetGunFromNotExistingGun() {
        Assert.assertNull(player.getGun(gun.getName()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetGunsAndTryToRemove() {
        for (int i = 0; i < 10; i++) {
            player.addGun(gun);
        }
        player.getGuns().remove(gun);
    }

    @Test
    public void testGetGunsWithValidData() {
        for (int i = 0; i < 10; i++) {
            player.addGun(gun);
        }
        int size = player.getGuns().size();
        Assert.assertEquals(10, size);
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageWithZeroOrNegativeHealth() {
        player = new Player("Username", 0);
        player.takeDamage(10);
    }

    @Test
    public void testTakeDamageWithValidHealth() {
        player = new Player("Username", 100);
        player.takeDamage(10);
        Assert.assertEquals(90, player.getHealth());
    }

    @Test
    public void testRemoveGunWithNotExistingGun() {
        boolean expected  = player.removeGun(gun);
        Assert.assertFalse(expected);
    }

    @Test
    public void testRemoveGunWithValidGun() {
        player.addGun(gun);
        boolean expected = player.removeGun(gun);
        Assert.assertTrue(expected);
    }
}
