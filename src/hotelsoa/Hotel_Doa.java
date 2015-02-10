
package hotelsoa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hotel_Doa {
    DB_Accessor db;
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/hotel_db";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "admin";
    
    public Hotel_Doa() {
            db = new DB_MySql();
    }
    
    public void openDbConnection() throws ClassNotFoundException, SQLException {
        db.openConnection(DRIVER_CLASS, URL, USER_NAME, PASSWORD);
    }
    
    public void addNewHotel(Hotel hotel) throws ClassNotFoundException, SQLException {
        this.openDbConnection();
        
        List columnNames = new ArrayList();
        columnNames.add("hotel_id");
        columnNames.add("hotel_name");
        columnNames.add("street_address");
        columnNames.add("city");
        columnNames.add("state");
        columnNames.add("postal_code");
        columnNames.add("note");
        
        List hotelValues = new ArrayList();
        hotelValues.add(hotel.getHotelID());
        hotelValues.add(hotel.getHotelName());
        hotelValues.add(hotel.getStreetAddress());
        hotelValues.add(hotel.getCity());
        hotelValues.add(hotel.getState());
        hotelValues.add(hotel.getPostalCode());
        hotelValues.add(hotel.getNotes());
        
        db.insertRecord("hotel", columnNames, hotelValues);
    }
    
    public void removeHotel(Hotel hotel) throws ClassNotFoundException, SQLException {
        this.openDbConnection();
        
        String hotelId = Integer.toString(hotel.getHotelID());
        
        db.deleteRecord("hotel", "hotel_id", hotelId);
    }
    
    public void updateHotelInfo(Hotel hotel) throws ClassNotFoundException, SQLException {
        this.openDbConnection();
        
        List columnNames = new ArrayList();
        columnNames.add("hotel_id");
        columnNames.add("hotel_name");
        columnNames.add("street_address");
        columnNames.add("city");
        columnNames.add("state");
        columnNames.add("postal_code");
        columnNames.add("note");
        
        List hotelValues = new ArrayList();
        hotelValues.add(hotel.getHotelID());
        hotelValues.add(hotel.getHotelName());
        hotelValues.add(hotel.getStreetAddress());
        hotelValues.add(hotel.getCity());
        hotelValues.add(hotel.getState());
        hotelValues.add(hotel.getPostalCode());
        hotelValues.add(hotel.getNotes());
        
        db.updateRecord("hotel", columnNames, hotelValues, "hotel_id", Integer.toString(hotel.getHotelID()));
    }
    
    public Hotel findHotelById (int hotelID) throws ClassNotFoundException, SQLException {
        this.openDbConnection();
        
        Map record = db.findRecordById("hotel", "hotel_id", hotelID);
        
        Hotel hotel = new Hotel();
        
        hotel.setHotelID(Integer.parseInt(record.get("hotel_id").toString()));
        hotel.setHotelName(record.get("hotel_name").toString());
        hotel.setStreetAddress(record.get("street_address").toString());
        hotel.setCity(record.get("city").toString());
        hotel.setState(record.get("state").toString());
        hotel.setPostalCode(record.get("postal_code").toString());
        hotel.setNotes(record.get("note").toString());
        return hotel;
    }
    
    public List findAllHotels() throws ClassNotFoundException, SQLException {
        this.openDbConnection();
        
        List allHotels = new ArrayList();
        String sqlStmt = "SELECT * FROM HOTEL";
        
        allHotels = db.findRecords(sqlStmt);
 
        return allHotels;
    }
    
    public List findHotelsByState(String state) throws ClassNotFoundException, SQLException {
        this.openDbConnection();
        
        List hotelsState = new ArrayList();
        String sqlStmt = "SELECT * FROM HOTEL WHERE STATE = '" + state + "'";
        
        hotelsState = db.findRecords(sqlStmt);
        
        return hotelsState;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Hotel_Doa hDoa = new Hotel_Doa();
//        System.out.println(hDoa.findHotelById(1).getHotelID());
//        System.out.println(hDoa.findHotelById(1).getHotelName());
//        System.out.println(hDoa.findHotelById(1).getStreetAddress());
//        System.out.println(hDoa.findHotelById(1).getNotes());
        Hotel newHotel = new Hotel();
        newHotel.setHotelID(4);
        newHotel.setHotelName("French Quarter Inn");
        newHotel.setStreetAddress("166 Church St");
        newHotel.setCity("Charleston");
        newHotel.setState("SC");
        newHotel.setPostalCode("29401");
        newHotel.setNotes("Free Breakfast, Free Wifi, Room Service Available");
//        hDoa.addNewHotel(newHotel);
        
        hDoa.updateHotelInfo(newHotel);
        System.out.println(hDoa.findHotelById(4).getNotes());
        
    }
}
