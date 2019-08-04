package com.example.aad1.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.aad1.R;

import static com.example.aad1.model.TodoTask.HIGH_PRIORITY;
import static com.example.aad1.model.TodoTask.LOW_PRIORITY;
import static com.example.aad1.model.TodoTask.MEDIUM_PRIORITY;

public class PriorityStarImageView extends AppCompatImageView {

    public final static int COMPLETED = 3;

    public PriorityStarImageView(Context context) {
        super(context);
    }

    public PriorityStarImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStyle(context, attrs);
    }

    public PriorityStarImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setStyle(context, attrs);
    }

    private void setStyle(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PriorityStarImageView);
        int priority = attributes.getInteger(R.styleable.PriorityStarImageView_priority, HIGH_PRIORITY);
        setPriority(priority);
        attributes.recycle();
    }

    public void setPriority(int priority) {
        Resources res = getResources();
        int star = 0;
        int contentDescription = 0;
        switch (priority) {
            case HIGH_PRIORITY:
                star = R.drawable.ic_star_red_24dp;
                contentDescription = R.string.high_priority_task_red_star;
                break;
            case MEDIUM_PRIORITY:
                star = R.drawable.ic_star_orange_24dp;
                contentDescription = R.string.medium_priority_task_orange_star;
                break;
            case LOW_PRIORITY:
                star = R.drawable.ic_star_yellow_24dp;
                contentDescription = R.string.low_priority_task_yellow_star;
                break;
            case COMPLETED:
                star = R.drawable.ic_star_grey_24dp;
                contentDescription = R.string.completed_task_grey_star;
        }

        setBackground(res.getDrawable(star));
        setContentDescription(res.getString(contentDescription));
    }

}
