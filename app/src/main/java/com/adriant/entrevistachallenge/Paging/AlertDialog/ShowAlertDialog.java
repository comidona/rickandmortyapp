package com.adriant.entrevistachallenge.Paging.AlertDialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.adriant.entrevistachallenge.Models.Result;
import com.adriant.entrevistachallenge.R;

public class ShowAlertDialog {

    public ShowAlertDialog() {
    }

    public void showInfoDialog(Context mContext, Result result){

        LayoutInflater inflater = LayoutInflater.from(mContext);
        final android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(mContext);
        View mView = inflater.inflate(R.layout.alert_dialog_character_details, null);
        mBuilder.setView(mView);

        setInfoText(result, mView);

        final android.app.AlertDialog dialog = mBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button closeDialogBtn = mView.findViewById(R.id.close_btn);
        closeDialogBtn.setOnClickListener(v -> dialog.hide());

    }

    private void setInfoText(Result result, View view){

        TextView status, specie, gender, headl, location;
        status = view.findViewById(R.id.character_status);
        specie = view.findViewById(R.id.specie_response);
        gender = view.findViewById(R.id.gender_response);
        headl = view.findViewById(R.id.character_name);
        location = view.findViewById(R.id.location_response);

        status.setText(result.getStatus());
        specie.setText(result.getSpecies());
        gender.setText(result.getGender());
        headl.setText(result.getName());
        location.setText((result.getOrigin().getName()));

        checkType(result, view);

    }

    private void checkType(Result result, View view){

        String unknown, sType;
        TextView type;

        unknown = "desconocido";
        sType = result.getType();

        type = view.findViewById(R.id.type_response);

        if(sType.equals("")){
            type.setText(unknown);
        }else{
            type.setText(sType);
        }

    }

}
