package com.teamteam.blueboi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by acer on 28/01/2018.
 */

public class HistoryViewHolderWorker extends RecyclerView.ViewHolder {

    ImageView ivStar5, ivStar4, ivStar3, ivStar2, ivStar1;
    TextView tvTitle;
    TextView tvDatetime;
    TextView tvClientName;

    public HistoryViewHolderWorker (View itemView) {
        super(itemView);
        // insert find vie by ids here
    }
}
