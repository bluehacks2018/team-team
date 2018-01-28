package com.teamteam.blueboi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by acer on 28/01/2018.
 */

public class HomeViewHolderUser extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvDatetime;
    TextView tvWorkerName;
    ImageView ivStatus;

    public HomeViewHolderUser(View itemView) {
        super(itemView);
        // insert find vie by ids here
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDatetime = (TextView) itemView.findViewById(R.id.tv_datetime);
        tvWorkerName = (TextView) itemView.findViewById(R.id.tv_workerName);

    }
}
