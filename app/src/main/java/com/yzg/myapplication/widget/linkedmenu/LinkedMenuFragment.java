package com.yzg.myapplication.widget.linkedmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzg.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 二级联动菜单
 *
 * Created by yzg on 2017/3/10.
 */

public abstract class LinkedMenuFragment<P, C> extends Fragment {
    @Bind(R.id.recy_parent)
    RecyclerView recyParent;
    @Bind(R.id.recy_children)
    RecyclerView recyChildren;
    private View rootView;

    private List<P> parentList;
    private List<C> childList;
    private List<C> selectedChildList = new ArrayList<>();
    private int currentParentPos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.linked_menu_layout, container, false);
        ButterKnife.bind(this, rootView);

        recyParent.setLayoutManager(getParentLayoutManager());
        recyParent.setAdapter(parentAdapter);
        recyChildren.setLayoutManager(getChildLayoutManager());
        recyChildren.setAdapter(childAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    RecyclerView.Adapter parentAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(getContext()).inflate(getParentItemRes(), parent, false)) {};
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            bindParentView(holder.itemView, parentList.get(position), currentParentPos == position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position == currentParentPos){
                        return;
                    }

                    int pos = currentParentPos;
                    currentParentPos = position;

                    parentAdapter.notifyItemChanged(pos);
                    parentAdapter.notifyItemChanged(position);

                    selectedChildList(parentList.get(currentParentPos));
                    childAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return parentList == null ? 0 : parentList.size();
        }
    };

    RecyclerView.Adapter childAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(getContext()).inflate(getChildItemRes(), parent, false)) {};
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            bindChildView(holder.itemView, selectedChildList.get(position));
        }

        @Override
        public int getItemCount() {
            return selectedChildList == null ? 0 : selectedChildList.size();
        }
    };

    public void setMenuData(List<P> parentList, List<C> childList){
        this.parentList = parentList;
        this.childList = childList;

        currentParentPos = 0;
        selectedChildList(parentList.get(currentParentPos));

        parentAdapter.notifyDataSetChanged();
        childAdapter.notifyDataSetChanged();
    }

    private void selectedChildList(P parent){
        selectedChildList.clear();
        for(C child : this.childList){
            if(isChildOfParent(parent, child)) {
                selectedChildList.add(child);
            }
        }
    }

    protected abstract int getParentItemRes();

    protected abstract int getChildItemRes();

    protected abstract void bindParentView(View view, P data, boolean isSelected);

    protected abstract void bindChildView(View view, C data);

    protected abstract boolean isChildOfParent(P parent, C child);

    protected abstract RecyclerView.LayoutManager getParentLayoutManager();

    protected abstract RecyclerView.LayoutManager getChildLayoutManager();

}
