public class DateOfTheEvent {
    private final int day;
    private final String month;

    public DateOfTheEvent(int day, String month) {
        this.day = day;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Date: " + day + " " + month.toUpperCase();
    }

}

