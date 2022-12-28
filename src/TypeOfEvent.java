public class TypeOfEvent {
    private final String name;
    private final int peopleQty;

    public int getPeopleQty() {
        return peopleQty;
    }

    @Override
    public String toString() {
        return "Ceremony: " + name.toUpperCase() + " for " + peopleQty + " people";
    }

    public TypeOfEvent(String name, int peopleQty) {
        this.name = name;
        this.peopleQty = peopleQty;

    }

}

