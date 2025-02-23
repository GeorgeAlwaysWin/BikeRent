package bikerentmodel;

import java.util.HashMap;
import java.util.regex.Pattern;

class Admin extends TableDB{
    public long id;
    public String login;

    protected Admin(String login, String passport, String address){
        this.login = login;
    }

    protected static boolean updatePassword(long id, String password){
        String passmd5 = User.pass_change(password);
        db.query("update admin set password = ? where user_id = ?", new String[]{passmd5, Long.toString(id)}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }
}
