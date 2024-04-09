package toby.helloboot;

public interface HelloRepository {

    Hello findHello(String name);

    void increaseCount(String name);

    /**
     * default method
     */
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
