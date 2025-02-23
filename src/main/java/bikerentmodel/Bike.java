package bikerentmodel;

import java.util.Arrays;

class Bike extends TableDB{
    public long id;
    public String shop_name;
    public String model_name;

    protected Bike(String shop_name, String model_name){
        this.model_name = shop_name;
        this.shop_name = model_name;
    }

    protected static Object[][] getAllBikes(){
        db.query("SELECT * FROM bikes", new String[]{}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return Arrays.copyOfRange(db.res, 1, db.res.length);
        }
        return null;
    }
}
