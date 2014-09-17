package com.besaba.revonline.primitivecollections.internal;

/**
 * @author Marco
 * @since 1.0
 */
public class Utils {
    public static int hashCode(int value) {
        return value;
    }

    public static int hashCode(boolean value) {
        return value ? 1 : 0;
    }

    public static int hashCode(long value) {
        return (int)(value ^ (value >>> 32));
    }

    public static int hashCode(float value) {
        return Float.floatToIntBits(value);
    }

    public static int hashCode(double value) {
        return hashCode(Double.doubleToLongBits(value));
    }
}
