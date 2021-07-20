package test.task.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class CustomMapTest {
    private final Long KEY_1 = -100L;
    private final Long KEY_2 = 20L;
    private final Long KEY_3 = 30L;
    private final Car FIRST_CAR = new Car(200, "Mazda");
    private final Car SECOND_CAR = new Car(100, "Niva");
    private final Car THIRD_CAR = new Car(300, "Tesla");
    private final Integer FIRST_INTEGER = 3;
    private final Integer SECOND_INTEGER = 5;


    @Test
    public void getByNonExistedKey() {
        CustomMap<Integer> customMap = new CustomMapImpl<>();
        Assert.assertNull("Test failed! If key doesn't exist, we should return null.",
                customMap.getValue(KEY_1));
    }

    @Test
    public void putAndGetOk() {
        CustomMap<Car> customMap = new CustomMapImpl<>();
        customMap.put(KEY_1, FIRST_CAR);
        customMap.put(KEY_2, SECOND_CAR);
        customMap.put(KEY_3, THIRD_CAR);

        Assert.assertEquals("Test failed! The size isn't correct. Expected 3 but was "
                + customMap.getSize(), 3, customMap.getSize());

        Car firstActualValue = customMap.getValue(KEY_1);
        Car secondActualValue = customMap.getValue(KEY_2);
        Car thirdActualValue = customMap.getValue(KEY_3);
        Assert.assertEquals("Test failed! HashMap expects to contain " + FIRST_CAR
                + " values, but was " + firstActualValue, FIRST_CAR, firstActualValue);
        Assert.assertEquals("Test failed! HashMap expects to contain " + SECOND_CAR
                + " values, but was " + secondActualValue, SECOND_CAR, secondActualValue);
        Assert.assertEquals("Test failed! HashMap expects to contain " + THIRD_CAR
                + " values, but was " + thirdActualValue, THIRD_CAR, thirdActualValue);
    }

    @Test
    public void putTheSameElement() {
        CustomMap<Car> customMap = new CustomMapImpl<>();
        customMap.put(KEY_1, FIRST_CAR);
        customMap.put(KEY_2, SECOND_CAR);
        customMap.put(KEY_3, THIRD_CAR);
        customMap.put(KEY_1, FIRST_CAR);
        customMap.put(KEY_2, SECOND_CAR);
        customMap.put(KEY_3, THIRD_CAR);
        Assert.assertEquals("Test failed! We should add checking if the same element "
                + "exists in the map", 3, customMap.getSize());

        Car firstActualValue = customMap.getValue(KEY_1);
        Car secondActualValue = customMap.getValue(KEY_2);
        Car thirdActualValue = customMap.getValue(KEY_3);
        Assert.assertEquals("Test failed! HashMap expects to contain " + FIRST_CAR
                + " values, but was " + firstActualValue, FIRST_CAR, firstActualValue);
        Assert.assertEquals("Test failed! HashMap expects to contain " + SECOND_CAR
                + " values, but was " + secondActualValue, SECOND_CAR, secondActualValue);
        Assert.assertEquals("Test failed! HashMap expects to contain " + THIRD_CAR
                + " values, but was " + thirdActualValue, THIRD_CAR, thirdActualValue);
    }

    @Test
    public void putAndGetByNullKey() {
        CustomMap<Integer> customMap = new CustomMapImpl<>();
        customMap.put(null, FIRST_INTEGER);
        Integer firstActualValue = customMap.getValue(null);
        Assert.assertEquals("Test failed! HashMap expects to contain " + FIRST_INTEGER
                + " values, but was " + firstActualValue, FIRST_INTEGER, firstActualValue);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1 but was "
                + customMap.getSize(), 1, customMap.getSize());
        customMap.put(null, SECOND_INTEGER);
        Integer secondActualValue = customMap.getValue(null);
        Assert.assertEquals("Test failed! HashMap expects to contain " + SECOND_INTEGER
                + " values, but was " + secondActualValue, SECOND_INTEGER, secondActualValue);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 1 but was "
                + customMap.getSize(), 1, customMap.getSize());
    }

    @Test
    public void clearTest() {
        CustomMap<Integer> customMap = new CustomMapImpl<>();
        customMap.put(KEY_1, FIRST_INTEGER);
        customMap.put(KEY_2, SECOND_INTEGER);
        customMap.clear();
        Assert.assertEquals("Test failed! The size isn't correct. Map was not cleared because have size of "
                + customMap.getSize(), 0, customMap.getSize());
    }

    @Test
    public void isEmptyTest() {
        CustomMap<Integer> customMap = new CustomMapImpl<>();
        Assert.assertTrue("Test failed! The size isn't correct. Map is empty but it returns false ",
                customMap.isEmpty());
    }

    @Test
    public void forEachTest() {
        CustomMap<Integer> customMap = new CustomMapImpl<>();
        customMap.put(KEY_1, FIRST_INTEGER);
        customMap.put(KEY_2, SECOND_INTEGER);
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        customMap.forEach((v) -> sum.updateAndGet(v1 -> v1 + v));
        Integer actualSum = sum.get();
        Integer expected = FIRST_INTEGER + SECOND_INTEGER;
        Assert.assertEquals("Test failed! HashMap expects to sum 2 integers ( " + FIRST_INTEGER
                + " and " + SECOND_INTEGER + ", but was "
                + actualSum, expected, actualSum);
    }
}
