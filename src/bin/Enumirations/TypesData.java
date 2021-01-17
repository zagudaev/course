package bin.Enumirations;

public enum TypesData {
    NON_DATA(0,"NON_DATA"),
    NON_DEFINITELY(1,"NON_DEFINITELY"),
    INT(2,"INT"),
    REAL(3,"REAL"),
    BOOL(4,"BOOL");

    private int index;
    private String nameType;
    TypesData(int index,String nameType){
        this.index = index;
        this.nameType = nameType;
    }
    public int getIndex() {
        return index;
    }
    public String getNameType(){
        return nameType;
    }
}
