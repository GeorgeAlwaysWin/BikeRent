package bikerentmodel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

public class BikeRentModel {
    private static User active_user = User.getInstance();

    public static boolean Authorization(String login, String password, boolean reg){
        if (reg){
            return User.reg(login, password);
        } else {return User.auth(login, password);}
    }

    public static boolean updateClientAddress(String address){
        return Client.updateAddress(active_user.id, address);
    }

    public static boolean updateClientPassport(String passport){
        return Client.updatePassport(active_user.id, passport);
    }

    public static boolean updatePassword(String old_pass, String new_pass){
        if (User.checkPassword(active_user.login, old_pass)) {
            switch (active_user.role){
                case client -> {
                    return Client.updatePassword(active_user.id, new_pass);
                }
                case admin -> {
//                    return Admin.updatePassword(active_user.id, new_pass);
                }
                case worker -> {
//                    return Worker.updatePassword(active_user.id, new_pass);
                }
            }
        }
        return false;
    }

    public static String getActiveUserRole(){
        return Roles.get_role(active_user.id).toString();
    }

    public static Object[][] getAllShops(){
        return Shop.getAllShops();
    }

    public static Object[][] getShopsByModel(String model){
        return Shop.getShopsByModel(model);
    }

    public static String getAddressByShopName(String shop_name){
        return Shop.getAddressByShopName(shop_name);
    }

    public static Object[] getActiveClientBooking(){
        return Booking.getClientBooking(active_user.id);
    }

    public static boolean cancelActiveClientBooking(){
        return Booking.cancelClientBooking(active_user.id);
    }

    public static boolean cancelOutdatedBookings(){
        return Booking.cancelOutdatedBookings();
    }

    public static boolean bookBike(String bike_model, String shop_name, LocalDate start_date){
        return Booking.bookBike(active_user.id, bike_model, shop_name, start_date);
    }

    public static Object[][] getAllModels(){
        return (Object[][]) Model.getAllModels();
    }

    public static HashMap<String, String> getClientInfo(){
        if (active_user.login != null){
            return Client.getClient(active_user.id);
        } else {return null;}
    }

    public static void LogOut(){
        active_user.logout();
    }

    public static LocalDate dateToLocalDate(Date date){
        return BikeDB.dateToLocaldate(date);
    }

    public static boolean checkRentByBooking(long book_id){
        return Rent.checkRentByBooking(book_id);
    }

    public static void deleteShop(String shop_name){
        Shop.deleteShop(shop_name);
    }

    public static void createShop(String shop_name, String address){
        Shop.createShop(shop_name, address);
    }

    public static void updateShop(String old_shop_name, String new_shop_name, String address){
        Shop.updateShop(old_shop_name, new_shop_name, address);
    }

    public static void main(String[] args) {
        Client.updatePassword(39, "123");
        System.out.print(User.checkPassword("abc", "123"));
    }
}
