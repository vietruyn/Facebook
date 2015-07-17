package info.androidhive.listviewfeed.data;

/**
 * Created by levietruyn on 29/06/2015.
 */
public class CommentItem {
    int id, countLike, avt;
    String name, comment;
    boolean like;
    public CommentItem(){}

        public CommentItem(int id, String name, int avt, String comment, boolean like, int countLike) {
            super();
            this.id = id;
            this.avt =avt;
            this.name = name;
            this.comment = comment;
            this.like = like;
            this.countLike = countLike;
        }
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public boolean getLike() {
        return like;
    }

    public boolean setLike(boolean like) {
        this.like = like;
        return like;
    }
    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getCountLike() {
        return countLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public int getAvt() {
        return avt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
