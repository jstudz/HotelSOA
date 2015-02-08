
package hotelsoa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    Hotel_Doa hDoa;
    
    public HotelService() {
        hDoa = new Hotel_Doa();
    }
    
    public Hotel findHotel(int hotelId) throws ClassNotFoundException, SQLException {  
        return hDoa.findHotelById(hotelId);
    }
    
    public List<Hotel> findAllHotels() throws ClassNotFoundException, SQLException {
        List<Hotel> allHotels = new ArrayList<Hotel>();
        
        return allHotels = hDoa.findAllHotels();
    }
    
    public void addHotel(Hotel hotel) throws ClassNotFoundException, SQLException {
        hDoa.addNewHotel(hotel);
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HotelService hs = new HotelService();
        
        System.out.println(hs.findAllHotels().toString());
    }
}
