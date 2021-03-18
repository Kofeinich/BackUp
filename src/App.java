import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        BackUp backup = new BackUp();
        File file1= new File("Zamai1", 5);
        File file2 = new File("Zamai2", 9);
        File file3 = new File("Zamai3", 2);
        File file4 = new File("Zamai3", 8);

        ContainerBuilder.SeparatedBuilder separatedBuilder = new ContainerBuilder.SeparatedBuilder();
        ContainerBuilder.ArchiveBuilder archiveBuilder = new ContainerBuilder.ArchiveBuilder();
        CreateStrategy.IncrementalCreation incStrategy = new CreateStrategy.IncrementalCreation();
        CreateStrategy.FattestCreation fullStrategy = new CreateStrategy.FattestCreation();

        backup.addFile(file1);
        backup.addFile(file2);
        backup.addFile(file3);
        backup.addFile(file4);

        backup.createRestorePoint(fullStrategy, separatedBuilder);
        file2.setSize(7);
        backup.setCurrentTime(3);
        backup.createRestorePoint(incStrategy, archiveBuilder);
        backup.setCurrentTime(1567); file1.setSize(100000);
        backup.createRestorePoint(fullStrategy, separatedBuilder);
        backup.show();

        List<CleanStrategy> cleanupStrategyList = new ArrayList<>();
        cleanupStrategyList.add(
               new CleanStrategy.SizingClean(23)
        );
        cleanupStrategyList.add(
                new CleanStrategy.TimingClean(7700000)
        );

        cleanupStrategyList.add(
                new CleanStrategy.CountingClean(3)
        );

        backup.cleanup(cleanupStrategyList);
        System.out.println("After first cleanup\n");
        backup.show();

        cleanupStrategyList.add(
                new CleanStrategy.SizingClean(21)
        );

        cleanupStrategyList.add(
                new CleanStrategy.CountingClean(3)
        );

        backup.cleanup(cleanupStrategyList);
        System.out.println("After second cleanup\n");
        backup.show();



    }
}
