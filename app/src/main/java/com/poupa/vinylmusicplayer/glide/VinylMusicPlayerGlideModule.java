package com.poupa.vinylmusicplayer.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.poupa.vinylmusicplayer.glide.artistimage.ArtistImage;
import com.poupa.vinylmusicplayer.glide.artistimage.ArtistImageLoader;
import com.poupa.vinylmusicplayer.glide.audiocover.AudioFileCover;
import com.poupa.vinylmusicplayer.glide.audiocover.AudioFileCoverLoader;

import java.io.InputStream;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */

@GlideModule
public class VinylMusicPlayerGlideModule extends AppGlideModule {
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide,
                                   @NonNull Registry registry) {
        registry.append(AudioFileCover.class, InputStream.class, new AudioFileCoverLoader.Factory());
        registry.append(ArtistImage.class, InputStream.class, new ArtistImageLoader.Factory(context));
    }
}
