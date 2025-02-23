package bikerentmodel;

import java.sql.*;

class BikeDB {
    private BikeDB(){};

    private static class BikeDatabaseHolder{
        public static final BikeDB HOLDER_INSTANCE = new BikeDB();
    }

    protected static BikeDB getInstance(){
        return BikeDatabaseHolder.HOLDER_INSTANCE;
    }
    protected enum CRUD {C, R, U, D}
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/bikerentschema";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement st;
    protected Object[][] res;
    /**
     * Returns Object[][] with boolean status true,false in [0][0] of array and data from select query
     */
    protected void query(String query, String[] args, CRUD query_type) {
        this.res = null;
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
//            System.out.println(con);
            st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (args!=null) {
                for (int i = 0; i < args.length; i++) {
                    st.setString(i + 1, args[i]);
                }
            }
            switch (query_type) {
                case C, D, U -> {
                    int rs = st.executeUpdate();
                    this.res = new Object[1][1];
                    if (rs > 0) {
                        this.res[0][0] = true;
                    } else {
                        this.res[0][0] = false;
                    }
                }
                case R -> {
                    ResultSet rs = st.executeQuery();
                    if (rs != null) {
                        ResultSetMetaData rs_info = rs.getMetaData();
                        int columns = rs_info.getColumnCount();
                        rs.last();
                        int rows = rs.getRow();
                        rs.beforeFirst();
                        //                    String[] datatypes = new String[columns];
                        //                    for (int i = 0; i < columns; i++) {
                        //                        rs_info.getColumnType(i);
                        //                    }
                        this.res = new Object[rows+1][columns];
                        if (rows == 0){
                            this.res[0][0] = false;
                        } else { this.res[0][0] = true; }
                        int j = 1;
                        while (rs.next()){
                            for (int i = 0; i < columns; i++) {
                                this.res[j][i] = rs.getObject(i+1);
                            }
                            j++;
                        }
                    } else {
                        this.res[0][0] = new Object[1][1];
                        this.res[0][0] = false;
                    }
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,st and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                st.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
