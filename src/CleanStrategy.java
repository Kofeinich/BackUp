/**
 * Here I am using GoF pattern Strategy which improve my architecture
 *
 */

public interface CleanStrategy {
    void execute(BackUp backup);

    class CountingClean implements CleanStrategy {
        private final Integer count;

        public CountingClean(Integer maxPointsToSave){
            this.count = maxPointsToSave;
        }
        /**
         * @param backup is object of my Backup
         * @see CountingClean#execute (so this is main part of the pattern)
         */
        @Override
        public void execute(BackUp backup) {
           if (backup.getCountOfPoints() >= count){
               Integer delta = backup.getCountOfPoints() - count;
               while (delta != 0){
                   backup.getRestorePoints().remove(backup.getRestorePoints().size() - 1);
                   delta--;
               }
           }
        }
    }

    class SizingClean implements CleanStrategy {
        private final Integer size;
        public SizingClean(Integer sizeToDelete){
            this.size = sizeToDelete;
        }
        /**
         * @param backup is object of my Backup
         * @see SizingClean#execute (so this is main part of the pattern)
         */
        @Override
        public void execute(BackUp backup) {
            backup.getRestorePoints().removeIf(restorePoint -> restorePoint.calculateSize() >= size);
        }
    }

    class TimingClean implements CleanStrategy {

        private final long time;

        public TimingClean(long timeToDelete) {
            this.time = timeToDelete;
        }
        /**
         * @param backup is object of my Backup
         * @see TimingClean#execute (so this is main part of the pattern)
         */
        @Override
        public void execute(BackUp backup) {
            backup.getRestorePoints().removeIf(
                    restorePoint -> backup.getCurrentTime() - restorePoint.getCreationTime() >= time
            );
        }
    }
}
