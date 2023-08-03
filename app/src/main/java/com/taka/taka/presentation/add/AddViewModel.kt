package com.taka.taka.presentation.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taka.taka.domain.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private val _state: MutableLiveData<String> = MutableLiveData()
    val state: LiveData<String>
        get() = _state
    private val _addSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val addSuccess: LiveData<Boolean>
        get() = _addSuccess

    fun addTicket(
        file: File,
        titleKor: String,
        titleEng: String,
        date: String,
        hall: String,
        seat: String,
        cast: String,
        seller: String,
        review: String
    ) {
        viewModelScope.launch {
            try {
                val body = hashMapOf<String, RequestBody>()
                body["titleKor"] = titleKor.toRequestBody("text/plain".toMediaTypeOrNull())
                body["titleEng"] = titleEng.toRequestBody("text/plain".toMediaTypeOrNull())
                body["date"] = date.toRequestBody("text/plain".toMediaTypeOrNull())
                body["hall"] = hall.toRequestBody("text/plain".toMediaTypeOrNull())
                body["seat"] = seat.toRequestBody("text/plain".toMediaTypeOrNull())
                body["cast"] = cast.toRequestBody("text/plain".toMediaTypeOrNull())
                body["seller"] = seller.toRequestBody("text/plain".toMediaTypeOrNull())
                body["review"] = review.toRequestBody("text/plain".toMediaTypeOrNull())
                val fileRequestBody: RequestBody = file.asRequestBody("image/png".toMediaTypeOrNull())
                val filePart = MultipartBody.Part.createFormData("img", file.name, fileRequestBody)

                val response = ticketRepository.addTicket(body, filePart)

                _addSuccess.value = response.success
            } catch (e: Exception) {
                _state.value = "티켓 추가에 실패했습니다"
                e.printStackTrace()
            }
        }
    }

    fun editTicket(
        ticketId: Int,
        file: File,
        titleKor: String,
        titleEng: String,
        date: String,
        hall: String,
        seat: String,
        cast: String,
        seller: String,
        review: String
    ) {
        viewModelScope.launch {
            try {
                val body = hashMapOf<String, RequestBody>()
                body["titleKor"] = titleKor.toRequestBody("text/plain".toMediaTypeOrNull())
                body["titleEng"] = titleEng.toRequestBody("text/plain".toMediaTypeOrNull())
                body["date"] = date.toRequestBody("text/plain".toMediaTypeOrNull())
                body["hall"] = hall.toRequestBody("text/plain".toMediaTypeOrNull())
                body["seat"] = seat.toRequestBody("text/plain".toMediaTypeOrNull())
                body["cast"] = cast.toRequestBody("text/plain".toMediaTypeOrNull())
                body["seller"] = seller.toRequestBody("text/plain".toMediaTypeOrNull())
                body["review"] = review.toRequestBody("text/plain".toMediaTypeOrNull())
                val fileRequestBody: RequestBody = file.asRequestBody("image/png".toMediaTypeOrNull())
                val filePart = MultipartBody.Part.createFormData("img", file.name, fileRequestBody)

                val response = ticketRepository.editTicket(ticketId, body, filePart)

                _addSuccess.value = response.success
            } catch (e: Exception) {
                _state.value = "티켓 수정에 실패했습니다"
                e.printStackTrace()
            }
        }
    }
}