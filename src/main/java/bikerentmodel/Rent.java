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
        db.query("SELECT * FROM rent WHERE book_id = ?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
        return (boolean) db.res[0][0];
    }

}
