package ntu.quanndm.recyclerview;

public class LandScape {
    String landImageFileName;
    String landCaption;
    //Generate Contrustion
    public LandScape(String landImageFileName, String landCaption) {
        this.landImageFileName = landImageFileName;
        this.landCaption = landCaption;
    }
    //Generate Getter Setter
    public String getLandImageFileName() {
        return landImageFileName;
    }

    public void setLandImageFileName(String landImageFileName) {
        this.landImageFileName = landImageFileName;
    }

    public String getLandCaption() {
        return landCaption;
    }

    public void setLandCaption(String landCaption) {
        this.landCaption = landCaption;
    }
}
