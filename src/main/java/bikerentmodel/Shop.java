package bikerentmodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

class Shop extends TableDB{
    public String name;
    public String address;

    protected Shop(String name, String address){
        this.name = name;
        this.address = address;
    }

    protected static Object[][] getAllShops(){
        db.query("SELECT * FROM shops", new String[]{}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return Arrays.copyOfRange(db.res, 1, db.res.length);
        }
        return null;
    }

    protected static String getAddressByShopName(String shop_name){
        db.query("SELECT address FROM shops WHERE shops.shop_name = ?", new String[]{shop_name}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (String) db.res[1][0];
        }
        return null;
    }

    protected static Object[][] getShopsByModel(String model){
        String q = "SELECT shops.shop_name, shops.address FROM bikes JOIN shops ON bikes.shop_name=shops.shop_name LEFT JOIN (select book_id, shop_name, bike_model FROM booking WHERE end_date IS NUll AND bike_model=?) AS booked ON booked.shop_name=shops.shop_name WHERE bikes.bike_model = ? GROUP BY shops.shop_name HAVING COUNT(book_id) < COUNT(*)";
        db.query(q, new String[]{model, model}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return Arrays.copyOfRange(db.res, 1, db.res.length);
        }
        return null;
    }

    protected static void deleteShop(String shop_name){
        String q = "SELECT shop_name FROM shops WHERE shop_name != ? LIMIT 1";
        db.query(q, new String[]{shop_name}, BikeDB.CRUD.R);
        String alt = (String) db.res[1][0];
        q = "UPDATE shop_workers SET shop_name=? WHERE shop_name=?";
        db.query(q, new String[]{alt, shop_name}, BikeDB.CRUD.U);
        q = "UPDATE bikes SET shop_name=? WHERE shop_name=?";
        db.query(q, new String[]{alt, shop_name}, BikeDB.CRUD.U);
        q = "UPDATE booking SET shop_name=? WHERE shop_name=?";
        db.query(q, new String[]{alt, shop_name}, BikeDB.CRUD.U);
        q = "DELETE FROM shops WHERE shop_name=?";
        db.query(q, new String[]{shop_name}, BikeDB.CRUD.D);
    }

    protected static void createShop(String shop_name, String address){
        String q = "INSERT INTO shops (shop_name, address) VALUES (?,?)";
        db.query(q, new String[]{shop_name, address}, BikeDB.CRUD.C);
    }

    protected static void updateShop(String old_shop_name, String new_shop_name, String address){
        String q = "UPDATE shop_workers SET shop_name=? WHERE shop_name=?";
        db.query(q, new String[]{new_shop_name, old_shop_name}, BikeDB.CRUD.U);
        q = "UPDATE bikes SET shop_name=? WHERE shop_name=?";
        db.query(q, new String[]{new_shop_name, old_shop_name}, BikeDB.CRUD.U);
        q = "UPDATE booking SET shop_name=? WHERE shop_name=?";
        db.query(q, new String[]{new_shop_name, old_shop_name}, BikeDB.CRUD.U);
        q = "UPDATE shops SET shop_name=?, address=? WHERE shops.shop_name=?";
        db.query(q, new String[]{new_shop_name, address, old_shop_name}, BikeDB.CRUD.U);
    }
}
