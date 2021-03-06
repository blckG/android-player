package com.cube.lush.player.tv.state;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cube.lush.player.R;

/**
 * Displayed to the user in order to indicate that data is loading.
 *
 * @author Jamie Cruwys
 */
public class ErrorFragment extends android.support.v17.leanback.app.ErrorFragment
{
	private static final String ERROR_FRAGMENT_TAG = "error";

	public static void show(FragmentManager fragmentManager, View parentView, Runnable retryAction)
	{
		fragmentManager.executePendingTransactions();
		if (parentView != null && fragmentManager.findFragmentByTag(ERROR_FRAGMENT_TAG) == null)
		{
			ErrorFragment errorFragment = new ErrorFragment();
			errorFragment.retryAction = retryAction;
			fragmentManager.beginTransaction().add(parentView.getId(), errorFragment, ERROR_FRAGMENT_TAG).commit();
		}
	}

	public static void hide(FragmentManager fragmentManager)
	{
		if (fragmentManager.isDestroyed())
		{
			return;
		}

		fragmentManager.executePendingTransactions();
		Fragment spinnerFragment = fragmentManager.findFragmentByTag(ERROR_FRAGMENT_TAG);
		if (spinnerFragment != null)
		{
			fragmentManager.beginTransaction().remove(spinnerFragment).commitAllowingStateLoss();
		}
	}

	private Runnable retryAction;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_cloud_off_white_48dp));
		setDefaultBackground(true);
		setMessage(getString(R.string.network_offline));
		setButtonText(getString(R.string.retry));
		setButtonClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				retryAction.run();
			}
		});
	}
}
