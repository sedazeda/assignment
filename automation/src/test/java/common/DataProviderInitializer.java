package common;

public class DataProviderInitializer {

    public DataProviderInitializer() {
    }

    public static String[][] createValidDataForAdd() {
        return new String[][]{
                {"Sushi Laptop2", "2000-12-12", "2020-12-12", "IBM", "valid"},
                {"Personal usage2", "2000-12-12", "2020-12-12", "IBM", "valid"},
                {"Laptop2 for their home", "2000-12-12", "2020-12-12", "IBM", "valid"},
                {"2Our Home PC", "2000-12-12", "2020-12-12", "", "valid"}
        };
    }

    public static String[][] createDeleteData() {
        return new String[][]{
                {"aisdkasid13132", "2000-12-12", "2020-12-12", "IBM", "valid"},
                {"Personal", "2000-12-12", "2020-12-12", "IBM", "valid"},
                {"Laptop for kitchen", "2000-12-12", "2020-12-12", "IBM", "valid"},
                {"2My Roof PC", "2000-12-12", "2020-12-12", "", "valid"}
        };
    }

    public static String[][] createInvalidDataForAdd() {
        return new String[][]{
                {"", "2000-12-12", "2020-12-12", "IBM", "invalid"},
                {"Seda PC22", "2000-12-12", "200011-12-12", "IBM", "invalid"},
                {"Comic 2Laptop for room", "12-12", "Jan 20", "IBM", "invalid"},
                {"2Purple Home PC", "200560-12-12", "2020-12-12", "IBM", "invalid"}
        };
    }


    public static String[][] validDataForEdit() {
        return new String[][]{
                {"My Computer 20192", "2001-12-12", "1988-12-12", "IBM", "valid"},
                {"Personal 20082", "2002-12-12", "1987-12-12", "IBM", "valid"},
                {"Laptop for2 my home NEW", "2000-12-12", "1986-12-12", "IBM", "valid"},
                {"My new Room PC200", "2003-12-10", "1985-12-12", "IBM", "valid"}
        };
    }

    public static String[][] invalidDataForEdit() {
        return new String[][]{
                {"", "invaliddate", "2020-12-12", "IBM", "invalid"},
                {"PersonalLovelyLaptop2", "2000-12-12", "200011-12-12", "IBM", "invalid"},
                {"Laptop 2for my Home 2019", "12-12", "Jan 20", "IBM", "invalid"},
                {"Our Home 2PC", "200560-12-12", "2020-12-12", "", "invalid"}
        };
    }
}
