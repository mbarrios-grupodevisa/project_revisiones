package gt.com.metrocasas.revisiones;

public class ItemRevision {
    String date = "DOS";

    public ItemRevision(){
    }

    public ItemRevision(String date)
    {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
