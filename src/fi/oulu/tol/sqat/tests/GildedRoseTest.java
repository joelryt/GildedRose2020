package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void testGildedRose_quality_decreases() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 1, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void testGildedRose_Aged_Brie_quality_increases() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 1, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality for Aged Brie", 1, quality);
	}
	
	@Test
	public void testGildedRose_Aged_Brie_quality_doesnt_increases_over_50() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 1, 50));
		inn.setItem(new Item("Aged Brie", 0, 50));
		inn.setItem(new Item("Aged Brie", -1, 50));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		assertEquals("Aged Brie quality1 over 50", 50, quality1);
		assertEquals("Aged Brie quality2 over 50", 50, quality2);
		assertEquals("Aged Brie quality3 over 50", 50, quality3);
	}
	
	
	@Test
	public void testGildedRose_Aged_Brie_quality_increases_twice_as_fast() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 0, 20));
		inn.setItem(new Item("Aged Brie", -1, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		
		assertEquals("Failed quality1 for Aged Brie", 22, quality1);
		assertEquals("Failed quality2 for Aged Brie", 22, quality2);
	}
	
	@Test
	public void testGildedRose_quality_doesnt_decrease_below_0() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Elixir of the mongoose", 1, 0));
		inn.setItem(new Item("Elixir of the mongoose", 0, 0));
		inn.setItem(new Item("Elixir of the mongoose", -1, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		assertEquals("Elixir quality1 below 0", 0, quality1);
		assertEquals("Elixir quality2 below 0", 0, quality2);
		assertEquals("Elixir quality3 below 0", 0, quality3);
	}
	
	@Test
	public void testGildedRose_Sulfuras_quality_doesnt_decrease() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 1, 80));
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		assertEquals("Sulfuras quality1 changed", 80, quality1);
		assertEquals("Sulfuras quality2 changed", 80, quality2);
		assertEquals("Sulfuras quality3 changed", 80, quality3);
	}

	@Test
	public void testGildedRose_Backstage_pass_quality_increases() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		assertEquals("Backstage pass quality1 incorrect", 21, quality1);
		assertEquals("Backstage pass quality2 incorrect", 22, quality2);
		assertEquals("Backstage pass quality3 incorrect", 23, quality3);
	}
	
	@Test
	public void testGildedRose_Backstage_pass_quality_doesnt_increase_over_50() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 49));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		int quality4 = items.get(3).getQuality();
		int quality5 = items.get(4).getQuality();
		int quality6 = items.get(5).getQuality();
		
		assertEquals("Backstage pass quality1 over 50", 50, quality1);
		assertEquals("Backstage pass quality2 over 50", 50, quality2);
		assertEquals("Backstage pass quality3 over 50", 50, quality3);
		assertEquals("Backstage pass quality4 over 50", 50, quality4);
		assertEquals("Backstage pass quality5 over 50", 50, quality5);
		assertEquals("Backstage pass quality6 over 50", 50, quality6);
	}
	
	@Test
	public void testGildedRose_Backstage_pass_concert_over() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 50));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		
		assertEquals("Backstage pass quality1 after concert incorrect", 0, quality1);
		assertEquals("Backstage pass quality2 after concert incorrect", 0, quality2);
	}
	
	@Test
	public void testGildedRose_quality_decreases_twice_as_fast() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 0, 6));
		inn.setItem(new Item("Conjured Mana Cake", -1, 6));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		
		assertEquals("Twice as fast quality1 decrease incorrect", 4, quality1);
		assertEquals("Twice as fast quality2 decrease incorrect", 4, quality2);
	}
	
	@Test
	public void testGildedRose_skip_loop() {
		GildedRose inn = new GildedRose();
		inn.oneDay();
	}
}
