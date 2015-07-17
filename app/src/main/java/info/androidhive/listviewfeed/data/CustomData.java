package info.androidhive.listviewfeed.data;

/** This is just a simple class for holding data that is used to render our custom view */
public class CustomData {
    private int mBackgroundColor;
    private String mText;
    private int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public CustomData(int srcImage) {
        img=srcImage;
    }

    /**
     * @return the backgroundColor
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * @return the text
     */
    public String getText() {
        return mText;
    }
}
