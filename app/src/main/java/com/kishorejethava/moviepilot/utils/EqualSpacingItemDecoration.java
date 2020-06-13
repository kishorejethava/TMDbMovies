package com.kishorejethava.moviepilot.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kishore Jethava on 3/11/2019.
 */
public class EqualSpacingItemDecoration extends RecyclerView.ItemDecoration {
  private final int top;
  private final int left;
  private final int bottom;
  private final int right;
  private int displayMode;

  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;
  public static final int GRID = 2;

  public EqualSpacingItemDecoration(int top, int left, int bottom, int right) {
    this(top, left, bottom, right, -1);
  }

  public EqualSpacingItemDecoration(int top, int left, int bottom, int right, int displayMode) {
    this.top = top;
    this.left = left;
    this.bottom = bottom;
    this.right = right;
    this.displayMode = displayMode;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int position = parent.getChildViewHolder(view).getAdapterPosition();
    int itemCount = state.getItemCount();
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    setSpacingForDirection(outRect, layoutManager, position, itemCount);
  }

  private void setSpacingForDirection(Rect outRect,
                                      RecyclerView.LayoutManager layoutManager,
                                      int position,
                                      int itemCount) {

    // Resolve display mode automatically
    if (displayMode == -1) {
      displayMode = resolveDisplayMode(layoutManager);
    }

    switch (displayMode) {
      case HORIZONTAL:
        outRect.left = left;
        outRect.right = position == itemCount - 1 ? right : 0;
        outRect.top = top;
        outRect.bottom = bottom;
        break;
      case VERTICAL:
        outRect.left = left;
        outRect.right = right;
        outRect.top = top;
        outRect.bottom = position == itemCount - 1 ? bottom : 0;
        break;
      case GRID:
        if (layoutManager instanceof GridLayoutManager) {
          GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
          int cols = gridLayoutManager.getSpanCount();
          //  3 = 7 / 2
          int rows = itemCount / cols;

          //if rows is odd number make it even
          if((rows & 1) != 0){
            rows += 1;
          }


          outRect.left = left;
          outRect.right = position % cols == cols - 1 ? right : 0;
          outRect.top = top;
          outRect.bottom = position / cols == rows - 1 ? bottom : 0;
        }
        break;
    }
  }

  private int resolveDisplayMode(RecyclerView.LayoutManager layoutManager) {
    if (layoutManager instanceof GridLayoutManager) return GRID;
    if (layoutManager.canScrollHorizontally()) return HORIZONTAL;
    return VERTICAL;
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
  }
}
