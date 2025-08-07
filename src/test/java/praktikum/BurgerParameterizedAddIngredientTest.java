package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedAddIngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    private Burger burger;

    public BurgerParameterizedAddIngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getIngredients() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "ketchup", 0.5f},
                {IngredientType.FILLING, "beef", 1.2f},
                {IngredientType.SAUCE, "mustard", 0.4f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testIngredientListSizeAfterAddition() {
        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getType()).thenReturn(type);
        when(mockIngredient.getName()).thenReturn(name);
        when(mockIngredient.getPrice()).thenReturn(price);

        burger.addIngredient(mockIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testCorrectIngredientAddedToBurger() {
        Ingredient mockIngredient = mock(Ingredient.class);
        when(mockIngredient.getType()).thenReturn(type);
        when(mockIngredient.getName()).thenReturn(name);
        when(mockIngredient.getPrice()).thenReturn(price);

        burger.addIngredient(mockIngredient);

        assertEquals(mockIngredient, burger.ingredients.get(0));
    }
}
