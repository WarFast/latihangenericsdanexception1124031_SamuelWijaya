import java.util.ArrayList;
import java.util.Collections;

class NonPositiveNumberException extends Exception {
    public NonPositiveNumberException(String msg) {
        super(msg);
    }
}

class CustomList<T extends Number> {

    private ArrayList<T> data = new ArrayList<>();

    public void add(T value) throws NonPositiveNumberException {
        if (value.doubleValue() <= 0) {
            throw new NonPositiveNumberException("tidak bisa negatif" + value);
        }
        data.add(value);
    }
    public T getTenPercentLow() {
        if (data.isEmpty()) {
            return null; 
        }
        ArrayList<T> sorted = new ArrayList<>(data);
        Collections.sort(sorted, (a, b) -> Double.compare(a.doubleValue(), b.doubleValue()));
        int idx = (int)(sorted.size() * 0.1);
        if (idx < 0) idx = 0;
        if (idx >= sorted.size()) idx = sorted.size() - 1;

        return sorted.get(idx);
    }

    public void printAll() {
        System.out.println("Isi list: " + data);
    }
}

 public class latihangenericsdanexception {
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList<>();

        try {
            list.add(3);
            list.add(3);
            list.add(4);
            list.add(7);
            list.add(5);
            list.add(6);
            list.add(4);
            list.add(5);
        } catch (NonPositiveNumberException e) {
            System.out.println("Error: " + e.getMessage());
        }

        list.printAll();

        Integer tenLow = list.getTenPercentLow();
        System.out.println("10% low = " + tenLow);
    }
}
