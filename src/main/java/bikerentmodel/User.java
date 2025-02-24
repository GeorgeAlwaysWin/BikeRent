package bikerentmodel;

import java.nio.charset.StandardCharsets;
import java.security.*;

class User {
//    protected long id;
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

    protected static String getPassword(String login){
        db.query("SELECT password FROM (SELECT login, password FROM admins UNION SELECT login, password FROM clients UNION SELECT login, password FROM workers) AS U WHERE login=?",new String[]{login},BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (String) db.res[1][0];
        }
        return null;
    }

    protected static boolean checkPassword(String login, String pass){
        String old_pass = getPassword(login);
        if (pass.equals(old_pass)){
            return true;
        }
//        if (old_pass != null){
//            String md5pass = md5hash(pass);
//            if (md5pass.equals(old_pass)){
//                return true;
//            }
//        }
        return false;
    }

    protected static boolean auth(String log, String pass){
        if (checkPassword(log, pass)){
            User user = User.getInstance();
            user.login = log;
            user.role = Roles.get_role(log);
            return true;
        }
        return false;
    }

    protected static boolean reg(String log, String pass, String role){
//        pass = md5hash(pass);
        db.query("SELECT * FROM (SELECT login FROM admins UNION SELECT login FROM clients UNION SELECT login FROM workers) AS U WHERE login=?",new String[]{log},BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return false;
        }
        db.query("INSERT INTO roles (role) VALUES (?)", new String[]{role}, BikeDB.CRUD.C);
        if (!(boolean) db.res[0][0]){
            return false;
        }
        db.query("SELECT MAX(user_id) FROM roles", null, BikeDB.CRUD.R);

        switch (Roles.stringToRole(role)){
            case client -> {
                db.query("INSERT INTO clients (user_id, login, password) VALUES (?, ?, ?)",
                        new String[]{Long.toString((Long) db.res[1][0]), log, pass}, BikeDB.CRUD.C);
            }
            case worker -> {
                db.query("INSERT INTO workers (user_id, login, password) VALUES (?, ?, ?)",
                        new String[]{Long.toString((Long) db.res[1][0]), log, pass}, BikeDB.CRUD.C);
            }
            case admin -> {
                db.query("INSERT INTO admins (user_id, login, password) VALUES (?, ?, ?)",
                        new String[]{Long.toString((Long) db.res[1][0]), log, pass}, BikeDB.CRUD.C);
            }

        }



        return (boolean) db.res[0][0];
    }

    protected void logout(){
        this.login = null;
        this.role = null;
    }

    protected static Object[] getUser(String login){
        db.query("SELECT U.login, roles.role FROM (SELECT user_id, login FROM admins UNION SELECT user_id, login FROM clients UNION SELECT user_id, login FROM workers) AS U JOIN roles ON U.user_id=roles.user_id  WHERE login=?",new String[]{login},BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return db.res[1];
        }
        return null;
    }

}

