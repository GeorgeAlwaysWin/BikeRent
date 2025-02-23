package bikerentmodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;

class Booking extends TableDB{
    public long user_id;
    public String bike_model;
    public String shop_name;
    public Date start_date;
    public Timestamp end_date;
    public long book_id;

    protected Booking(long user_id, String bike_model, String shop_name, Date start_date, Timestamp end_date, long book_id){
        this.user_id = user_id;
        this.bike_model = bike_model;
        this.shop_name = shop_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.book_id = book_id;
    }

    protected static Object[] getClientBooking(long id){
        db.query("SELECT * FROM booking WHERE user_id = ?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return db.res[1];
        }
        return null;
    }

//    protected static boolean isClientHasBooking(long id){
//        db.query("SELECT * FROM booking WHERE user_id = ?", new String[]{Long.toString(id)}, BikeDB.CRUD.R);
//        return (boolean) db.res[0][0];
//    }

    protected static boolean cancelClientBooking(long id){
        db.query("DELETE FROM booking WHERE user_id = ?", new String[]{Long.toString(id)}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean bookBike(long user_id, String bike_model, String shop_name, LocalDate start_date){
        db.query("INSERT INTO booking (user_id, bike_model, shop_name, start_date) VALUES (?, ?, ?, ?)",
                new String[]{Long.toString(user_id), bike_model, shop_name, start_date.toString()}, BikeDB.CRUD.C);
        return (boolean) db.res[0][0];
    }

    protected static boolean cancelOutdatedBookings(){
        db.query("DELETE FROM booking WHERE book_id NOT IN (select rent.book_id from rent) AND start_date < curdate()", new String[]{}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static Object[][] getTodayBookings(){
        db.query("SELECT * FROM booking WHERE start_date=curdate()", new String[]{}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return Arrays.copyOfRange(db.res, 1, db.res.length);
        }
        return null;
    }
}
