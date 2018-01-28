package com.teamteam.blueboi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by acer on 28/01/2018.
 */

public class RequestsViewHolderWorker extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvDatetime;
    TextView tvLocation;
    Button btnAccept;
    Button btnReject;

    public RequestsViewHolderWorker (View itemView) {
        super(itemView);
        // insert find vie by ids here
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDatetime = (TextView) itemView.findViewById(R.id.tv_datetime);
        tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
        btnAccept = itemView.findViewById(R.id.btn_accept);
        btnReject = itemView.findViewById(R.id.btn_decline);
    }
}
