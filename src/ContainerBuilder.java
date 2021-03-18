public interface ContainerBuilder {
    Container build();

    class SeparatedBuilder implements ContainerBuilder {
        @Override
        public Container build() {
            return new Container.DividedContainer();
        }
    }

    class ArchiveBuilder implements ContainerBuilder {
        @Override
        public Container build() {
            return new Container.WinRaRContainer();
        }
    }
}
