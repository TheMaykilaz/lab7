package main.coffeevan.model;

import static org.junit.jupiter.api.Assertions.*;

import main.coffeevan.model.InstantCoffeeInBags;

import org.junit.jupiter.api.Test;

class InstantCoffeeInBagsTest {
    @Test
    public void testVolumeCalculation() {
        InstantCoffeeInBags instantCoffee = new InstantCoffeeInBags("3-in-1", 500, 200); // 200 пакетиків
        assertEquals(4, instantCoffee.getVolume()); // 200 пакетиків = 4 літри
    }

    @Test
    public void testGetAndSetBags() {
        InstantCoffeeInBags instantCoffee = new InstantCoffeeInBags("Classic", 300, 100);
        assertEquals(100, instantCoffee.getBags());
        instantCoffee.setBags(300);
        assertEquals(300, instantCoffee.getBags());
    }

    @Test
    public void testInheritance() {
        InstantCoffeeInBags instantCoffee = new InstantCoffeeInBags("Gold", 400, 100);
        assertEquals("Gold", instantCoffee.getName());
        assertEquals(400, instantCoffee.getPrice());
    }

    @Test
    public void testToString() {
        InstantCoffeeInBags instantCoffee = new InstantCoffeeInBags("3-in-1", 500, 200);
        String expected = "InstantCoffeeInBags{name='3-in-1', price=500, bags=200}";
        assertEquals(expected, instantCoffee.toString());
    }

}