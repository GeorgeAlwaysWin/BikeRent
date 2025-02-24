package bikerentmodel;

import java.util.Arrays;

class Bike extends TableDB{

//    protected static Object[][] getAllBikes(){
//        db.query("SELECT * FROM bikes", new String[]{}, BikeDB.CRUD.R);
//        if ((boolean) db.res[0][0]){
//            return Arrays.copyOfRange(db.res, 1, db.res.length);
//        }
//        return null;
//    }

    protected static Object[][] getAllNotRentedBikesFromShop(String shop_name){
        db.query("SELECT * FROM bikes WHERE shop_name=? AND bike_id NOT IN (SELECT bike_id FROM rent)", new String[]{shop_name}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return Arrays.copyOfRange(db.res, 1, db.res.length);
        }
        return null;
    }

    protected static Long getBikeForRent(String shop_name, String model_name){
        String q = "SELECT bike_id FROM bikes WHERE bikes.shop_name=? AND bikes.bike_model=? AND bikes.bike_id NOT IN (SELECT bike_id FROM rent) LIMIT 1";
        db.query(q, new String[]{shop_name, model_name}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (Long) db.res[1][0];
        }
        return null;
    }

    protected static boolean checkBikeByModel(String model_name){
        String q = "SELECT bike_model FROM bikes WHERE bikes.bike_model=? LIMIT 1";
        db.query(q, new String[]{model_name}, BikeDB.CRUD.R);
        return (boolean) db.res[0][0];
    }

    protected static boolean deleteBike(String id){
        String q = "DELETE FROM bikes WHERE bike_id=?";
        db.query(q, new String[]{id}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean createBike(String model_name, String shop_name){
        String q = "INSERT INTO bikes (bike_model, shop_name) VALUES (?,?)";
        db.query(q, new String[]{model_name, shop_name}, BikeDB.CRUD.C);
        return (boolean) db.res[0][0];
    }

}
