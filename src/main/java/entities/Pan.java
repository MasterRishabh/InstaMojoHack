
package entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pan {

    @SerializedName("textAngle")
    @Expose
    private Double textAngle;
    @SerializedName("orientation")
    @Expose
    private String orientation;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("regions")
    @Expose
    private List<Region> regions = null;

    public Double getTextAngle() {
        return textAngle;
    }

    public void setTextAngle(Double textAngle) {
        this.textAngle = textAngle;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

}
