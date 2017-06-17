package com.yzg.myapplication.widget;

import android.content.Context;
import android.support.design.widget.*;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.yzg.common.util.MathUtil;

import java.util.List;

/**
 * Created by yzg on 2017/1/17.
 */

@CoordinatorLayout.DefaultBehavior(CoorHeaderViewLayout.Behavior.class)
public class CoorHeaderViewLayout extends RelativeLayout {

    private static final int INVALID_SCROLL_RANGE = -1;

    private int mTotalScrollRange = INVALID_SCROLL_RANGE;

    private View scrollChildView;
    private View fixChildView;

    private int minOffsetOfScrollChild;
    private int maxOffsetOfScrollChild;

    public CoorHeaderViewLayout(Context context) {
        super(context);
    }

    public CoorHeaderViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoorHeaderViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final int getTotalScrollRange() {
        if (mTotalScrollRange != INVALID_SCROLL_RANGE) {
            return mTotalScrollRange;
        }

        int range = getMeasuredHeight();

        if(scrollChildView != null){
            range -= scrollChildView.getMeasuredHeight();
        }

        if(fixChildView != null){
            range -= fixChildView.getMeasuredHeight();
        }

        return mTotalScrollRange = range;
    }

    public void setScrollChildView(View scrollChild){
        this.scrollChildView = scrollChild;
    }

    public void setFixChildView(View fixChild){
        this.fixChildView = fixChild;
    }

    boolean hasScrollableChildren() {
        return getTotalScrollRange() != 0;
    }

    void dispatchOffsetUpdates(int offset){
        if(maxOffsetOfScrollChild == 0){
            if(fixChildView != null){
                maxOffsetOfScrollChild = fixChildView.getTop();
            }else {
                maxOffsetOfScrollChild = getHeight();
            }
        }

        if(minOffsetOfScrollChild == 0){
            if(scrollChildView != null){
                minOffsetOfScrollChild = scrollChildView.getTop();
                maxOffsetOfScrollChild -= scrollChildView.getHeight();
            }
        }

        if(minOffsetOfScrollChild <= -offset && maxOffsetOfScrollChild >= -offset && scrollChildView != null){
            ViewCompat.offsetTopAndBottom(scrollChildView, -offset - scrollChildView.getTop());
        }
    }

    public static class Behavior extends HeaderBehavior<CoorHeaderViewLayout>{
        private boolean mSkipNestedPreScroll;

        @Override
        public boolean setTopAndBottomOffset(int offset) {
            return super.setTopAndBottomOffset(offset);
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout parent, CoorHeaderViewLayout child,
                                           View directTargetChild, View target, int nestedScrollAxes) {
            final boolean started = (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0
                    && child.hasScrollableChildren()
                    && parent.getHeight() - directTargetChild.getHeight() <= child.getHeight();

            return started;
        }

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, CoorHeaderViewLayout child,
                                      View target, int dx, int dy, int[] consumed) {
            if (dy != 0 && !mSkipNestedPreScroll) {
                int min, max;
                if (dy < 0) {
                    // We're scrolling down
                    min = -child.getTotalScrollRange();
                    max = min + 0;
                } else {
                    // We're scrolling up
                    min = -child.getTotalScrollRange();
                    max = 0;
                }
                consumed[1] = scroll(coordinatorLayout, child, dy, min, max);
            }
        }

        @Override
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, CoorHeaderViewLayout child,
                                   View target, int dxConsumed, int dyConsumed,
                                   int dxUnconsumed, int dyUnconsumed) {
            if (dyUnconsumed < 0) {
                // If the scrolling view is scrolling down but not consuming, it's probably be at
                // the top of it's content
                scroll(coordinatorLayout, child, dyUnconsumed,
                        -child.getTotalScrollRange(), 0);
                // Set the expanding flag so that onNestedPreScroll doesn't handle any events
                mSkipNestedPreScroll = true;
            } else {
                // As we're no longer handling nested scrolls, reset the skip flag
                mSkipNestedPreScroll = false;
            }
        }

        @Override
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, CoorHeaderViewLayout abl,
                                       View target) {
            // Reset the flags
            mSkipNestedPreScroll = false;
        }

        @Override
        public boolean onNestedFling(final CoordinatorLayout coordinatorLayout,
                                     final CoorHeaderViewLayout child, View target, float velocityX, float velocityY,
                                     boolean consumed) {
            boolean flung = false;

            if (!consumed) {
                // It has been consumed so let's fling ourselves
                flung = fling(coordinatorLayout, child, -child.getTotalScrollRange(),
                        0, -velocityY);
            }
            return flung;
        }

        @Override
        boolean canDragView(CoorHeaderViewLayout view) {
            return true;
        }

        @Override
        int getMaxDragOffset(CoorHeaderViewLayout view) {
            return -view.getTotalScrollRange();
        }

        @Override
        int getScrollRangeForDragFling(CoorHeaderViewLayout view) {
            return view.getTotalScrollRange();
        }

        @Override
        public boolean onMeasureChild(CoordinatorLayout parent, CoorHeaderViewLayout child,
                                      int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec,
                                      int heightUsed) {
            final CoordinatorLayout.LayoutParams lp =
                    (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            if (lp.height == CoordinatorLayout.LayoutParams.WRAP_CONTENT) {
                parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed,
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), heightUsed);
                return true;
            }

            // Let the parent handle it as normal
            return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed,
                    parentHeightMeasureSpec, heightUsed);
        }

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, CoorHeaderViewLayout abl,
                                     int layoutDirection) {
            boolean handled = super.onLayoutChild(parent, abl, layoutDirection);

            setTopAndBottomOffset(MathUtil.constrain(getTopAndBottomOffset(), -abl.getTotalScrollRange(), 0));

            // Make sure we dispatch the offset update
            abl.dispatchOffsetUpdates(getTopAndBottomOffset());

            return handled;
        }

        @Override
        int setHeaderTopBottomOffset(CoordinatorLayout parent, CoorHeaderViewLayout header, int newOffset, int minOffset, int maxOffset) {
            final int curOffset = getTopBottomOffsetForScrollingSibling();
            int consumed = 0;

            if (minOffset != 0 && curOffset >= minOffset && curOffset <= maxOffset) {
                // If we have some scrolling range, and we're currently within the min and max
                // offsets, calculate a new offset
                newOffset = MathUtil.constrain(newOffset, minOffset, maxOffset);
                if (curOffset != newOffset) {

                    final boolean offsetChanged = setTopAndBottomOffset(newOffset);
                    header.dispatchOffsetUpdates(getTopAndBottomOffset());
                    consumed = curOffset - newOffset;

                    if (!offsetChanged) {
                        parent.dispatchDependentViewsChanged(header);
                    }
                }
            }
            return consumed;
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior{
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
            // We depend on any AppBarLayouts
            return dependency instanceof CoorHeaderViewLayout;
        }

        @Override
        CoorHeaderViewLayout findFirstDependency(List<View> views) {
            for (int i = 0, z = views.size(); i < z; i++) {
                View view = views.get(i);
                if (view instanceof CoorHeaderViewLayout) {
                    return (CoorHeaderViewLayout) view;
                }
            }
            return null;
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
            offsetChildAsNeeded(parent, child, dependency);
            return false;
        }

        private void offsetChildAsNeeded(CoordinatorLayout parent, View child, View dependency) {
            final CoordinatorLayout.Behavior behavior =
                    ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
            if (behavior instanceof Behavior) {
                // Offset the child, pinning it to the bottom the header-dependency, maintaining
                // any vertical gap and overlap
                final Behavior ablBehavior = (Behavior) behavior;
                ViewCompat.offsetTopAndBottom(child, (dependency.getBottom() - child.getTop())
                        + getVerticalLayoutGap()
                        - getOverlapPixelsForOffset(dependency));
            }
        }

        @Override
        int getScrollRange(View v) {
            if (v instanceof CoorHeaderViewLayout) {
                return ((CoorHeaderViewLayout) v).getTotalScrollRange();
            } else {
                return super.getScrollRange(v);
            }
        }
    }
}
