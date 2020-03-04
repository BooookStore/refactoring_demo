package demo.refactoring;

import java.util.Objects;

public class Play {

    private String name;

    private String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Play)) return false;
        Play play = (Play) o;
        return Objects.equals(name, play.name) &&
                Objects.equals(type, play.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

}
