package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends MainActivityFragmentBase {
    private PublisherInterstitialAd interstitialAd;

    private AdListener adListener = new AdListener(){
        private final String LOG_TAG = "AD_LISTENER";

        @Override
        public void onAdLoaded() {
            Log.d(LOG_TAG, "Interstitial Ad ready");

            super.onAdLoaded();
        }

        @Override
        public void onAdFailedToLoad(int i) {
            Log.e(LOG_TAG, "Interstitial Ad failed to load. Error code " + i);

            super.onAdFailedToLoad(i);
        }

        @Override
        public void onAdClosed() {
            Log.d(LOG_TAG, "Interstitial Ad closed. Resuming app navigation");

            super.onAdClosed();
            interstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            MainActivityFragment.super.fetchJoke();
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdView adView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(BuildConfig.AD_TEST_DEVICE)
                .build();
        adView.loadAd(adRequest);

        interstitialAd = new PublisherInterstitialAd(getContext());
        interstitialAd.setAdUnitId(BuildConfig.INTERSTITIAL_AD_UNIT_ID);
        interstitialAd.setAdListener(adListener);

        requestNewInterstitialAd();

        return root;
    }

    @Override
    protected void fetchJoke() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            super.fetchJoke();
        }
    }

    public void requestNewInterstitialAd() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                                            .addTestDevice(BuildConfig.AD_TEST_DEVICE)
                                            .build();

        interstitialAd.loadAd(adRequest);
    }
}
