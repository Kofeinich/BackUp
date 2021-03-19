/**
 * Here I am using GoF pattern Strategy which improve my architecture
 *
 */

import java.util.HashMap;
import java.util.Map;

public interface CreateStrategy {
    /**
     * @see FattestCreation#create (so this who I am creating a Point)
     */
    Point create(BackUp backup, Container storage);
    class FattestCreation implements CreateStrategy {
        /**
         * @param backup is object of my Backup
         * @param storage is type of Container Creation
         */
        @Override
        public Point create(BackUp backup, Container storage) {
            Map<String, File> fileMap = new HashMap<>();
            for (File file : backup.getCurFiles().values()){
                fileMap.put(file.getFilename(), new File(file.getFilename(), file.getSize()));
            }
            storage.setFiles(fileMap);
            return new Point.FattestForPoint(storage, backup.getCurrentTime());
        }
    }

    class IncrementalCreation implements CreateStrategy {
        /**
         * @param backup is object of my Backup
         * @param storage is type of Container Creation
         */
        @Override
        public Point create(BackUp backup, Container storage) {
            Map<String, File> beforeFiles;
            Point beforePoint = null;
            if (backup.getResPoints().isEmpty()) {
                beforeFiles = new HashMap<>();
            } else {
                beforePoint = backup.getResPoints().get(backup.getResPoints().size() - 1);
                beforeFiles = beforePoint.getFiles();
            }
            Map<String, File> files = new HashMap<>();
            Map<String, File> currentFiles = backup.getCurFiles();
            for (File file : currentFiles.values()){
                if (beforeFiles.keySet().stream().anyMatch(name -> name.equals(file.getFilename()))){
                    files.put(
                            file.getFilename(),
                            new File(
                                    file.getFilename(),
                                    file.getSize() - beforeFiles.get(file.getFilename()).getSize()
                            )
                    );
                }
                else {
                    files.put(file.getFilename(), new File(file.getFilename(), file.getSize()));
                }
            }
            storage.setFiles(files);
            return new Point.IncrementalPoint(storage, backup.getCurrentTime(), beforePoint);
        }
    }
}
