package bikerentmodel;

class Admin extends TableDB{
    public long id;
    public String login;

    protected Admin(String login, String passport, String address){
        this.login = login;
    }

    protected static boolean updatePassword(String login, String password){
        String passmd5 = User.pass_change(password);
        db.query("update admins set password = ? where login = ?", new String[]{passmd5, login}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

    protected static String getById(long id){
        db.query("SELECT login FROM admins WHERE user_id=?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (String) db.res[1][0];
        }
        return null;
    }

    protected static Long getId(String login){
        db.query("SELECT user_id FROM admins WHERE login=?", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (Long) db.res[1][0];
        };
        return null;
    }

    protected static boolean delete(String login){
        db.query("DELETE FROM admins WHERE login=?", new String[]{login}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }
}
