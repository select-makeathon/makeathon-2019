package com.selectmakeathon.app.ui.createteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.selectmakeathon.app.R;
import com.selectmakeathon.app.model.UserModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddMemberBottomSheet extends BottomSheetDialogFragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference userReference;
    TextInputLayout teamMemberEditText;
    AddMemberInterface memberInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_add_member, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        userReference = firebaseDatabase.getReference().child("users");
        teamMemberEditText = view.findViewById(R.id.el_team_member_reg_no);
        memberInterface = TeamActivity.getAddMemberInterface();
        view.findViewById(R.id.btn_team_member_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teamMemberEditText.getEditText().getText() == null || teamMemberEditText.getEditText().getText().length() == 0) {
                    teamMemberEditText.setError("Please Enter Registration Number");
                } else {
                    final String memberRegNo = teamMemberEditText.getEditText().getText().toString();
                    userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(memberRegNo)) {
                                UserModel member = dataSnapshot.child(memberRegNo).getValue(UserModel.class);
                                assert member != null;
                                if (member.isJoined() || member.isLeader()) {
                                    Toast.makeText(getContext(), "Entered member is already a part of a team, Please enter a new Reg No.", Toast.LENGTH_SHORT).show();
                                } else {
                                    memberInterface.addMember(member);
                                }
                            }
                            else{
                                Toast.makeText(getContext(), "Member Doesn't Exist, Please try another Registration Number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        return view;
    }
}
