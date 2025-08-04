package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
        mockBun = mock(Bun.class);
        mockIngredient = mock(Ingredient.class);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.moveIngredient(0, 1);
        assertEquals(ing2, burger.ingredients.get(0));
        assertEquals(ing1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockIngredient.getPrice()).thenReturn(1.5f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        float expected = 2.0f * 2 + 1.5f;
        assertEquals(expected, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        when(mockBun.getName()).thenReturn("Булочка");
        when(mockBun.getPrice()).thenReturn(2.0f);

        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getName()).thenReturn("Чесночный соус");
        when(mockIngredient.getPrice()).thenReturn(1.5f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();

        System.out.println("Receipt:\n" + receipt); // не проходил тест проверял там была запятая а не точка:)

        assertTrue(receipt.contains("(==== Булочка ====)"));
        assertTrue(receipt.toLowerCase().contains("sauce")); // lowercase для type
        assertTrue(receipt.contains("Чесночный соус"));
        assertTrue(receipt.contains("Price: 5,500000"));
    }
}
