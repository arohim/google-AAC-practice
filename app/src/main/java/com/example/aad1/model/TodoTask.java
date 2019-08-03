package com.example.aad1.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "TodoTask")
public class TodoTask implements Parcelable {

    public final static int HIGH_PRIORITY = 0;
    public final static int MEDIUM_PRIORITY = 1;
    public final static int LOW_PRIORITY = 2;
    public final static int TASK_COMPLETED = 1;
    public final static int TASK_NOT_COMPLETED = 0;
    public final static long NO_DUE_DATE = 0;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "dueDate")
    private long dueDate;

    @NonNull
    @ColumnInfo(name = "completed")
    private int completed;

    public TodoTask() {
        this.description = null;
        this.priority = 0;
        this.dueDate = 0L;
        this.completed = 0;
        this.name = null;
    }

    public TodoTask(String name, String description, int priority, long dueDate, int completed) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.priority);
        dest.writeLong(this.dueDate);
        dest.writeInt(this.completed);
    }

    protected TodoTask(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.priority = in.readInt();
        this.dueDate = in.readLong();
        this.completed = in.readInt();
    }

    public static final Creator<TodoTask> CREATOR = new Creator<TodoTask>() {
        @Override
        public TodoTask createFromParcel(Parcel source) {
            return new TodoTask(source);
        }

        @Override
        public TodoTask[] newArray(int size) {
            return new TodoTask[size];
        }
    };
}
