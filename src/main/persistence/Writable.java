package persistence;

import org.json.JSONObject;

// Citation: All of the code in this class are similar to JsonSerializationDemo (given in the project phase 2)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
