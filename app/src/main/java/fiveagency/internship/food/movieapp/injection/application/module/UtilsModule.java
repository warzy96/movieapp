package fiveagency.internship.food.movieapp.injection.application.module;

import android.content.Context;

import com.facebook.CallbackManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.injection.application.ForApplication;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoaderImpl;
import fiveagency.internship.food.movieapp.ui.utils.Logger;
import fiveagency.internship.food.movieapp.ui.utils.LoggerImpl;
import fiveagency.internship.food.movieapp.ui.utils.StringUtil;
import fiveagency.internship.food.movieapp.ui.utils.StringUtilImpl;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(@ForApplication final Context context) {
        return new ImageLoaderImpl(context);
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return new LoggerImpl();
    }

    @Provides
    @Singleton
    public StringUtil provideStringUtil(@ForApplication final Context context) {
        return new StringUtilImpl(context);
    }

    @Provides
    @Singleton
    CallbackManager provideCallbackManager() {
        return CallbackManager.Factory.create();
    }
}
