package bikerentmodel;

import java.util.HashMap;
import java.util.regex.Pattern;

class Client extends TableDB{

    protected static HashMap<String, String> get(String login){
        db.query("SELECT passport, address FROM clients WHERE login=?", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            String passport = (db.res[1][0] != null) ? (String) db.res[1][0] : "";
            String address = (db.res[1][1] != null) ? (String) db.res[1][1] : "";

            HashMap <String, String> record = new HashMap<String, String>();
            record.put("login", login);
            record.put("passport", passport);
            record.put("address", address);
            return record;
        }
        return null;
    }

    protected static boolean updateAddress(String login, String address){

        db.query("update clients set address = ? where login = ?", new String[]{address, login}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static boolean updatePassport(String login, String passport){
        if (!Pattern.matches("\\d{10}", passport)){
            return false;
        };
        db.query("update clients set passport = ? where login = ?", new String[]{passport, login}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static boolean updatePassword(String login, String password){
        String passmd5 = User.pass_change(password);
        db.query("update clients set password = ? where login = ?", new String[]{passmd5, login}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static Long getId(String login){
        db.query("SELECT user_id FROM clients WHERE login=?", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (Long) db.res[1][0];
        };
        return null;
    }

    protected static boolean delete(String login){
        db.query("DELETE FROM clients WHERE login=?", new String[]{login}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }
}
