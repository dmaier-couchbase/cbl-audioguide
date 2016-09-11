package de.openmuseau.audioguide.model;

import java.util.List;

import de.openmuseau.audioguide.model.Entry;

/**
 * Created by david on 04.09.16.
 */
public class EntriesModel {

    /**
     * The entries to show in the list
     */
    private List<Entry> entries;

    /**
     * The current visible entries
     */
    int currEntry;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public int getCurrEntry() {
        return currEntry;
    }

    public void setCurrEntry(int currEntry) {
        this.currEntry = currEntry;
    }
}
