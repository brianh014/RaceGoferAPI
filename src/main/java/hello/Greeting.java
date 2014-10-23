package hello;

public class Greeting {

    private final long id;
    private final String content;
    private final int _age;

    public Greeting(long id, String content, int age) {
        this.id = id;
        this.content = content;
        _age = age;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getAge() {return _age;}
}
