package bikerentmodel;

import java.util.Arrays;

class Model extends TableDB{
    public String model_name;
    public enum bike_type{mountain, country, city}
    public bike_type type;
    public short gears;
    protected Model(String model_name, bike_type type, short gears){
        this.model_name = model_name;
        this.type = type;
        this.gears = gears;
    }

    protected static Object[][] getAllModels(){
        db.query("SELECT * FROM bike_models", new String[]{}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return Arrays.copyOfRange(db.res, 1, db.res.length);
        }
        return null;
    }
}
