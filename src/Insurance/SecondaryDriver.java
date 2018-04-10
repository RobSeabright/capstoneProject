package Insurance;

import java.util.Date;

public class SecondaryDriver extends Driver {

    public SecondaryDriver(int userId, String firstName, String lastName, Date dateOfBirth, String address, String city,
                           String province, String postalCode, String phoneNumber, String email, String gender,
                           String driversLicenceNumber, Date licenceYearIssued) {

        super (userId, firstName, lastName, dateOfBirth, address, city, province, postalCode, phoneNumber,
                email, gender, driversLicenceNumber, licenceYearIssued);

    }

}
