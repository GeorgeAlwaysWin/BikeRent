package bikerentmodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

class Booking extends TableDB{
    public String  login;
    public String bike_model;
    public String shop_name;
    public Date start_date;
    public Timestamp end_date;
    public long book_id;

    protected Booking(String login, String bike_model, String shop_name, Date start_date, Timestamp end_date, long book_id){
        this.login = login;
        this.bike_model = bike_model;
        this.shop_name = shop_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.book_id = book_id;
    }

    protected static Object[] getClientBooking(String login){
        db.query("SELECT * FROM booking WHERE login = ? AND end_date IS NULL", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return db.res[1];
        }
        return null;
    }

    protected static Long getId(String login){
        db.query("SELECT book_id FROM booking WHERE login = ? AND end_date IS NULL", new String[]{login}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return (Long) db.res[1][0];
        }
        return null;
    }

    protected static boolean cancelClientBooking(String login){
        db.query("DELETE FROM booking WHERE login = ? AND end_date IS NULL", new String[]{login}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean delete(String login){
        db.query("DELETE FROM booking WHERE login = ?", new String[]{login}, BikeDB.CRUD.D);
        return (boolean) db.res[0][0];
    }

    protected static boolean bookBike(String login, String bike_model, String shop_name, LocalDate start_date){
        db.query("INSERT INTO booking (login, bike_model, shop_name, start_date) VALUES (?, ?, ?, ?)",
                new String[]{login, bike_model, shop_name, start_date.toString()}, BikeDB.CRUD.C);
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

    protected static Object[] getBookingById(String id){
        db.query("SELECT * FROM booking WHERE book_id=? AND end_date IS NULL", new String[]{id}, BikeDB.CRUD.R);
        if ((boolean) db.res[0][0]){
            return db.res[1];
        }
        return null;
    }

    protected static boolean setBookingEnd(Long booking_id, LocalDateTime end_time){
        String q = "UPDATE booking SET end_date=? WHERE book_id=?";
        db.query(q, new String[]{end_time.toString(), Long.toString(booking_id)}, BikeDB.CRUD.U);
        return (boolean) db.res[0][0];
    }
}
