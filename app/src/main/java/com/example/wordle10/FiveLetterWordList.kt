package com.example.wordle10

import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

private const val API_KEY = BuildConfig.API_KEY
object FiveLetterWordList {
    fun getRandomFiveLetterWord(callback: ((String?) -> Unit)? = null) {
        var word = ""

        val client = AsyncHttpClient()
        var headers = RequestHeaders()
        val params = RequestParams()
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = "{\"timezone\":\"UTC + 0\"}".toRequestBody(mediaType)

        val clientUrl = "https://wordle-game-api1.p.rapidapi.com/word"
        headers["x-rapidapi-key"] = API_KEY
        headers["x-rapidapi-host"] = "wordle-game-api1.p.rapidapi.com"
        headers["Content-Type"] = "application/json"

        client.post(clientUrl, headers, params, body, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                if (response != null) {
                    Log.d("onFailure", response)
                }
                callback?.invoke(null)
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JsonHttpResponseHandler.JSON) {
                word = json.jsonObject.getString("word")
                Log.d("onSuccess", word)
                callback?.invoke(word)
            }
        })
    }
}