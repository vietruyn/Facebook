package info.androidhive.listviewfeed.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rom on 4/20/2015.
 */
public class Blog implements Serializable {
    private ArrayList<String> html_attributions;
    private String next_page_token;
    private ArrayList<Result> results;
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public ArrayList<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(ArrayList<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
