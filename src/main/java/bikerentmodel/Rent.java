package bikerentmodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;

class Rent extends TableDB{
    public long user_id;
    public String bike_model;
    public String shop_name;
    public Date start_date;
    public Timestamp end_date;
    public long book_id;

    protected Rent(long book_id, String shop_name){
        this.book_id = book_id;
        this.shop_name = shop_name;
    }

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
