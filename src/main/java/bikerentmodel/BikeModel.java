package bikerentmodel;

import java.util.HashMap;

public class BikeModel {
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

    public static boolean updatePassword(String oldpass, String newpass){
        if (User.checkPassword(active_user.login, oldpass)) {
            return Client.updatePassword(active_user.id, newpass);
        } else {return false;}
    }

    public static HashMap<String, String> getClientInfo(){
        if (active_user.login != null){
            return Client.getClient(active_user.id);
        } else {return null;}
    }

    public static void LogOut(){
        active_user.logout();
    }

    public static void main(String[] args) {
//        BikeDB db = BikeDB.getInstance();
//        Object[][] res;
//        if (User.auth("abc", "1234")){
//            System.out.print("OK");
//        };

    }
}
