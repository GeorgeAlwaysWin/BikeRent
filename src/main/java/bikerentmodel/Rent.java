package bikerentmodel;

class Rent extends TableDB{

    protected static boolean checkRentByBooking(long id){
        db.query("SELECT book_id FROM rent WHERE book_id = ?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
        return (boolean) db.res[0][0];
    }

    protected static boolean delete(long book_id){
        db.query("DELETE FROM rent WHERE book_id=?", new String[]{Long.toString(book_id)}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean create(long book_id, long bike_id){
        String q = "INSERT INTO rent (book_id, bike_id) VALUES (?,?)";
        db.query(q, new String[]{Long.toString(book_id), Long.toString(bike_id)}, BikeDB.CRUD.C);
        return (boolean) db.res[0][0];
    }

}
