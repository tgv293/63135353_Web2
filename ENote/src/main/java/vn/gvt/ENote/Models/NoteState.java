package vn.gvt.ENote.Models;

public enum NoteState {
    HIGH("Cao"),
    MIDDLE("Trung bình"),
    LOW("Thấp");

    private String stateAsString;

    NoteState(String state) {
        this.stateAsString = state;
    }

    public String getStateAsString() {
        return stateAsString;
    }

    public static NoteState getByStringName(String state) {
        state = state.toLowerCase();

        switch (state) {
            case "high":
                return HIGH;
            case "middle":
                return MIDDLE;
            case "low":
                return LOW;
            default:
                throw new IllegalArgumentException("No such element in NoteState enum!");
        }
    }
}
