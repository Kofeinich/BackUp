/**
 * It's a pretty imitation of really existing file
 */

public class File {
    private String filename;
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFilename(){
        return filename;
    }

    public File(String filename, Integer size) {
        this.filename = filename;
        this.size = size;
    }
}
