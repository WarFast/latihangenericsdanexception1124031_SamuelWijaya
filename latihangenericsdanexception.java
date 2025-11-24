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
            throw new NonPositiveNumberException("Angka tidak boleh nol atau negatif: " + value);
        }
        data.add(value);
    }

    // Mengambil nilai pada posisi 10% rendah
    public T getTenPercentLow() {
        if (data.isEmpty()) {
            return null; // gaya mahasiswa: return null daripada lempar exception lain
        }

        // copy supaya tidak mengganggu data asli
        ArrayList<T> sorted = new ArrayList<>(data);
        Collections.sort(sorted, (a, b) -> Double.compare(a.doubleValue(), b.doubleValue()));

        // index 10% (pakai floor)
        int idx = (int) Math.floor(sorted.size() * 0.1);

        // Antisipasi jika hasil 0.1 * size = 0
        if (idx < 0) idx = 0;
        if (idx >= sorted.size()) idx = sorted.size() - 1;

        return sorted.get(idx);
    }

    public void printAll() {
        System.out.println("Isi list: " + data);
    }
}

 class Main {
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
