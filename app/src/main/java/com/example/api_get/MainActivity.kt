package com.example.api_get

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.InputMethodManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//https://cursokotlin.com/capitulo-20-consumiento-apis-retrofit-2/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun initCharacter(puppies: Response) {
        if(puppies.status == "success"){
            imagesPuppies = puppies.images
        }
        Adapter = Adapter(imagesPuppies)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = Adapter
    }
//    override fun onQueryTextSubmit(query: String): Boolean {
//        searchByName(query.toLowerCase())
//        return true
//    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchByName(query: String) {
        doAsync {
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
            val puppies = call.body() as Response
            uiThread {
                if(puppies.status == "success") {
                    //initCharacter(puppies)
                }else{
                    showErrorDialog()
                }
                hideKeyboard()
            }
        }
    }
    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }

//    override fun onQueryTextChange(newText: String?): Boolean {
//        return true
//    }

    private fun hideKeyboard(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(viewRoot.windowToken, 0)
    }
}