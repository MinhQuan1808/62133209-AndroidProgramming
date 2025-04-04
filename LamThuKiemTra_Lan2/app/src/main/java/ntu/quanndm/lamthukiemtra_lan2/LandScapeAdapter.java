package ntu.quanndm.lamthukiemtra_lan2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.itemLandHolder> {
    Context context;
    ArrayList<LandScape> lstData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public itemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem = cai_bom.inflate(R.layout.item_land, parent, false);
        itemLandHolder viewHolderCreated = new itemLandHolder(vItem);
        return viewHolderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull itemLandHolder holder, int position) {
        LandScape landHienThi = lstData.get(position);
        String caption = landHienThi.getLandCaption();
        String tenAnh = landHienThi.getLandImageFileName();
        holder.tvCaption.setText(caption);
        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(tenAnh, "mipmap", packageName);
        holder.ivLand.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }


    class itemLandHolder extends RecyclerView.ViewHolder{
        TextView tvCaption;
        ImageView ivLand;
        public itemLandHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.tvLand);
            ivLand = itemView.findViewById(R.id.imgLand);
        }
    }

}
