import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is my class for backup our data
 */
public class BackUp {
    private  Map<String, File> curFiles;
    private List<Point> resPoints;
    private long curTime;

    public BackUp() {
        curFiles = new HashMap<>();
        resPoints = new ArrayList<>();
        curTime = 0;
    }

    public List<Point> getResPoints() {
        return resPoints;
    }

    public Map<String, File> getCurFiles() {
        return curFiles;
    }

    public Integer getCountOfPoints(){
        return getResPoints().size();
    }

    public void show() {
        for (Point restorePoint : resPoints) {
            System.out.println("Size = " + restorePoint.calculateSize() + ", creation time = " + restorePoint.getCreationTime());
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
        curFiles.put(file.getFilename(), file);
    }

    public void createRestorePoint(CreateStrategy strategy, ContainerBuilder storage) {
        resPoints.add(strategy.create(this, storage.build()));
    }

    public long getCurrentTime() {
        return curTime;
    }

    public void setCurrentTime(long currentTime) {
        this.curTime = currentTime;
    }
}
