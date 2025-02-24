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

    protected static boolean updateBikeModel(String old_model_name, String new_model_name, String type, String gears){
        String q = "UPDATE bike_models SET bike_model=?, type=?, gears=? WHERE bike_model=?";
        db.query(q, new String[]{new_model_name, type, gears, old_model_name}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static boolean deleteBikeModel(String model_name){
        String q = "DELETE FROM bike_models WHERE bike_model=?";
        db.query(q, new String[]{model_name}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean createBikeModel(String model_name, String type, String gears){
        String q = "INSERT INTO bike_models (bike_model, type, gears) VALUES (?,?,?)";
        db.query(q, new String[]{model_name, type, gears}, BikeDB.CRUD.C);
        return (boolean) db.res[0][0];
    }
}
