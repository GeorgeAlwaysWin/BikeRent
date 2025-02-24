package bikerentmodel;

class Roles extends TableDB {
    protected enum roles {client, worker, admin};
    protected static roles get_role(String login) {
        db.query("SELECT roles.role FROM (SELECT user_id, login FROM admins UNION SELECT user_id, login FROM clients UNION SELECT user_id, login FROM workers) AS U JOIN roles ON roles.user_id=U.user_id  WHERE login=?", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return roles.valueOf((String) db.res[1][0]);
        }
        return null;
    }
//    protected static boolean delete_role_by_login(String login){
//        db.query("DELETE FROM roles WHERE user_id=(SELECT U.user_id FROM (SELECT user_id, login FROM admins UNION SELECT user_id, login FROM clients UNION SELECT user_id, login FROM workers) AS U WHERE login=?", new String[]{login}, BikeDB.CRUD.D);
//        return (boolean) db.res[0][0];
//    }
    protected static boolean deleteRoleById(long user_id){
        db.query("DELETE FROM roles WHERE user_id=?", new String[]{Long.toString(user_id)}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static roles stringToRole(String role){
        return roles.valueOf(role);
    }
}
