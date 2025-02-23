package bikerentmodel;

class Roles extends TableDB {
    protected enum roles {client, worker, admin};
    protected static roles get_role(long id) {
        db.query("SELECT role FROM roles WHERE user_id=?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return roles.valueOf((String) db.res[1][0]);
        }
        return null;
    }
}
