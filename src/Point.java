/**
 * So this is class which works with our Points
 * We can using Stream API for matching
 * @see Point#Point(Container, long)
 * @see Point.IncrementalPoint#IncrementalPoint(Container, long, Point)
 * @see Point.FattestForPoint#FattestForPoint(Container, long)
 */

import java.util.HashMap;
import java.util.Map;

public abstract class Point {
    protected final Container storage;
    private final long creationTime;

    protected Point(Container storage, long creationTime) {
        this.storage = storage;
        this.creationTime = creationTime;
    }

    public abstract Map<String, File> getFiles();
    public Integer calculateSize(){
        Integer size = 0;
        for(File file : getFiles().values()){
            size += file.getSize();
        }
        return size;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public static class IncrementalPoint extends Point {
        private final Point beforePoint;

        public IncrementalPoint(Container storage, long creationTime, Point beforePoint) {
            super(storage, creationTime);
            this.beforePoint = beforePoint;
        }

        @Override
        public Map<String, File> getFiles() {
            Map<String, File> files = new HashMap<>();
            Map<String, File> currentFiles = storage.getFiles();
            Map<String, File> beforeFiles;
            if (beforePoint != null) {
                beforeFiles = beforePoint.getFiles();
            } else {
                beforeFiles = new HashMap<>();
            }
            for (File file : currentFiles.values()) {
                if (beforeFiles.keySet().stream().anyMatch(name -> name.equals(file.getFilename()))) {
                    files.put(
                            file.getFilename(),
                            new File(
                                    file.getFilename(),
                                    file.getSize() + beforeFiles.get(file.getFilename()).getSize()
                            )
                    );
                } else {
                    files.put(file.getFilename(), new File(file.getFilename(), file.getSize()));
                }
            }
            return files;
        }
    }

    public static class FattestForPoint extends Point {
        public FattestForPoint(Container storage, long creationTime) {
            super(storage, creationTime);
        }
        @Override
        public Map<String, File> getFiles() {
            return storage.getFiles();
        }
    }
}
