package bikerentmodel;

public class BikeModel {
    public static void main(String[] args) {
        BikeDB db = BikeDB.getInstance();
        Object[][] res;
        if (User.auth("abc", "1234")){
            System.out.print("OK");
        };

    }
}
