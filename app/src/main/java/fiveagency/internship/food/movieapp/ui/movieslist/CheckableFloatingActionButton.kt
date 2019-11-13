package fiveagency.internship.food.movieapp.ui.movieslist

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CheckableFloatingActionButton : FloatingActionButton, Checkable {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var checked: Boolean = false
    private val checkedStateSet = intArrayOf(android.R.attr.state_checked)
    private var checkedChangeListener: OnCheckedChangeListener? = null

    override fun isChecked(): Boolean = checked

    override fun toggle() {
        checked = !checked
        checkedChangeListener?.checkedChange(isChecked)
        refreshDrawableState()
    }

    override fun setChecked(checked: Boolean) {
        this.checked = checked
        checkedChangeListener?.checkedChange(isChecked)
        refreshDrawableState()
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (checked) {
            View.mergeDrawableStates(drawableState, checkedStateSet)
        }
        return drawableState
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    fun setOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
        this.checkedChangeListener = onCheckedChangeListener
    }
    interface OnCheckedChangeListener {
        fun checkedChange(isChecked: Boolean)
    }
}