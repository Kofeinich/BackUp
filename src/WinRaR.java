/**
 * This is pretty imitation of really existing WinRaR archive
 */

import java.util.Map;

public class WinRaR {
    private Map<String, File> files;

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public Map<String, File> getFiles() {
        return files;
    }
}
