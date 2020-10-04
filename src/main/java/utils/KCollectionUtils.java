import java.utils.*;

public class KCollectionUtils {

    public static isNull(Object obj) {
        return obj == null;
    }


    public static isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isProperSubCollection(final Collection<?> a, final Collection<?> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        return a.size() < b.size() && CollectionUtils.isSubCollection(a, b);
    }

    public static boolean isEqualCollection(final Collection<?> a, final Collection<?> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        if (a.size() != b.size()) {
            return false;
        }
        final CardinalityHelper<Object> helper = new CardinalityHelper<>(a, b);
        if (helper.cardinalityA.size() != helper.cardinalityB.size()) {
            return false;
        }
        for (final Object obj : helper.cardinalityA.keySet()) {
            if (helper.freqA(obj) != helper.freqB(obj)) {
                return false;
            }
        }
        return true;
    }

}