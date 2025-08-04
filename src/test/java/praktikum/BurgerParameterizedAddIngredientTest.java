package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

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
        return Arrays.asList(new Object[][] {
                { IngredientType.SAUCE, "ketchup", 0.5f },
                { IngredientType.FILLING, "beef", 1.2f },
                { IngredientType.SAUCE, "mustard", 0.4f }
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testAddIngredientParameterized() {
        Ingredient ingredient = new Ingredient(type, name, price);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }
}
