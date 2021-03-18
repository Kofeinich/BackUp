import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is my class for backup our data
 */
public class BackUp {
    private final Map<String, File> currentFiles;
    private final List<Point> restorePoints;
    private long currentTime;

    public BackUp() {
        currentFiles = new HashMap<>();
        restorePoints = new ArrayList<>();
        currentTime = 0;
    }

    public Map<String, File> getCurrentFiles() {
        return currentFiles;
    }

    public List<Point> getRestorePoints() {
        return restorePoints;
    }

    public Integer getCountOfPoints(){
        return getRestorePoints().size();
    }

    public void show() {
        for (Point restorePoint : restorePoints) {
            System.out.println("Restore point, size = " + restorePoint.calculateSize() + ", creation time = " + restorePoint.getCreationTime());
            Map<String, File> files = restorePoint.getFiles();
            for (File file : files.values()) {
                System.out.println("Файл: " + file.getFilename() + ", размер: " + file.getSize());
            }
            System.out.println();
        }
    }

    public void cleanup(List<CleanStrategy> cleanupStrategies){
        for (CleanStrategy cleanupStrategy : cleanupStrategies){
            cleanupStrategy.execute(this);
        }
    }

    public void addFile(File file) {
        currentFiles.put(file.getFilename(), file);
    }

    public void createRestorePoint(CreateStrategy strategy, ContainerBuilder storage) {
        restorePoints.add(strategy.create(this, storage.build()));
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
