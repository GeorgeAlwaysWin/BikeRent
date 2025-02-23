package bikerentmodel;

import java.nio.charset.StandardCharsets;
import java.security.*;

class User {
    protected long id;
    protected String login = null;
    protected Roles.roles role = null;
    protected static BikeDB db = BikeDB.getInstance();

    private static class UserHolder{
        public static final User HOLDER_INSTANCE = new User();
    }

    protected static User getInstance(){
        return User.UserHolder.HOLDER_INSTANCE;
    }

    private static String md5hash(String data){
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

    protected static String pass_change(String data){
        return User.md5hash(data);
    }

    protected static boolean checkPassword(String log, String pass){
        db.query("SELECT user_id, login, password FROM admin UNION SELECT user_id, login, password FROM clients UNION SELECT user_id, login, password FROM workers WHERE login=?",new String[]{log},BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            String md5pass = md5hash(pass);
            if (md5pass.equals((String) db.res[1][2])){
                return true;
            }
        }
        return false;
    }

    protected static boolean auth(String log, String pass){
        if (checkPassword(log, pass)){
            User user = User.getInstance();
            user.id = (long) db.res[1][0];
            user.login = log;
            user.role = Roles.get_role(user.id);
            return true;
        }
        return false;
    }

    protected static boolean reg(String log, String pass){
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

    protected void logout(){
        this.login = null;
        this.id = -1;
        this.role = null;
    }
}

