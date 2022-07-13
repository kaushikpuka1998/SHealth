package com.kgstrivers.shealth.RecyclerviewAdapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.kgstrivers.shealth.Models.Firebasedata
import com.kgstrivers.shealth.R
import kotlinx.android.synthetic.main.singlevideo.view.*


class DataRecyclerViewHolder( val videolist:ArrayList<Firebasedata>) :RecyclerView.Adapter<DataRecyclerViewHolder.MyViewHolder>() {


        class  MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {

            val videotitle = view.videoname
            lateinit var view: View

            fun bind(data: Firebasedata)
            {
                videotitle.text = data.title
                try{

                    var playerView: PlayerView
                    var simpleExoPlayer: SimpleExoPlayer
                    simpleExoPlayer = SimpleExoPlayer.Builder(view.context).build()
                    view.exoplayerview.player = simpleExoPlayer
//                    val videoUri: Uri = Uri.parse(data.link)
//                    val mediaItem: MediaItem = MediaItem.fromUri(videoUri)
//
//                    exoPlayer.clearMediaItems();
//                    exoPlayer.setMediaItem(mediaItem);
////                    val defaultHttpDataSourceFactory : DefaultHttpDataSourceFactory = DefaultHttpDataSourceFactory()
//                    val mediasource :MediaSource  = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
//                        .createMediaSource(MediaItem.fromUri("https://www.youtube.com/watch?v=VVXKVFyYQdQ"))
//                    exoPlayer.prepare( mediasource);
//                    exoPlayer.setPlayWhenReady(true);


                    val mediaItem : MediaItem = MediaItem.fromUri(Uri.parse("https://www.youtube.com/watch?v=VVXKVFyYQdQ"));
                    simpleExoPlayer.addMediaItem(mediaItem);
                    simpleExoPlayer.prepare();
                    simpleExoPlayer.play();
                    simpleExoPlayer.setPlayWhenReady(true);
                }
                catch(e:Exception)
                {
                    Log.d("view Holder Error",""+e)
                }
            }


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.singlevideo,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder:DataRecyclerViewHolder.MyViewHolder, position: Int) {
        holder.bind(videolist[position])

    }

    override fun getItemCount(): Int {
        Log.d("VVV", videolist.size.toString())
       return videolist.size
    }
}

