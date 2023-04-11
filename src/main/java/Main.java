import dao.impl.CurrencyDao;

public class Main {
    public static void main(String[] args) {
        System.out.println(new CurrencyDao().read(1));
    }
}
