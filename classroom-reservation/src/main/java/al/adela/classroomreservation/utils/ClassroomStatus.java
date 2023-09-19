package al.adela.classroomreservation.utils;

public enum ClassroomStatus {

    AVAILABLE ("A"),
    BUSY ("B");

    private String value;

    ClassroomStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
