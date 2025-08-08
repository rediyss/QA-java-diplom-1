package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.within;
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
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.bun).isEqualTo(mockBun);
        softly.assertAll();
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients).hasSize(1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(mockIngredient);
        softly.assertAll();
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients).isEmpty();
        softly.assertAll();
    }

    @Test
    public void testMoveIngredient() {
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.moveIngredient(0, 1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(ing2);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(ing1);
        softly.assertAll();
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockIngredient.getPrice()).thenReturn(1.5f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        float expected = 2.0f * 2 + 1.5f;

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat((double) burger.getPrice())
                .isCloseTo((double) expected, within(0.001));
        softly.assertAll();
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

        String expectedReceipt =
                "(==== Булочка ====)\n" +
                        "= sauce Чесночный соус =\n" +
                        "(==== Булочка ====)\n\n" +
                        "Price: 5,500000\n";

        String actualReceipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualReceipt).isEqualTo(expectedReceipt);
        softly.assertAll();
    }
}
