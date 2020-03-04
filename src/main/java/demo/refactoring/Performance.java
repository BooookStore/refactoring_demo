package demo.refactoring;

import java.util.Objects;

public class Performance {

    private String playID;

    private int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }

    public int getAudience() {
        return audience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Performance)) return false;
        Performance that = (Performance) o;
        return audience == that.audience &&
                Objects.equals(playID, that.playID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playID, audience);
    }

}
