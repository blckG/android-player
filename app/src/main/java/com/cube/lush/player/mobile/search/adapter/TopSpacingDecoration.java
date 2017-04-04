package com.cube.lush.player.mobile.search.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.AllArgsConstructor;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 04/04/2017.
 */
@AllArgsConstructor
public class TopSpacingDecoration extends RecyclerView.ItemDecoration
{
	private int space;

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
	{
		outRect.top = space;
	}
}