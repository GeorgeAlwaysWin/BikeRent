package bikerentmodel;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class BikeRentModel {
    private static User active_user = User.getInstance();

    public static boolean Authorization(String login, String password, boolean reg){
        if (reg){
            return User.reg(login, User.pass_change(password), "client");
        } else {return User.auth(login, User.pass_change(password));}
    }

    public static boolean regUser(String login, String password, String role){
        return User.reg(login, password, role);
    }

    public static boolean regHashUser(String login, String password, String role){
        return User.reg(login, User.pass_change(password), role);
    }

    public static boolean updateClientAddress(String address){
        return Client.updateAddress(active_user.login, address);
    }

    public static boolean updateClientPassport(String passport){
        return Client.updatePassport(active_user.login, passport);
    }

    public static boolean updateActiveUserPassword(String old_pass, String new_pass){
        if (User.checkPassword(active_user.login, old_pass)) {
            return updatePassword(active_user.login, active_user.role, new_pass);
        }
        return false;
    }

    public static Roles.roles stringToRole(String role){
        return Roles.stringToRole(role);
    }

    public static boolean updatePassword(String login, Roles.roles role, String new_pass){
        switch (role){
            case client -> {
                return Client.updatePassword(login, new_pass);
            }
            case admin -> {
                return Admin.updatePassword(login, new_pass);
            }
            case worker -> {
                return Worker.updatePassword(login, new_pass);
            }
        }
        return false;
    }

    public static String getActiveUserRole(){
        return Roles.get_role(active_user.login).toString();
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
        return Booking.getClientBooking(active_user.login);
    }

    public static boolean cancelActiveClientBooking(){
        return Booking.cancelClientBooking(active_user.login);
    }

//    public static boolean cancelOutdatedBookings(){
//        return Booking.cancelOutdatedBookings();
//    }

    public static boolean bookBike(String bike_model, String shop_name, LocalDate start_date){
        return Booking.bookBike(active_user.login, bike_model, shop_name, start_date);
    }

    public static Object[][] getAllModels(){
        return (Object[][]) Model.getAllModels();
    }

    public static HashMap<String, String> getClientInfo(){
        if (active_user.login != null){
            return Client.get(active_user.login);
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

    public static Object[] getUser(String login){
        return User.getUser(login);
    }

    public static String getActiveUser(){
        return active_user.login;
    }

    public static boolean deleteUser(String login){
        Roles.roles role = Roles.get_role(login);
        switch (role){
            case client -> {
                Long id = Client.getId(login);
                Client.delete(login);
                Roles.deleteRoleById(id);
                id = (Long) Booking.getId(login);
                if (id!=null) {
                    Booking.delete(login);
                    Rent.delete(id);
                }
                return true;
            }
            case admin -> {
                Long id = Admin.getId(login);
                Admin.delete(login);
                Roles.deleteRoleById(id);
                return true;
            }
            case worker -> {
                long id = Worker.getId(login);
                Worker.delete(login);
                Roles.deleteRoleById(id);
                Worker.clearShop(login);
                return true;
            }
        }
        return false;
    }

    public static boolean updateUserRole(String login, String role){
        String pass = User.getPassword(login);
        deleteUser(login);
        return regUser(login, pass, role);
    }

    public static String getShop(){
        return Worker.getShop(active_user.login);
    }

    public static boolean setWorkerShop(String shop_name){
        return Worker.setShop(active_user.login, shop_name);
    }

    public static boolean updateWorkerShop(String shop_name){
        return Worker.updateShop(active_user.login, shop_name);
    }

    public static HashMap<String, Object> getBookingById(String id){
        Object[] booking = Booking.getBookingById(id);
        if (booking != null) {
            HashMap<String, Object> key_booking = new HashMap<>();
            key_booking.put("bike_model", booking[1]);
            key_booking.put("shop_name", booking[2]);
            key_booking.put("start_date", booking[3]);
            key_booking.put("book_id", booking[5]);
            return key_booking;
        }
        return null;
    }

    public static boolean setBookingEnd(Long book_id, LocalDateTime time){
        return Booking.setBookingEnd(book_id, time);
    }

    public static boolean endRent(Long book_id){
        return Rent.delete(book_id);
    }

    public static boolean startRent(Long book_id, Long bike_id){
        return Rent.create(book_id, bike_id);
    }

    public static Long getBike(String model_name){
        return Bike.getBikeForRent(Worker.getShop(active_user.login), model_name);
    }

    public static Object[][] getNotRentedBikesFromShop(){
        return Bike.getAllNotRentedBikesFromShop(Worker.getShop(active_user.login));
    }

    public static boolean updateBikeModel(String old_model_name, String model_name, String type, String gears){
        return Model.updateBikeModel(old_model_name, model_name, type, gears);
    }

    public static boolean deleteBikeModel(String model_name){
        if (Bike.checkBikeByModel(model_name)){return false;}
        return Model.deleteBikeModel(model_name);
    }

    public static boolean deleteBike(String id){
        return Bike.deleteBike(id);
    }

    public static boolean createBike(String model){
        return Bike.createBike(model, Worker.getShop(active_user.login));
    }

    public static boolean createBikeModel(String model_name, String type, String gears){
        return Model.createBikeModel(model_name, type, gears);
    }

    public static void main(String[] args) {
//        Client.updatePassword(39, "123");
//        System.out.print(User.checkPassword("abc", "123"));
    }
}
