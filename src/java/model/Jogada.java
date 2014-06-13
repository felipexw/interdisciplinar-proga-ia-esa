package model;

/**
 *
 * @author Felipe
 */
public class Jogada {

    private Integer id;
    private Integer i;
    private Integer j;
    private Integer buttonIndex;
    private String contentValue;

    public Jogada(Integer i, Integer j, Integer id, Integer buttonIndex, String contentValue) {
        this.id = id;
        this.i = i;
        this.j = j;
        this.buttonIndex = buttonIndex;
        this.contentValue = contentValue;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public void setButtonIndex(Integer buttonIndex) {
        this.buttonIndex = buttonIndex;
    }

    public Integer getButtonIndex() {
        return buttonIndex;
    }

    public final Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public final Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public final Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "ChatMessage [i=" + i + ", j=" + j
                + ", id=" + id + "]";
    }

}
