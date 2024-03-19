package data.datasource;

public class ForeignKeyInformation {

    boolean isUnique;

    String tableOrigine;

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public String getTableOrigine() {
        return tableOrigine;
    }

    public void setTableOrigine(String tableOrigine) {
        this.tableOrigine = tableOrigine;
    }
}
