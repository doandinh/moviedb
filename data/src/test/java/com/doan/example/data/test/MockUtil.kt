package com.doan.example.data.test

import com.doan.example.data.extensions.parseJsonAsSingle
import com.doan.example.data.remote.models.responses.*
import io.mockk.every
import io.mockk.mockk
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object MockUtil {

    val mockHttpException: HttpException
        get() {
            val response = mockk<Response<Any>>()
            val httpException = mockk<HttpException>()
            val responseBody = mockk<ResponseBody>()
            every { response.code() } returns 500
            every { response.message() } returns "message"
            every { response.errorBody() } returns responseBody
            every { httpException.code() } returns response.code()
            every { httpException.message() } returns response.message()
            every { httpException.response() } returns response
            every { responseBody.string() } returns "{\n" +
                "  \"message\": \"message\"\n" +
                "}"
            return httpException
        }

    val errorResponse = ErrorResponse(
        message = "message"
    )

    val movieResponse = MoviesResponse(
        page = 1
    )

    val movieDetailResponse = MovieDetailResponse(
        id = 123
    )

    @Throws(Exception::class)
    inline fun <reified T> getModel(clazz: Class<T>, fileName: String): T {
        val json = readJsonFile(fileName)
        return requireNotNull(parseJsonAsSingle(json))
    }

    @Throws(IOException::class)
    fun Any.readJsonFile(filename: String): String {
        val inputStream = requireNotNull(javaClass.getResourceAsStream(filename))
        val stringBuilder = StringBuilder()
        var index: Int
        val bytes = ByteArray(4096)
        while (inputStream.read(bytes).also { index = it } != -1) {
            stringBuilder.append(String(bytes, 0, index))
        }
        return stringBuilder.toString()
    }
}
