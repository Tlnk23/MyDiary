package com.tlnk.mydiary.ui.dairy;

import java.io.Serializable;

public class DairyModel implements Serializable {

    private String id;
    private String name;
    private long date_start;
    private long date_finish;
    private String description;


    public DairyModel(){

    }

    public String getName() {
        return name;
    }

   public long getDate_finish() {
        return date_finish;
    }

    public long getDate_start() {
        return date_start;
    }

    public long getTime() {
        return date_start;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
