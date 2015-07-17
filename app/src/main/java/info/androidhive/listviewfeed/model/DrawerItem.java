package info.androidhive.listviewfeed.model;

public class DrawerItem {

	String ItemName;
	int imgResID;
	String title;
	String Text;

	public DrawerItem(String itemName, int imgResID) {
		ItemName = itemName;
		this.imgResID = imgResID;
	}

	public DrawerItem(String title) {
		this(null,0);
		this.title = title;
	}


	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}


}
