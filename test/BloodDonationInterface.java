import java.time.LocalDateTime;

/**
* @author Yunpeng Lyu
*
* Code for BloodDonation Interface
*
*/

public interface BloodDonationInterface {
    String getDonationID();
    String getDonorName();
    String getBloodType();
    double getDonationAmount();
    void setDonationAmount(double amount);
    LocalDateTime getDonationDate();
    void setDonationDate(LocalDateTime date);
}
