package com.disheecompose.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disheecompose.data.local.LocalCuratorProvider.curatorList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel(){
    private val _signInResult = MutableStateFlow<Boolean?>(null)
    val signInResult: StateFlow<Boolean?> = _signInResult


    fun signIn(email: String, password: String){
        viewModelScope.launch {
            try {
                val curator = curatorList.find { it.email == email }

                _signInResult.value = curator?.password == password

                _signInResult.value =  curator?.password == password

                Log.d("AuthViewModel", "signInResult: ${_signInResult.value}")
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}