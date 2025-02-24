package bikerentmodel;

class Worker extends TableDB{

    protected static boolean updatePassword(String login, String password){
        String passmd5 = User.pass_change(password);
        db.query("update workers set password = ? where login = ?", new String[]{passmd5, login}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }

//    protected static String getById(long id){
//        db.query("SELECT login FROM workers WHERE user_id=?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
//        if ((boolean) db.res[0][0]){
//            return (String) db.res[1][0];
//        }
//        return null;
//    }

    protected static Long getId(String login){
        db.query("SELECT user_id FROM workers WHERE login=?", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (Long) db.res[1][0];
        }
        return null;
    }

    protected static String getShop(String login){
        db.query("SELECT shop_name FROM shop_workers WHERE login=?", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (String) db.res[1][0];
        }
        return null;
    }

    protected static boolean setShop(String login, String shop_name){
        String q = "INSERT INTO shop_workers (login, shop_name) VALUES (?,?)";
        db.query(q, new String[]{login, shop_name}, BikeDB.CRUD.C);
        return  (boolean) db.res[0][0];
    }

    protected static boolean updateShop(String login, String shop_name){
        String q = "UPDATE shop_workers SET shop_name=? WHERE login=?";
        db.query(q, new String[]{shop_name, login}, BikeDB.CRUD.U);
        return  (boolean) db.res[0][0];
    }

    protected static boolean delete(String login){
        db.query("DELETE FROM workers WHERE login=?", new String[]{login}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean clearShop(String login){
        db.query("DELETE FROM shop_workers WHERE login=?", new String[]{login}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }
}
