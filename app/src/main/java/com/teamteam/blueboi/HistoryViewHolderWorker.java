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
        ivStar5 = (ImageView) itemView.findViewById(R.id.iv_star5);
        ivStar4 = (ImageView) itemView.findViewById(R.id.iv_star4);
        ivStar3 = (ImageView) itemView.findViewById(R.id.iv_star3);
        ivStar2 = (ImageView) itemView.findViewById(R.id.iv_star2);
        ivStar1 = (ImageView) itemView.findViewById(R.id.iv_star1);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDatetime = (TextView) itemView.findViewById(R.id.tv_datetime);
        tvClientName = (TextView) itemView.findViewById(R.id.tv_clientName);
    }
}
