package uscratings;

import java.util.ArrayList;

public class SearchResult {
	ArrayList<Result> results = new ArrayList<>();
	public ArrayList<Result> getResults() {
		return results;
	}
	public void add(Result r) {
		results.add(r);
	}
}


