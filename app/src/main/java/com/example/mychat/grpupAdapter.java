package com.example.mychat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;

import java.util.List;

public class grpupAdapter extends RecyclerView.Adapter<grpupAdapter.GroupViewHolder> {

    private final List<Group> groups;
    private final Context context;

    public grpupAdapter(List<Group> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupViewHolder ( LayoutInflater.from ( context ).inflate ( R.layout.group_layout, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull grpupAdapter.GroupViewHolder holder, int position) {
        holder.bind(groups.get ( position ));
    }


    @Override
    public int getItemCount() {
        return groups.size ();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupNameTextView;
        LinearLayout containerLayout;
        public GroupViewHolder(@NonNull View itemView) {
            super ( itemView );
            groupNameTextView = itemView.findViewById ( R.id.groupNameTextView );
            containerLayout = itemView.findViewById ( R.id.containerLayout );
        }

        public void bind(Group group) {
            groupNameTextView.setText ( group.getName () );
            containerLayout.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    final String GUID = group.getGuid ();
                    final String groupType = CometChatConstants.GROUP_TYPE_PUBLIC;
                    final String password = null;
//                    CometChat.joinGroup(GUID, groupType, password, new CometChat.CallbackListener<Group>() {
//                        @Override
//                        public void onSuccess(Group joinedGroup) {
//                            chatActivity.start(context,GUID);
//                        }
//
//
//                        @Override
//                        public void onError(CometChatException e) {
//                            Toast.makeText ( context, "already joined", Toast.LENGTH_SHORT ).show ();
//                        }
//                    });
                    chatActivity.start(context,GUID);
                }
            } );
        }
    }
}