package persistence;

import java.io.*;



public abstract class JsonWriter {
    protected static final int TAB = 4;
    protected String destination;
    protected PrintWriter writer;

    // EFFECTS: constructs abstract writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
    
    // MODIFIES: this
    // EFFECTS: writes string to file
    protected void saveToFile(String json) {
        writer.print(json);
    }   

}
