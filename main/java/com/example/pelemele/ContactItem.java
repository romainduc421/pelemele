package com.example.pelemele;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactItem extends RecyclerView.ViewHolder {
    private TextView nameTxt;
    private TextView phoneTxt;

    public ContactItem(@NonNull View view){
        super(view);
        nameTxt = view.findViewById(R.id.contact_name);
        phoneTxt = view.findViewById(R.id.contact_number);
    }

    public TextView getNameTxt() {
        return nameTxt;
    }

    public TextView getPhoneTxt() {
        return phoneTxt;
    }
}
