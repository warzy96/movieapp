package fiveagency.internship.food.movieapp.ui.pager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.core.view.children

class WindowInsetsFrameLayout : FrameLayout {

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setOnHierarchyChangeListener(object : OnHierarchyChangeListener {
            override fun onChildViewRemoved(parent: View?, child: View?) {
            }

            override fun onChildViewAdded(parent: View?, child: View?) {
                requestApplyInsets()
            }
        })
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        children.forEach {
            it.dispatchApplyWindowInsets(insets)
        }
        return insets
    }
}