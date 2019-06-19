package beans;

public class BookBean {
	
	public String name;
	public static final String NAME="name";
	
	public String author;
	public static final String AUTHOR="author";
	
	public String year;	
	public static final String YEAR="year";
	
	public String price;
	public static final String PRICE="price";
	
	
	
	public BookBean() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public BookBean(String name, String author, String year, String price) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookBean [name=" + name + ", author=" + author + ", year=" + year + ", price=" + price + "]";
	}
	
	

}
