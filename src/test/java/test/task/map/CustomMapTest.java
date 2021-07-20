package test.task.map;

import org.junit.Assert;
import org.junit.Test;

public class CustomMapTest {
    private final Long KEY_1 = -100L;
    private final Long KEY_2 = 20L;
    private final Long KEY_3 = 30L;
    private final Car FIRST_CAR = new Car(200, "Mazda 6");
    private final Car SECOND_CAR = new Car(100, "Niva");
    private final Car THIRD_CAR = new Car(300, "Tesla");

    @Test
    public void getByNonExistedKey() {
        CustomMap<Integer> customMap = new CustomMapRealisation<>();
        Assert.assertNull("Test failed! If key doesn't exist, we should return null.",
                customMap.getValue(KEY_1));
    }

    @Test
    public void putAndGetOk() {
        CustomMap<Car> customMap = new CustomMapRealisation<>();
        customMap.put(KEY_1, FIRST_CAR);
        customMap.put(KEY_2, SECOND_CAR);
        customMap.put(KEY_3, THIRD_CAR);

        Assert.assertEquals("Test failed! The size isn't correct. Expected 3 but was "
                + customMap.getSize(), 3, customMap.getSize());

        Car firstActualValue = customMap.getValue(KEY_1);
        Car secondActualValue = customMap.getValue(KEY_2);
        Car thirdActualValue = customMap.getValue(KEY_3);
        Assert.assertEquals("Test failed! HashMap expects to contain 3 values, but was "
                + firstActualValue, FIRST_CAR, firstActualValue);
        Assert.assertEquals("Test failed! HashMap expects to contain 5 values, but was "
                + secondActualValue, SECOND_CAR, secondActualValue);
        Assert.assertEquals("Test failed! HashMap expects to contain 1 values, but was "
                + thirdActualValue, THIRD_CAR, thirdActualValue);
    }
}
