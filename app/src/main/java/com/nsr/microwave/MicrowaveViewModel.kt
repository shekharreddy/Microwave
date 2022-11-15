package com.nsr.microwave

import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsr.microwave.utils.CookingTimes.CookingType.POTATO
import com.nsr.microwave.utils.CookingTimes.CookingType.VEGETABLE
import com.nsr.microwave.utils.CookingTimes.CookingType.BEVERAGE
import com.nsr.microwave.utils.CookingTimes.CookingType.POPCORN
import com.nsr.microwave.utils.CookingTimes.CookingType.REHEAT
import com.nsr.microwave.utils.CookingTimes.CookingType.DEFROST
import com.nsr.microwave.utils.CookingTimes.CookingType.MYPLATE

/**
 * Microwave view model to handle cooking types and all other functionalities
 */
class MicrowaveViewModel : ViewModel() {
    private var countdownTimer: CountDownTimer? = null
    private var cookTimeInMilliseconds = 0L
    private var pauseOffSet = 0L
    private var cookTime = 0L
    private val _formattedElapsedTime = MutableLiveData<String>("")
    val formattedElapsedTime: LiveData<String>
        get() = _formattedElapsedTime

    private val _startPause = MutableLiveData(false)
    val startPause: LiveData<Boolean>
        get() = _startPause

    fun updateTimer(view: View) {
        cookTime =  when (view.id) {
            R.id.button_popcorn -> POPCORN.cookTime
            R.id.button_beverage -> BEVERAGE.cookTime
            R.id.button_potato -> POTATO.cookTime
            R.id.button_vegetable -> VEGETABLE.cookTime
            R.id.button_reheat -> REHEAT.cookTime
            R.id.button_myplate -> MYPLATE.cookTime
            R.id.button_defrost -> DEFROST.cookTime
            else -> 0
        }
        updateLiveDataAndStartTimer()
    }

    private fun updateLiveDataAndStartTimer(){
        _formattedElapsedTime.value = getFormattedTime(
            cookTime
        )
        resetTimer()
        starTimer(cookTime)
    }

    fun handleNumberSelection(view: View) {
        //TODO number selection handling
    }

    fun add30Secs(){
        val remainingCookTime = cookTime - pauseOffSet
        cookTime += 30000
        if(remainingCookTime > 0) {
            updateLiveDataAndStartTimer()
        }
    }

    fun startOrPauseCooking() {
        _startPause.postValue(_startPause.value?.not())
    }

    private fun getFormattedTime(time: Long): String {
        val minutes = time / 1000 / 60
        val seconds = time / 1000 % 60
        return "$minutes : $seconds"
    }

    fun starTimer(cookTime : Long = 0){
        countdownTimer = object : CountDownTimer(cookTime - pauseOffSet, 1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseOffSet = cookTimeInMilliseconds - millisUntilFinished
                _formattedElapsedTime.postValue(getFormattedTime(millisUntilFinished))
            }

            override fun onFinish() {
            }
        }.start()
    }

    fun pauseTimer(){
        countdownTimer?.cancel()
    }

    private fun resetTimer(){
        if (countdownTimer!= null){
            countdownTimer?.cancel()
            countdownTimer = null
            pauseOffSet =0
        }
    }
}