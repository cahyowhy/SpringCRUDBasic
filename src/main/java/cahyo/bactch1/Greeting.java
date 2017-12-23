package cahyo.bactch1;

public class Greeting {
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
