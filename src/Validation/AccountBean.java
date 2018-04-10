package Validation;

import Insurance.*;
import Managers.AccidentManager;
import Managers.AccountManager;
import Managers.ProfileSelect;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name = "AccountBean")
public class AccountBean implements Serializable{

    private int userId;
    private String email;
    private String password;
    private Boolean validationFlag = true;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getValidationFlag() {
        return validationFlag;
    }

    public void setValidationFlag(Boolean validationFlag) {
        this.validationFlag = validationFlag;
    }

    public String createAccount() {
        Managers.AccountManager manager = new AccountManager();
        manager.createNewAccount(getEmail(), getPassword());

        return "login?faces-redirect=true";
    }

    public String login() {
        Managers.AccountManager manager = new AccountManager();
        ArrayList<Object> validationList = manager.accountLogin(getEmail(), getPassword());

        validationFlag = true;

        if (validationList.get(0).equals(true)) {
            ProfileSelect profileSelect = new ProfileSelect();
            Account account = (Account) validationList.get(1);
            setUserId(account.getUserId());
            ArrayList profileList = profileSelect.select(userId);

            FacesContext facesContext = FacesContext.getCurrentInstance();

            for (Object object :profileList) {
                if (object instanceof PrimaryDriver) {

                    UserBean userBean = (UserBean) facesContext.getApplication() .createValueBinding("#{UserBean}").getValue(facesContext);

                    userBean.setUserId(userId);
                    userBean.setFirstName(((PrimaryDriver) object).getFirstName());
                    userBean.setLastName(((PrimaryDriver) object).getLastName());
                    userBean.setDob(((PrimaryDriver) object).getDateOfBirth().toString());
                    userBean.setAddress(((PrimaryDriver) object).getAddress());
                    userBean.setCity(((PrimaryDriver) object).getCity());
                    userBean.setProvince(((PrimaryDriver) object).getProvince());
                    userBean.setPostalCode(((PrimaryDriver) object).getPostalCode());
                    userBean.setEmail(((PrimaryDriver) object).getEmail());
                    userBean.setGender(((PrimaryDriver) object).getGender());
                    userBean.setLicenseNumber(((PrimaryDriver) object).getDriversLicenceNumber());
                    userBean.setLicenseDate(((PrimaryDriver) object).getLicenceIssuedDate().toString());
                }
                if (object instanceof HomeOwner) {

                    UserBean userBean = (UserBean) facesContext.getApplication() .createValueBinding("#{UserBean}").getValue(facesContext);

                    userBean.setUserId(userId);
                    userBean.setFirstName(((HomeOwner) object).getFirstName());
                    userBean.setLastName(((HomeOwner) object).getLastName());
                    userBean.setDob(((HomeOwner) object).getDateOfBirth().toString());
                    userBean.setAddress(((HomeOwner) object).getAddress());
                    userBean.setCity(((HomeOwner) object).getCity());
                    userBean.setProvince(((HomeOwner) object).getProvince());
                    userBean.setPostalCode(((HomeOwner) object).getPostalCode());
                    userBean.setEmail(((HomeOwner) object).getEmail());
                    userBean.setGender(((HomeOwner) object).getGender());
                }
                if (object instanceof HomeQuote) {

                    Home home = ((HomeQuote) object).getHome();
                    HomeQuoteBean homeQuoteBean = (HomeQuoteBean) facesContext.getApplication() .createValueBinding("#{HomeQuoteBean}").getValue(facesContext);

                    homeQuoteBean.setHome(home);
                    homeQuoteBean.setQuoteID(((HomeQuote) object).getQuoteID());
                    homeQuoteBean.setStartDate(((HomeQuote) object).getStartDate());
                    homeQuoteBean.setEndDate(((HomeQuote) object).getEndDate());
                    homeQuoteBean.setBasePremium(((HomeQuote) object).getBasePremium());
                    homeQuoteBean.setTax(((HomeQuote) object).getTax());
                    homeQuoteBean.setTotal(((HomeQuote) object).getTotal());
                    homeQuoteBean.setHomeYear(home.getYearBuilt());
                    homeQuoteBean.setHomeType(home.getHomeType());
                    homeQuoteBean.setHomeTypeDisplay();
                    homeQuoteBean.setHeatingType(home.getHeatingType());
                    homeQuoteBean.setHomeTypeDisplay();
                    homeQuoteBean.setAddress(home.getAddress());
                    homeQuoteBean.setCity(home.getCity());
                    homeQuoteBean.setProvince(home.getProvince());
                    homeQuoteBean.setPostalCode(home.getPostalCode());
                }
                if (object instanceof HomePolicy) {

                    Home home = ((HomePolicy) object).getHome();
                    HomePolicyBean homePolicyBean = (HomePolicyBean) facesContext.getApplication() .createValueBinding("#{HomePolicyBean}").getValue(facesContext);

                    homePolicyBean.setHome(home);
                    homePolicyBean.setPolicyID(((HomePolicy) object).getPolicyID());
                    homePolicyBean.setStartDate(((HomePolicy) object).getStartDate());
                    homePolicyBean.setEndDate(((HomePolicy) object).getEndDate());
                    homePolicyBean.setBasePremium(((HomePolicy) object).getBasePremium());
                    homePolicyBean.setTax(((HomePolicy) object).getTax());
                    homePolicyBean.setTotal(((HomePolicy) object).getTotal());
                    homePolicyBean.setHomeYear(home.getYearBuilt());
                    homePolicyBean.setHomeType(home.getHomeType());
                    homePolicyBean.setHomeTypeDisplay();
                    homePolicyBean.setHeatingType(home.getHeatingType());
                    homePolicyBean.setHomeTypeDisplay();
                    homePolicyBean.setAddress(home.getAddress());
                    homePolicyBean.setCity(home.getCity());
                    homePolicyBean.setProvince(home.getProvince());
                    homePolicyBean.setPostalCode(home.getPostalCode());
                }
                if (object instanceof VehicleQuote) {

                    Vehicle vehicle = ((VehicleQuote) object).getVehicle();
                    VehicleQuoteBean vehicleQuoteBean = (VehicleQuoteBean) facesContext.getApplication() .createValueBinding("#{VehicleQuoteBean}").getValue(facesContext);
                    AccidentManager accidentManager = new AccidentManager();

                    vehicleQuoteBean.setQuoteID(((VehicleQuote) object).getQuoteID());
                    vehicleQuoteBean.setStartDate(((VehicleQuote) object).getStartDate());
                    vehicleQuoteBean.setEndDate(((VehicleQuote) object).getEndDate());
                    vehicleQuoteBean.setBasePremium(((VehicleQuote) object).getBasePremium());
                    vehicleQuoteBean.setTax(((VehicleQuote) object).getTax());
                    vehicleQuoteBean.setTotal(((VehicleQuote) object).getTotal());

                    //Calls the accident manager to return accidents in the past 5 years + accidents in the past 10
                    // years for the user and sets it to accidents total
                    vehicleQuoteBean.setAccidentsTotal(accidentManager.countFive(((VehicleQuote) object).getUser().getUserId()) +
                            accidentManager.countTen(((VehicleQuote) object).getUser().getUserId()));
                    vehicleQuoteBean.setVehicleValue(((VehicleQuote) object).getReplacementCost());
                    vehicleQuoteBean.setVehicleMake(vehicle.getMake());
                    vehicleQuoteBean.setVehicleModel(vehicle.getModel());
                    vehicleQuoteBean.setVehicleYear(vehicle.getYear());
                    vehicleQuoteBean.setPlateNumber(vehicle.getPlateNumber());
                }
                if (object instanceof VehiclePolicy) {

                    Vehicle vehicle = ((VehiclePolicy) object).getVehicle();
                    VehiclePolicyBean vehiclePolicyBean = (VehiclePolicyBean) facesContext.getApplication()
                            .createValueBinding("#{VehiclePolicyBean}").getValue(facesContext);
                    AccidentManager accidentManager = new AccidentManager();

                    vehiclePolicyBean.setPolicyID(((VehiclePolicy) object).getPolicyID());
                    vehiclePolicyBean.setStartDate(((VehiclePolicy) object).getStartDate());
                    vehiclePolicyBean.setEndDate(((VehiclePolicy) object).getEndDate());
                    vehiclePolicyBean.setBasePremium(((VehiclePolicy) object).getBasePremium());
                    vehiclePolicyBean.setTax(((VehiclePolicy) object).getTax());
                    vehiclePolicyBean.setTotal(((VehiclePolicy) object).getTotal());

                    //Calls the accident manager to return accidents in the past 5 years + accidents in the past 10
                    // years for the user and sets it to accidents total
                    vehiclePolicyBean.setAccidentsTotal(accidentManager.countFive(((VehiclePolicy) object).getUser().getUserId()) +
                    accidentManager.countTen(((VehiclePolicy) object).getUser().getUserId()));
                    vehiclePolicyBean.setVehicleValue(vehicle.getVehicleValue());
                    vehiclePolicyBean.setVehicleMake(vehicle.getMake());
                    vehiclePolicyBean.setVehicleModel(vehicle.getModel());
                    vehiclePolicyBean.setVehicleYear(vehicle.getYear());
                    vehiclePolicyBean.setPlateNumber(vehicle.getPlateNumber());
                }
            }

            return "viewUserProfile?faces-redirect=true";
        } else {
            validationFlag = false;
            return "login?faces-redirect=true";
        }

    }
}
