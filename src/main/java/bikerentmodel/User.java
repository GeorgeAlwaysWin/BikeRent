package bikerentmodel;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class User {
    public long id;
    public String login;
    public Roles.roles role;
    public static BikeDB db = BikeDB.getInstance();

    private static class UserHolder{
        public static final User HOLDER_INSTANCE = new User();
    }

    public static User getInstance(){
        return User.UserHolder.HOLDER_INSTANCE;
    }

    public static String md5hash(String data){
        data = data + "OsL3*1";
        byte[] byteddata = data.getBytes(StandardCharsets.UTF_8);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hasheddata = md5.digest(byteddata);
        return new String(hasheddata, StandardCharsets.UTF_8);
    }

    public static boolean auth(String log, String pass){
       db.query("SELECT user_id, login, password FROM admin UNION SELECT user_id, login, password FROM clients UNION SELECT user_id, login, password FROM workers WHERE login=?",new String[]{log},BikeDB.CRUD.R);
       if ((boolean) db.res[0][0]){
           String md5pass = md5hash(pass);
           if (md5pass.equals((String) db.res[1][2])){
               User user = User.getInstance();
               user.id = (long) db.res[1][0];
               user.login = log;
               user.role = Roles.get_role(user.id);
               return true;
           }
       }
       return false;
    }

    public static boolean reg(String log, String pass){
        db.query("SELECT user_id, login, password FROM admin UNION SELECT user_id, login, password FROM clients UNION SELECT user_id, login, password FROM workers WHERE login=?",new String[]{log},BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return false;
        }
        db.query("INSERT INTO roles (role) VALUES (?)", new String[]{"client"}, BikeDB.CRUD.C);
        if (!(boolean) db.res[0][0]){
            return false;
        }
        db.query("SELECT MAX(user_id) FROM roles", null, BikeDB.CRUD.R);
        db.query("INSERT INTO clients (user_id, login, password) VALUES (?, ?, ?)",
                new String[]{Long.toString((Long) db.res[1][0]), log, md5hash(pass)}, BikeDB.CRUD.C);

        return (boolean) db.res[0][0];
    }
}

