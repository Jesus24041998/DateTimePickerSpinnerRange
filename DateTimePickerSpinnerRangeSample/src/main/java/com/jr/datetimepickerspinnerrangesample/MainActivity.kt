package com.jr.datetimepickerspinnerrangesample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jr.datetimepickerspinnerrangelibrary.DateTimePickerDialogSpinnerRange
import com.jr.datetimepickerspinnerrangelibrary.HoursRange
import com.jr.datetimepickerspinnerrangelibrary.MinutesRange
import com.jr.datetimepickerspinnerrangesample.databinding.ActivityMainBinding
import org.joda.time.DateTime

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNormal.setOnClickListener {
            invoke()
        }
        binding.button25.setOnClickListener {
            invoke("special2")
        }
        binding.button410.setOnClickListener {
            invoke("special3")
        }


    }
    @SuppressLint("SetTextI18n")
    private fun invoke(kind:String = "")
    {
        var hourRange = HoursRange.NORMAL_HOURS
        var minuteRange = MinutesRange.NORMAL_MINUTES
        when(kind)
        {
            "special2" -> {
                hourRange = HoursRange.TWO_HOURS
                minuteRange = MinutesRange.FIVE_MINUTES
            }
            "special3" ->{
                hourRange = HoursRange.FOUR_HOURS
                minuteRange = MinutesRange.TEN_MINUTES
            }
        }

        DateTimePickerDialogSpinnerRange(
            supportFragmentManager, true, hourRange, minuteRange,
            DateTime().hourOfDay, DateTime().minuteOfDay
        )
        { _, hour, minute ->
            binding.Check.text = "Spinner selected -> $hour hours and $minute minutes"
        }
    }
}