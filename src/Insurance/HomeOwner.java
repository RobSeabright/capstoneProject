package Insurance;

import java.util.Date;

public class HomeOwner extends User {

    public HomeOwner (int userId, String firstName, String lastName, Date dateOfBirth, String address, String city,
                      String province, String postalCode, String phoneNumber, String email, String gender) {

        super(userId, firstName, lastName, dateOfBirth, address, city, province, postalCode, phoneNumber,
                email, gender);

    }

}
