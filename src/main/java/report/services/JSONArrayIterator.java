package report.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JSONArrayIterator implements Iterator<JSONObject> {
    private final JSONArray jsonArray;
    private int currentIndex;

    public JSONArrayIterator(JSONArray array) {
        this.jsonArray = array;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < jsonArray.length();
    }

    @Override
    public JSONObject next() {
        if (currentIndex >= jsonArray.length()) {
            throw new NoSuchElementException("No more elements in the array");
        }
        JSONObject nextObject;
        try {
            nextObject = jsonArray.getJSONObject(currentIndex);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        currentIndex++;
        return nextObject;
    }

}
