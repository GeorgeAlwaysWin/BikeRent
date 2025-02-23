package bikerentmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.entry;

class Client extends TableDB{
    public long id;
    public String login;
    public String passport;
    public String address;

    protected Client(String login, String passport, String address){
        this.login = login;
        this.passport = passport;
        this.address = address;
    }

    protected static HashMap<String, String> getClient(long id){
        db.query("SELECT login, passport, address FROM clients WHERE user_id=?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            String lg = (db.res[1][0] != null) ? (String) db.res[1][0] : "";
            String passport = (db.res[1][1] != null) ? (String) db.res[1][1] : "";
            String address = (db.res[1][2] != null) ? (String) db.res[1][2] : "";

            HashMap <String, String> record = new HashMap<String, String>();
            record.put("login", lg);
            record.put("passport", passport);
            record.put("address", address);
            return record;
        }
        return null;
    }

    protected static boolean updateAddress(long id, String address){

        db.query("update clients set address = ? where user_id = ?", new String[]{address, Long.toString(id)}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static boolean updatePassport(long id, String passport){
        if (!Pattern.matches("\\d{10}", passport)){
            return false;
        };
        db.query("update clients set passport = ? where user_id = ?", new String[]{passport, Long.toString(id)}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static boolean updatePassword(long id, String password){
        String passmd5 = User.pass_change(password);
        db.query("update clients set password = ? where user_id = ?", new String[]{passmd5, Long.toString(id)}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }
}
