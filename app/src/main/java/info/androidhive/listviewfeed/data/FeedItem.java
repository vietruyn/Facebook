package info.androidhive.listviewfeed.data;

public class FeedItem {
    private int id;
    private String name, status, image, profilePic, timeStamp, url;
    private boolean like;
    private int countLike, friend;

    public FeedItem() {
    }

    public FeedItem(int id, String name, String image, String status,
                    String profilePic, String timeStamp, String url, boolean like, int countLike) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.profilePic = profilePic;
        this.timeStamp = timeStamp;
        this.url = url;
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

    public String getImge() {
        return image;
    }

    public void setImge(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public int getFriend() {
        return friend;
    }

    }

