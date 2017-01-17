package co.com.grupoasd.pokedexdemoasd.object;

import java.util.List;

/**
 * Created by ASD on 11/01/2017.
 */

public class PokemonResultsSpring {
    private String next;
    private int count;
    private String previous;
    private Object results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }
}
