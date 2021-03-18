/**
 * Here u can see two types of Containers, which I am using in my project
 * @param files is Map of our Backup
 * @see Container.DividedContainer#setFiles(java.util.Map)
 * @see Container.WinRaRContainer#setFiles(java.util.Map)
 * @see Container.WinRaRContainer#WinRaRContainer(WinRaR)
 */


import java.util.Map;

public interface Container {
    Map<String, File> getFiles();
    void setFiles(Map<String, File> files);

    class DividedContainer implements Container {
        private Map<String, File> files;

        public void setFiles(Map<String, File> files) {
            this.files = files;
        }

        @Override
        public Map<String, File> getFiles() {
            return files;
        }
    }

    class WinRaRContainer implements Container {
        private final WinRaR archive;

        public WinRaRContainer() {
            this.archive = new WinRaR();
        }

        public void setFiles(Map<String, File> files) {
            this.archive.setFiles(files);
        }

        @Override
        public Map<String, File> getFiles() {
            return archive.getFiles();
        }
    }
}
