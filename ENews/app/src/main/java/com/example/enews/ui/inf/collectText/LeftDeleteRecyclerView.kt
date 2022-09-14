package com.example.enews.ui.inf.collectText

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.LinearInterpolator
import android.widget.Scroller
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


class LeftDeleteRecyclerView(context: Context, attrs: AttributeSet?) :
    RecyclerView(context, attrs, 0) {

    //item的根布局
    private var itemRoot: ConstraintLayout? = null

    //上一次滑动的Item根布局
    private var itemRootLast: ConstraintLayout? = null

    //上次X轴的滑动坐标
    private var mlastX = 0

    //上次Y轴的滑动坐标
    private var mlastY = 0

    //滑动的最大距离
    private val MAX_WIDTH = 100
    private var mContext: Context? = null
    private var mScroller: Scroller? = null

    // 0-关闭 1-打开
    private var deleteState1 = 0
    private var deleteState2 = 0

    init {
        mContext = context
        mScroller = Scroller(context, LinearInterpolator(context, null))
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val maxLength = dipToPx(mContext, MAX_WIDTH)
        val x = event.x.toInt()
        val y = event.y.toInt()
        val position: Int
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("123", "down")
            }
            MotionEvent.ACTION_MOVE -> {
                //恢复上一次侧滑的ITEM
                Log.d("123", "move")
                if (itemRootLast != null && deleteState1 == 1 && deleteState2 == 1) {
                    Log.d("123", "恢复")
                    itemRootLast!!.scrollTo(0, 0)
                    itemRootLast = null
                    invalidate()
                    deleteState1 = 0
                    deleteState2 = 0
                }
                //根据点击的坐标获取那个Item被点击了
                val view = findChildViewUnder(x.toFloat(), y.toFloat()) ?: return false
                val viewHolder: LeftDeleteAdapter.ViewHolder =
                    getChildViewHolder(view) as LeftDeleteAdapter.ViewHolder
                itemRoot = viewHolder.cardView.parent as ConstraintLayout?
                itemRootLast = itemRoot
                position = viewHolder.adapterPosition
                if (mOnItemClickListener != null) {
                    viewHolder.delete.setOnClickListener {
                        mOnItemClickListener!!.onClick(viewHolder.itemView, position)
                    }
                }
                // 滑出delete
                if (deleteState2 == 0 && abs(mlastX - x) > 0
                    && abs(mlastX -x) > abs(mlastY-y)
                ) {
                    val scrollX = itemRoot!!.scrollX
                    var newScrollX = scrollX + mlastX - x
                    if (newScrollX < 0) {
                        newScrollX = 0
                    } else if (newScrollX > maxLength) {
                        newScrollX = maxLength
                    }
                    itemRoot!!.scrollTo(newScrollX, 0)
                    deleteState1 = 1
                }
            }
            MotionEvent.ACTION_UP -> {
                Log.d("123", "up")
                val scrollX = itemRoot!!.scrollX
                val newScrollX: Int
                if (scrollX > maxLength / 2) {
                    newScrollX = maxLength - 50
                    if(deleteState1 == 1)
                        deleteState2 = 1
                } else {
                    newScrollX = 0
                }
                mScroller!!.startScroll(scrollX, 0, newScrollX - scrollX, 0)
                invalidate()
            }
        }
        mlastX = x
        mlastY = y
        return super.onTouchEvent(event)
    }

    private fun dipToPx(context: Context?, dip: Int): Int {
        return (dip * context!!.resources.displayMetrics.density + 0.5f).toInt()
    }

    override fun computeScroll() {
        if (mScroller!!.computeScrollOffset()) {
            itemRoot!!.scrollTo(mScroller!!.currX, mScroller!!.currY)
            if (itemRootLast != null) {
                itemRootLast!!.scrollTo(mScroller!!.currX, mScroller!!.currY)
            }
        }
        invalidate()
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(view: View?, position: Int)
    }

    public fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener?) {
        this.mOnItemClickListener = mOnItemClickListener
    }

}