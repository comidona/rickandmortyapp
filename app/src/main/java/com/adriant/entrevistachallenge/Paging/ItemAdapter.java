package com.adriant.entrevistachallenge.Paging;

import androidx.paging.PagedListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.adriant.entrevistachallenge.Models.Result;
import com.adriant.entrevistachallenge.Paging.AlertDialog.ShowAlertDialog;
import com.adriant.entrevistachallenge.R;

public class ItemAdapter extends PagedListAdapter<Result, ItemAdapter.ItemViewHolder> {

    private Context mContext;

    public ItemAdapter(Context mContext) {

        super(DIFF_CALLBACK);
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i) {

        final Result result = getItem(i);

        if(result != null){
            Glide.with(mContext)
                    .load(result.getImage())
                    .into(itemViewHolder.characterImage);

            itemViewHolder.headline.setText(result.getName());
        }else{
            Toast.makeText(mContext, "No se pueden cargar los personajes", Toast.LENGTH_SHORT).show();
        }

        itemViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAlertDialog dialog = new ShowAlertDialog();
                dialog.showInfoDialog(mContext, result);
            }
        });

    }

    private static final DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {

        @Override
        public boolean areItemsTheSame(@NonNull Result result, @NonNull Result t1) {
            return result.getId().equals(t1.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result result, @NonNull Result t1) {
            @SuppressLint("DiffUtilEquals") boolean equals = result.equals(t1);
            return equals;
        }
    };

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView characterImage;
        TextView headline;
        RelativeLayout parent_layout;

        private ItemViewHolder(@NonNull View itemView) {

            super(itemView);

            characterImage = itemView.findViewById(R.id.character_image);
            headline = itemView.findViewById(R.id.headline);
            parent_layout = itemView.findViewById(R.id.list_item_parent_layout);

        }
    }

}
