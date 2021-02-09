package com.tlnk.mydiary.ui.taskCreate;

import java.util.Date;

/**
 * Created by Alexandr Egorshin on 09.02.2021.
 */
public class TaskCreateModel {
    public String name;
    public String description;
    public long date_start;
    public long date_finish;

    public TaskCreateModel() {

    }

    public TaskCreateModel(String name, String description, long date_start, long date_finish) {
        this.name = name;
        this.description = description;
        this.date_start = date_start;
        this.date_finish = date_finish;
    }

}
