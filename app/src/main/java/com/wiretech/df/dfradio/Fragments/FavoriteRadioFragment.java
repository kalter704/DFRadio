package com.wiretech.df.dfradio.Fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wiretech.df.dfradio.Activityes.PlayActivity;
import com.wiretech.df.dfradio.Classes.RadioState;
import com.wiretech.df.dfradio.DataClasses.RadioChannels;
import com.wiretech.df.dfradio.Interfaces.OnRadioListener;
import com.wiretech.df.dfradio.R;

public class FavoriteRadioFragment extends Fragment implements OnRadioListener {

    private ContentAdapterForFavoriteList adapter;

    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RadioState.addRadioListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        adapter = new ContentAdapterForFavoriteList(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RadioState.removeRadioListener(this);
    }

    @Override
    public void onRadioStarted() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRadioPaused() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRadioStopped() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRadioLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRadioMetadata(String s, String s2) {

    }

    @Override
    public void onRadioError() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO Do UI works here.
                adapter.notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameDadio;
        public TextView mLocation;
        public ImageView mImgArrow;
        public ImageView mImgEqualizer;

        private RadioChannels radioChannels = RadioChannels.getInstance();

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_radio_list, parent, false));
            mNameDadio = (TextView) itemView.findViewById(R.id.tvRadioName);
            mLocation = (TextView) itemView.findViewById(R.id.tvLocation);
            mImgArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
            mImgEqualizer = (ImageView) itemView.findViewById(R.id.ivEqualizer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RadioState.hasConnectionToNetwork()) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, PlayActivity.class);
                        intent.putExtra(PlayActivity.EXTRA_ID, radioChannels.mLikes.get(getAdapterPosition()));
                        context.startActivity(intent);
                    } else {
                        Snackbar snackbar = Snackbar.make(
                                getActivity().findViewById(R.id.mainCoordLayout),
                                context.getString(R.string.error_network),
                                Snackbar.LENGTH_SHORT);
                        View snackView = snackbar.getView();
                        snackView.setBackgroundColor(getActivity().getResources().getColor(R.color.snackErrorNetworkColor));
                        TextView snackTV = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                        snackTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        snackbar.show();
                    }
                }
            });
        }
    }

    public class ContentAdapterForFavoriteList extends RecyclerView.Adapter<ViewHolder> {

        private RadioChannels radioChannels;
        Context context;
        //private static int LENGTH = 0;
        //private int index[];

        public ContentAdapterForFavoriteList(Context context) {
            radioChannels = RadioChannels.getInstance();
            this.context = context;
            //LENGTH = radioChannels.mLikes.size();
            /*
            if(radioChannels.mLikes.size() != 0) {
                index = new int[radioChannels.mLikes.size()];
                for(int i = 0; i < radioChannels.mLikes.size(); ++i) {
                    index[i] = radioChannels.mLikes.get(i);
                }
            }
            */
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int i = radioChannels.mIds.indexOf(radioChannels.mLikes.get(position));
            holder.mNameDadio.setText(radioChannels.mRadioNames[i]);
            holder.mLocation.setText(radioChannels.mLocations[i]);
            AnimationDrawable animation = null;
            animation = (AnimationDrawable) holder.mImgEqualizer.getBackground();
            if ((radioChannels.mIds.get(i) == radioChannels.mPlayRadioWithId)) {
                if (RadioState.isPlaying()) {
                    holder.mImgArrow.setVisibility(View.INVISIBLE);
                    holder.mImgEqualizer.setVisibility(View.VISIBLE);
                    if (animation != null) {
                        animation.stop();
                        animation.start();
                    }
                } else {
                    holder.mImgArrow.setVisibility(View.VISIBLE);
                    holder.mImgEqualizer.setVisibility(View.INVISIBLE);
                    if (animation != null) {
                        animation.stop();
                    }
                }
            } else {
                holder.mImgArrow.setVisibility(View.VISIBLE);
                holder.mImgEqualizer.setVisibility(View.INVISIBLE);
                if (animation != null) {
                    animation.stop();
                }
            }

        }

        @Override
        public int getItemCount() {
            return radioChannels.mLikes.size();
        }
    }

}
