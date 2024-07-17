package com.jr.datetimepickerspinnerrangelibrary


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.annotation.IntRange
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jr.datetimepickerspinnerrangelibrary.databinding.DateTimePickerDialogSpinnerRangeBinding
import kotlin.math.abs

enum class HoursRange(val range: Int) {
    NORMAL_HOURS(1),
    TWO_HOURS(2),
    FOUR_HOURS(4);
}

enum class MinutesRange(val range: Int) {
    NORMAL_MINUTES(1),
    FIVE_MINUTES(5),
    TEN_MINUTES(10);
}

class DateTimePickerDialogSpinnerRange @JvmOverloads constructor(
    val supportFragment: FragmentManager,
    val is24Hours: Boolean = false,
    val intervalHour: HoursRange,
    val intervalMinutes: MinutesRange,
    @IntRange(from = 0, to = 23) var actualHour: Int,
    @IntRange(from = 0, to = 59) var actualMinute: Int,
    val callback: (view: TimePicker, hour: Int, minutes: Int) -> Unit
) : DialogFragment() {
    lateinit var binding: DateTimePickerDialogSpinnerRangeBinding
    lateinit var timePicker: TimePicker

    init {
        this.setStyle(STYLE_NORMAL, theme)
        this.show(supportFragment, DateTimePickerDialogSpinnerRange::class.java.simpleName)
    }

    private fun replacePicker(
        view: ViewGroup,
        replaceMinute: Boolean = true,
        is24Hours: Boolean
    ): NumberPicker? {
        val viewChilds = (view.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup
        for (i in 0 until viewChilds.childCount) {
            val child = viewChilds.getChildAt(i)
            when {
                child is NumberPicker && (child.maxValue == 59) && replaceMinute -> return child
                child is NumberPicker && child.maxValue == (if (is24Hours) 23 else 11) && !replaceMinute -> return child
            }
        }
        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DateTimePickerDialogSpinnerRangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).also { dialog ->
            dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            timePicker = binding.timePicker
            timePicker.setIs24HourView(is24Hours)
            val displayHoursValues: ArrayList<String> = ArrayList()
            val displayMinutesValues: ArrayList<String> = ArrayList()
            val hours = if (is24Hours) 23 else 11
            for (i in 0..hours step intervalHour.range) {
                if (i.toString().length == 2) displayHoursValues.add(
                    String.format(
                        "%2d",
                        i
                    )
                ) else displayHoursValues.add(i.toString())
            }
            for (i in 0..59 step intervalMinutes.range) {
                if (i.toString().length == 2) displayMinutesValues.add(
                    String.format(
                        "%2d",
                        i
                    )
                ) else displayMinutesValues.add("0$i")
            }


            val minutePicker = replacePicker(timePicker, is24Hours = is24Hours)
            val hourPicker = replacePicker(timePicker, false, is24Hours)


            hourPicker?.apply {
                minValue = 0
                maxValue = displayHoursValues.lastIndex
                displayedValues = displayHoursValues.toTypedArray()
            }

            minutePicker?.apply {
                minValue = 0
                maxValue = displayMinutesValues.lastIndex
                displayedValues = displayMinutesValues.toTypedArray()
            }
            hourPicker?.displayedValues.apply {
                val number = this?.minByOrNull { abs(it.toInt() - actualHour) }?.toInt() ?: 0
                var positionIndex = 0
                this?.forEachIndexed { index, s -> if (s.toInt() == number) positionIndex = index }
                timePicker.hour = positionIndex
            }
            minutePicker?.displayedValues.apply {
                val number = this?.minByOrNull { abs(it.toInt() - actualMinute) }?.toInt() ?: 0
                var positionIndex = 0
                this?.forEachIndexed { index, s -> if (s.toInt() == number) positionIndex = index }
                timePicker.minute = positionIndex
            }

            timePicker.setOnTimeChangedListener { _, hourOfDay, minutes ->
                actualHour = hourPicker?.displayedValues?.get(hourOfDay)?.toInt() ?: 0
                actualMinute = minutePicker?.displayedValues?.get(minutes)?.toInt() ?: 0
            }

            binding.btnAcceptTime.setOnClickListener {
                callback.invoke(timePicker,actualHour,actualMinute)
                dialog?.dismiss()
            }
            binding.btnCancelTime.setOnClickListener { dialog?.dismiss() }

        } catch (_: Exception) {
        }
    }
}