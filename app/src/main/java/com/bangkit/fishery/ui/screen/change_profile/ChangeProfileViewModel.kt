package com.bangkit.fishery.ui.screen.change_profile

import androidx.lifecycle.ViewModel
import com.bangkit.fishery.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {


}