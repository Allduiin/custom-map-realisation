import org.junit.Assert;
import org.junit.Test;

public class CustomMapTest {
    private final Long DEFAULT_KEY_1 = -100L;
    @Test
    public void getByNonExistedKey() {
        CustomMap<Integer> myHashMap = new CustomMapRealisation<>();
        Assert.assertNull("Test failed! If key doesn't exist, we should return null.",
                myHashMap.getValue(DEFAULT_KEY_1));
    }
}
