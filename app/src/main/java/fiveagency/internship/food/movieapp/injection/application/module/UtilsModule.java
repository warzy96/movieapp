package fiveagency.internship.food.movieapp.injection.application.module;

import android.content.Context;
import android.content.Intent;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fiveagency.internship.food.movieapp.R;
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
    List<AuthUI.IdpConfig> provideSignInProviders() {
        return Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build(),
                             new AuthUI.IdpConfig.FacebookBuilder().build(),
                             new AuthUI.IdpConfig.TwitterBuilder().build(),
                             new AuthUI.IdpConfig.EmailBuilder().build());
    }

    @Provides
    @Singleton
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    Intent provideFirebaseAuthUIBuilderIntent(List<AuthUI.IdpConfig> providers) {
        return AuthUI.getInstance()
                     .createSignInIntentBuilder()
                     .setAvailableProviders(providers)
                     .setTheme(R.style.AppTheme)
                     .setIsSmartLockEnabled(false)
                     .build();
    }
}
