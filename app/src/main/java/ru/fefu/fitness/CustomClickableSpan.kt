import ru.fefu.fitness.R
import androidx.annotation.ColorInt
import android.content.Context
import android.text.style.ClickableSpan
import android.text.TextPaint
import android.view.View

class CustomClickableSpan(
    private val context: Context,
    private val onSpanClick: () -> Unit,
    @ColorInt private val color: Int = context.getColor(R.color.purple)
) : ClickableSpan() {

    override fun onClick(widget: View) {
        onSpanClick()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)

        ds.isUnderlineText = false
        ds.color = color
    }
}