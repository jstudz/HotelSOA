
package hotelsoa;

import java.sql.SQLException;

public class HotelService {
    Hotel_Doa hDoa;
    
    public HotelService() {
        hDoa = new Hotel_Doa();
    }
    
    public Hotel findHotel(int hotelId) throws ClassNotFoundException, SQLException {  
        return hDoa.findHotelById(hotelId);
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HotelService hs = new HotelService();
        
        System.out.println(hs.findHotel(2).getHotelName());
    }
}
