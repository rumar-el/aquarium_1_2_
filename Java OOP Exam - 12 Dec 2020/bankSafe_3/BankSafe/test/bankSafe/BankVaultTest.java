package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class BankVaultTest {
    private Item item;
    private BankVault bankVault;

    @Before
    public void setUp() {
        item = new Item("Owner", "Item_Id");
        bankVault = new BankVault();
    }

    @Test
    public void testConstructor(){
        Assert.assertEquals(12, bankVault.getVaultCells().size());
    }

//    @Test(expected = UnsupportedOperationException.class)
//    public void testGetVaultCellsWithInvalidData(){
//        bankVault.getVaultCells().remove("C4");
//    }

    @Test
    public void testGetVaultCellsWithValidData(){
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        Assert.assertNull(vaultCells.get("A3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItem() throws OperationNotSupportedException {
        bankVault.addItem("C34", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItem1() throws OperationNotSupportedException {
        bankVault.addItem(null, item);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddItem2() throws OperationNotSupportedException {
        bankVault.addItem("C4", null);
    }

    @Test
    public void testAddItem3() throws OperationNotSupportedException {
        bankVault.addItem("C4", item);
        Assert.assertEquals(item, bankVault.getVaultCells().get("C4"));
    }

    @Test
    public void testAddItem4() throws OperationNotSupportedException {
        String expected = bankVault.addItem("C4", item);
        Assert.assertEquals("Item:Item_Id saved successfully!", expected);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddItem5() throws OperationNotSupportedException {
        bankVault.addItem("C4", item);
        bankVault.addItem("C4", item);
    }

    @Test
    public void testAddItem6(){
        try {
            bankVault.addItem("C34", null);
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            Assert.assertEquals("Cell doesn't exist!", e.getMessage());
        }
    }

    @Test
    public void testAddItem7() {
        try {
            bankVault.addItem("C4", null);
        } catch (OperationNotSupportedException e) {
            Assert.assertEquals("Item is already in cell", e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItem(){
        bankVault.removeItem("dw", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItem1(){
        bankVault.removeItem("B1", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItem2(){
        bankVault.removeItem("B1", item);
    }

    @Test
    public void testRemoveItem3() throws OperationNotSupportedException {
        bankVault.addItem("B1", item);
        String expected = bankVault.removeItem("B1", item);
        Assert.assertEquals("Remove item:Item_Id successfully!", expected);
    }

    @Test
    public void testGetOwner() {
        Assert.assertEquals("Owner", item.getOwner());
    }

    @Test
    public void test(){
        BankVault bankVault = new BankVault();
        Assert.assertNull(bankVault.getVaultCells().get("C4"));
    }

    @Test
    public void test1() throws OperationNotSupportedException {
        Item item = new Item("O", "I");
        bankVault.addItem("B1", item);
        String expected = bankVault.removeItem("B1", item);
        Assert.assertEquals(12, bankVault.getVaultCells().size());
    }

    @Test
    public void test2() throws OperationNotSupportedException {
        Item item1 = new Item("Owner_1", "Id_1");
        Item item2 = new Item("Owner_2", "Id_2");
        Item item3 = new Item("Owner_3", "Id_3");
        Item item4 = new Item("Owner_4", "Id_4");
        bankVault.addItem("A1",item1);
        bankVault.addItem("A2",item2);
        bankVault.addItem("A3",item3);
        bankVault.addItem("A4",item4);
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        Assert.assertNotNull(vaultCells);
        Assert.assertEquals(item1, vaultCells.get("A1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test3() throws OperationNotSupportedException {
        bankVault.addItem("A1", item);
        bankVault.addItem("A1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test4() throws OperationNotSupportedException {
        bankVault.addItem(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test5() {
        bankVault.removeItem(null, null);
    }

    @Test
    public void test6() throws OperationNotSupportedException {
        Assert.assertNull(bankVault.getVaultCells().get("A1"));
        bankVault.addItem("A1", item);
        Assert.assertEquals(item, bankVault.getVaultCells().get("A1"));
        bankVault.removeItem("A1", item);
        Assert.assertNull(bankVault.getVaultCells().get("A1"));
    }
}