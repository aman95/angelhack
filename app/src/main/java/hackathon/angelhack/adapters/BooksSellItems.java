package hackathon.angelhack.adapters;

/**
 * Created by nitin on 21/6/15.
 */
public class BooksSellItems {
    private String name;
    private String author;
    private String price;

    private int id;


    public BooksSellItems(String name, String author, String price)
    {
        this.name = name;
        this.author = author ;

        this.price = price;

        this.id = id;
    }

    public String getBookName() {
        return name;
    }
    public String getAuthor () {
        return author;
    }
    public String getPrice() {
        return price;
    }


    public void setCompanyName(String title) {
        this.name =title;
    }

}
